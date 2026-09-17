# dev-up.ps1
$ErrorActionPreference = "Stop"

if (-not (Test-Path .\shared)) {
  Write-Host "Creating .\shared folder..." -ForegroundColor Cyan
  New-Item -ItemType Directory -Path .\shared | Out-Null
}

Write-Host "Starting Keycloak..." -ForegroundColor Cyan
docker-compose up -d keycloak

Write-Host "Waiting for Keycloak to become healthy, then provisioning realm/client..." -ForegroundColor Cyan
docker-compose run --rm keycloak-init

if (-not (Test-Path .\shared\client-secret.txt)) {
  Write-Error "client-secret.txt not found — keycloak-init likely failed. Check logs: docker-compose logs keycloak-init"
  exit 1
}

$secret = (Get-Content .\shared\client-secret.txt -Raw).Trim()

if ([string]::IsNullOrWhiteSpace($secret)) {
  Write-Error "Secret file was empty — keycloak-init did not produce a value."
  exit 1
}

Write-Host "Updating .env with generated AUTH_CLIENT_SECRET..." -ForegroundColor Cyan
(Get-Content .env) -replace '^AUTH_CLIENT_SECRET=.*', "AUTH_CLIENT_SECRET=$secret" | Set-Content .env

Write-Host "Starting bcf-app..." -ForegroundColor Cyan
docker-compose up -d bcf-app

Write-Host "Done. Run 'docker-compose ps' to verify status." -ForegroundColor Green