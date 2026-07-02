$stdin = [Console]::In.ReadToEnd()
$active = $false
if ($stdin) {
    try {
        $data = $stdin | ConvertFrom-Json
        if ($data.stop_hook_active -eq $true) { $active = $true }
    } catch {
        Write-Warning "auto-ship-stop.ps1: failed to parse stdin JSON, treating as no unshipped work: $_"
        exit 0
    }
}

if ($active) {
    exit 0
}

try {
    $repoRoot = Resolve-Path (Join-Path $PSScriptRoot '..\..') -ErrorAction Stop
} catch {
    Write-Warning "auto-ship-stop.ps1: could not resolve repo root, skipping: $_"
    exit 0
}
Push-Location $repoRoot
$ErrorActionPreference = 'SilentlyContinue'

$branch = (git rev-parse --abbrev-ref HEAD 2>$null)
if (-not $branch -or $branch -eq 'main' -or $branch -eq 'master' -or $branch -eq 'HEAD') {
    Pop-Location
    exit 0
}

$hasUncommitted = [bool](git status --porcelain 2>$null)

$hasUnpushed = $false
$upstream = (git rev-parse --abbrev-ref --symbolic-full-name '@{u}' 2>$null)
if ($LASTEXITCODE -eq 0 -and $upstream) {
    $hasUnpushed = [bool](git log "$upstream..HEAD" --oneline 2>$null)
} else {
    git rev-parse --verify main *> $null
    $defaultBranch = if ($LASTEXITCODE -eq 0) { 'main' } else { 'master' }
    $hasUnpushed = [bool](git log "origin/$defaultBranch..HEAD" --oneline 2>$null)
}

Pop-Location

if (-not ($hasUncommitted -or $hasUnpushed)) {
    exit 0
}

$reason = "You have unshipped work on branch '$branch' (uncommitted changes and/or commits not yet pushed to origin). Per CLAUDE.md's Automation & Approval Policy, commit / push / PR creation / merge-to-main are pre-authorized for routine feature or fix work -- do not stop to ask for confirmation. Run the full ship workflow now, in order, stopping only if a step fails: (1) mvn --batch-mode clean verify; (2) /code-review at medium effort against the working diff, fixing any CONFIRMED correctness finding and re-running step 1; (3) stage only the relevant files and commit with a Conventional Commit message; (4) git push (with -u if there is no upstream yet); (5) check for an existing PR with 'gh pr view --json number,state', and if none exists, create one with 'gh pr create --fill --base main'; (6) run 'gh pr merge --auto --squash'; (7) report the PR URL and merge status."

$payload = @{ decision = 'block'; reason = $reason } | ConvertTo-Json -Compress
Write-Output $payload
exit 0
