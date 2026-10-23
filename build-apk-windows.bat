@echo off
setlocal
where gradle >nul 2>nul
if errorlevel 1 (
  echo Gradle was not found. Please open this project in Android Studio, or use the GitHub Actions instructions in BUILD_APK_AUTOMATIC_JA.md.
  pause
  exit /b 1
)
gradle --no-daemon assembleDebug
if errorlevel 1 exit /b 1
echo.
echo APK created at: app\build\outputs\apk\debug\app-debug.apk
pause
