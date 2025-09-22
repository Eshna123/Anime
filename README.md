# Seekho Anime App


Kotlin + Jetpack Compose app for Seekho assignment. Uses Jikan API to fetch top anime, shows details, caches via Room, supports offline mode.


### How to run
- Open in Android Studio (Chipmunk or later).
- Add internet permission (already included).
- Build & Run on emulator/device.


### Assumptions
- Minimal external libraries to keep small & clear.
- No DI framework to keep code readable.


### Features implemented
- Fetch top anime from Jikan API
- List and detail screens
- Trailer (YouTube) opens in browser/webview fallback (Compose WebView not included to keep simple)
- Room caching for anime list
- Offline mode: shows cached data
- Basic error handling (Snackbars/messages)


### Known limitations
- No Hilt/Dagger used for dependency injection
- Minimal unit tests
- Cast list is best-effort from API; sometimes empty
