# APKを自動ビルドする手順（初回のみ設定が必要）

このプロジェクトには GitHub Actions 用の自動ビルド設定を含めています。実行環境に Android SDK がない場合でも、GitHub Actions 上で APK をビルドできます。

## 手順
1. GitHub にログインし、新しい空のリポジトリを作成します（例：`hachijo-town-app`）。
2. ZIP を展開し、`hachijo-android` フォルダーの中身をリポジトリにアップロードしてコミットします。`.github/workflows/build-apk.yml` も含めてください。
3. リポジトリの **Actions** タブを開き、`Build Android APK` を選び、**Run workflow** を実行します（main/master への push でも自動実行されます）。
4. 完了した実行を開き、Artifacts の `HachijoTownApp-debug-apk` をダウンロードします。
5. ZIP を展開し、`app-debug.apk` を Android スマホへ転送してインストールします。初回は Android の設定で、そのファイル管理アプリに「不明なアプリのインストール」を許可する必要がある場合があります。

## 注意
- これは自分の端末で試すためのデバッグAPKです。Google Play公開用の署名済みリリースAPKではありません。
- このリポジトリを公開するとソースコードも公開されます。公開したくない場合は private リポジトリを使ってください。
- APKのビルドはクラウド上で実行されます。初回設定として GitHub へのプロジェクトアップロードと Actions の実行は必要です。
- このアプリは八丈町が公式に提供・承認したアプリではありません。
- 町の新着情報の自動取得、地区別ごみ収集日の正確な登録、防災無線の自動取り込み、議員のSNS個別一覧は今後の開発項目です。
