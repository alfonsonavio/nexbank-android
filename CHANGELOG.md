# Changelog

All notable changes to this project are documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added
- Gradle convention plugins in `build-logic` for application, library, Compose and JVM modules.
- GitHub Actions CI: formatting check, debug build, unit tests and Android Lint on every pull request.
- Spotless with ktlint and compose-rules, shared with the IDE through `.editorconfig`.
- README, MIT license, architecture decision records and the OpenAPI contract.
- Changelog.
- `core:model` module with the `Money` value type.
- Domain models in `core:model`: `Iban` (with mod-97 validation), `Account`, `Transaction`,
  `Transfer` with a sealed `TransferStatus`, and `NewTransfer` with the contract's input limits.

### Changed
- IDE configuration (`.idea/`) is no longer tracked.
- CI runs unit tests with `./gradlew test`, so pure JVM modules are covered.
- Enable parallel Gradle sync in Android Studio (`org.gradle.tooling.parallel`).
