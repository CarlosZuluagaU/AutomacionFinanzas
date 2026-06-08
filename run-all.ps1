# run-all.ps1
# Levanta backend + frontend y ejecuta la automatizacion Serenity BDD

$BACKEND_DIR  = "C:\Users\carlo\OneDrive\Documentos\GitHub\fabrica_2026S11\fabrica_2026S1\MS_Finanzas"
$FRONTEND_DIR = "C:\Users\carlo\OneDrive\Documentos\GitHub\ComerciosConectaFinalUdeA\FrontEndFe20261"
$AUTO_DIR     = "C:\Users\carlo\OneDrive\Documentos\GitHub\fabrica_2026S11\AutomacionFinanzas"

function Wait-ForUrl {
    param([string]$Url, [string]$Name, [int]$TimeoutSec = 120)
    Write-Host "Esperando $Name en $Url ..." -ForegroundColor Yellow
    $elapsed = 0
    while ($elapsed -lt $TimeoutSec) {
        try {
            $r = Invoke-WebRequest -Uri $Url -TimeoutSec 3 -UseBasicParsing -ErrorAction Stop
            if ($r.StatusCode -lt 500) {
                Write-Host "$Name listo! ($elapsed s)" -ForegroundColor Green
                return
            }
        } catch {}
        Start-Sleep 3
        $elapsed += 3
        Write-Host "  ... $elapsed s"
    }
    Write-Host "TIMEOUT: $Name no respondio en $TimeoutSec s. Verifica los logs." -ForegroundColor Red
    exit 1
}

# ── 1. Backend ────────────────────────────────────────────────────────────────
Write-Host "`n[1/3] Levantando backend (localhost:8080) ..." -ForegroundColor Cyan
$backendCmd = "cd '$BACKEND_DIR'; `$env:SPRING_PROFILES_ACTIVE='local'; .\mvnw spring-boot:run"
Start-Process powershell -ArgumentList "-NoExit", "-Command", $backendCmd -WindowStyle Normal

# ── 2. Frontend ───────────────────────────────────────────────────────────────
Write-Host "[2/3] Levantando frontend (localhost:3000) ..." -ForegroundColor Cyan
$frontendCmd = "cd '$FRONTEND_DIR'; npm run dev"
Start-Process powershell -ArgumentList "-NoExit", "-Command", $frontendCmd -WindowStyle Normal

# ── 3. Esperar que ambos esten listos ─────────────────────────────────────────
Wait-ForUrl "http://localhost:8080/actuator/health" "Backend" 120
Wait-ForUrl "http://localhost:3000"                 "Frontend" 60

# ── 4. Automatizacion ─────────────────────────────────────────────────────────
Write-Host "`n[3/3] Ejecutando tests de automatizacion ..." -ForegroundColor Cyan
Set-Location $AUTO_DIR
.\gradlew clean test

# ── 5. Resultado ──────────────────────────────────────────────────────────────
Write-Host "`nReporte Serenity generado en:" -ForegroundColor Green
Write-Host "$AUTO_DIR\build\site\serenity\index.html" -ForegroundColor White
Write-Host "`nCierra las ventanas del backend y frontend cuando termines." -ForegroundColor Gray
