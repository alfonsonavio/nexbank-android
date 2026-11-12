# ADR 0002: Clean Architecture with feature and core modules

- Status: Accepted
- Date: 2026-11-12

## Context
The app will grow to several features (auth, accounts, transactions, transfers) and must be
testable without a real backend, which is not ready yet.

## Decision
- Layers: UI (Compose + MVI ViewModels) → domain (use cases, repository interfaces) → data.
- Modules: `core/*` for shared layers and `feature/*` per screen flow.
- `feature` modules depend only on `core:domain`, `core:model`, `core:designsystem` and `core:ui`,
  never on `core:data` or `core:network`. The data source can be swapped without touching UI code.
- `core:model` and `core:domain` are pure Kotlin (no Android), so their tests run on the JVM.
- Build configuration lives in convention plugins (`build-logic`), not in each module.

## Consequences
- More modules to set up at the start; offset by faster incremental builds and enforced boundaries.
- The dependency rule is enforced by the module graph, not by convention.
