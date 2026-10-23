#!/usr/bin/env bash
set -euo pipefail
if ! command -v gradle >/dev/null 2>&1; then
  echo "Gradle not found. Open this project in Android Studio or follow BUILD_APK_AUTOMATIC_JA.md for GitHub Actions." >&2
  exit 1
fi
gradle --no-daemon assembleDebug
echo "APK created at: app/build/outputs/apk/debug/app-debug.apk"
