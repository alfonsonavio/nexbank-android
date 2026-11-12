# NexBank Android

[![CI](https://github.com/alfonsonavio/nexbank-android/actions/workflows/ci.yml/badge.svg?branch=develop)](https://github.com/alfonsonavio/nexbank-android/actions/workflows/ci.yml)

Android client for **NexBank**, a portfolio digital banking platform built on a Java/Spring Boot
microservices backend. The app is developed contract-first against a shared OpenAPI definition,
so it can progress against a mock server while the backend catches up.

> **Status:** work in progress. Phase 0 (project foundations) is complete; feature work starts next.

## Tech stack

| Area | Choice | Status |
| --- | --- | --- |
| Language | Kotlin 2.2 | ✅ |
| UI | Jetpack Compose, Material 3 | ✅ |
| Build | Gradle 9, AGP 9 (built-in Kotlin), convention plugins in `build-logic` | ✅ |
| Quality | Spotless + ktlint + compose-rules, Android Lint, GitHub Actions CI | ✅ |
| Architecture | Clean Architecture, multi-module, MVI | 🔜 |
| DI / async | Hilt, Coroutines, Flow | 🔜 |
| Data | Retrofit + OkHttp, Room (offline), Paging 3 | 🔜 |
| Security | BiometricPrompt + PIN, encrypted token storage (Keystore) | 🔜 |
| Testing | JUnit, Turbine, MockWebServer, Compose UI tests | 🔜 |

## Planned module structure
app/ Application, DI graph, navigation
build-logic/ Gradle convention plugins
core/model Pure Kotlin domain models
core/domain Repository interfaces and use cases
core/data Repositories, mappers, offline sync
core/network Retrofit API, auth interceptor, token refresh
core/network-mock MockWebServer responses based on the API contract
core/database Room
core/security Token storage, biometrics, PIN
core/designsystem Material 3 theme and components
feature/* auth, accounts, transactions, transfers

## Getting started

Requirements: Android Studio (latest stable), JDK 25 for Gradle (provisioned automatically).

```bash
git clone https://github.com/alfonsonavio/nexbank-android.git
cd nexbank-android
./gradlew assembleDebug
```

Useful tasks:

| Task | Purpose |
| --- | --- |
| `./gradlew spotlessApply` | Format the code |
| `./gradlew spotlessCheck` | Verify formatting (runs in CI) |
| `./gradlew testDebugUnitTest` | Unit tests |
| `./gradlew lintDebug` | Android Lint |

## Workflow

- `main` holds stable releases; `develop` is the integration branch.
- Every change goes through a pull request with green CI; branches are protected.
- Commits follow [Conventional Commits](https://www.conventionalcommits.org/).

## Architecture decisions

- [ADR 0001 — Static analysis](docs/adr/0001-static-analysis.md)
- [ADR 0002 — Architecture and modules](docs/adr/0002-architecture-and-modules.md)
- [ADR 0003 — Contract-first API](docs/adr/0003-contract-first-api.md)

## Changelog

See [CHANGELOG.md](CHANGELOG.md).

## License

[MIT](LICENSE)
