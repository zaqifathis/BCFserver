# dev-reset.ps1
$ErrorActionPreference = "Stop"

Write-Host "Stopping and removing containers..." -ForegroundColor Cyan
docker-compose down -v

Write-Host "Removing Keycloak data..." -ForegroundColor Cyan
if (Test-Path .\keycloak_data) {
    Remove-Item -Recurse -Force .\keycloak_data
}

Write-Host "Removing shared folder..." -ForegroundColor Cyan
if (Test-Path .\shared) {
    Remove-Item -Recurse -Force .\shared
}

Write-Host "Recreating shared folder..." -ForegroundColor Cyan
New-Item -ItemType Directory -Path .\shared | Out-Null

Write-Host "Resetting AUTH_CLIENT_SECRET in .env to placeholder..." -ForegroundColor Cyan
(Get-Content .env) -replace '^AUTH_CLIENT_SECRET=.*', 'AUTH_CLIENT_SECRET=placeholder' | Set-Content .env

Write-Host "Done. Environment reset to fresh-clone state." -ForegroundColor Green