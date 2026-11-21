// 八丈タウンアプリ Service Worker（簡易版）
// ページ本体は常にネットワーク優先、アイコン等の静的ファイルだけキャッシュします。
const CACHE = "hachijo-pwa-v2";
const STATIC_FILES = ["./icon-192.png", "./icon-512.png", "./apple-touch-icon.png", "./manifest.webmanifest"];

self.addEventListener("install", (event) => {
  event.waitUntil(
    caches.open(CACHE).then((cache) => cache.addAll(STATIC_FILES)).then(() => self.skipWaiting())
  );
});

self.addEventListener("activate", (event) => {
  event.waitUntil(
    caches.keys().then((keys) =>
      Promise.all(keys.filter((k) => k !== CACHE).map((k) => caches.delete(k)))
    ).then(() => self.clients.claim())
  );
});

self.addEventListener("fetch", (event) => {
  const url = new URL(event.request.url);
  const isStatic = STATIC_FILES.some((f) => url.pathname.endsWith(f.replace("./", "/")));
  if (isStatic) {
    event.respondWith(
      caches.match(event.request).then((hit) => hit || fetch(event.request))
    );
  }
  // それ以外（ページ・天気APIなど）はそのままネットワークへ
});
