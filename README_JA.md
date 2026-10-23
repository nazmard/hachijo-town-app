# 八丈町アプリ（Android試作版）

八丈町の住民向け情報ポータルのAndroid Studioプロジェクトです。現在は町公式サイトなどへのリンクを中心とした初期試作です。八丈町の公式アプリではありません。

## 現在の機能
- 八丈町公式ホームページへのリンク
- 行政手続きの入口
- 町の新着情報へのリンク
- 船・飛行機の交通情報サイトへのリンク
- 地区選択（三根・大賀郷・樫立・中之郷・末吉）
- 防災・防災行政無線関連情報へのリンク
- 議会・議員SNSページの枠組み
- Androidの戻るボタン、WebView表示

## まだ実装されていない機能
- 町公式サイトの新着情報の自動取得
- 地区別ごみ収集日の正式データ
- プッシュ通知／防災無線の自動取得
- 議員の公式SNSアカウント一覧
- オフライン対応、アクセシビリティの追加検証

## APKをビルドする方法
この配布ZIPにはAndroid Studio用のソースプロジェクトが含まれています。APKそのものは含まれていません。

1. パソコンにAndroid Studioをインストールします。
2. Android Studioでこのフォルダー（`HachijoTownApp`）を開きます。
3. Android SDK Platform 35と、Android Studioが案内するGradle/JDKをインストールします。
4. Gradle同期が完了したら、メニューの **Build > Build Bundle(s) / APK(s) > Build APK(s)** を選択します。
5. 生成されたデバッグAPKは通常 `app/build/outputs/apk/debug/app-debug.apk` に出力されます。
6. APKを自分のAndroidスマホに転送して開き、必要に応じて「この提供元からのアプリを許可」を有効にしてインストールします。

## 開発環境
- Android application ID: `jp.hachijo.townapp`
- Minimum SDK: 24 (Android 7.0)
- Compile/Target SDK: 35
- Kotlin + Android WebView

## 公式リンクについて
外部リンクは制作時点の初期設定です。公開・配布前にリンク先、利用条件、町の公式情報との整合性を再確認してください。行政・防災情報は公式発表を優先してください。

## APK自動ビルド
`.github/workflows/build-apk.yml` を追加しています。初回設定は `BUILD_APK_AUTOMATIC_JA.md` を参照してください。GitHub Actions がデバッグAPKを生成し、Artifact としてダウンロードできるようにします。
