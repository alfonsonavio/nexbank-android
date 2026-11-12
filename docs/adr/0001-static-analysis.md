# ADR 0001: Static analysis with Spotless, ktlint and compose-rules

- Status: Accepted
- Date: 2026-11-12

## Context
The project needs automated, consistent formatting and style checks, enforced in CI,
across two development machines.

## Decision
- Use Spotless with ktlint (`android_studio` code style) and compose-rules,
  configured once in the root build and shared with the IDE through `.editorconfig`.
- `spotlessCheck` runs first in CI and blocks the merge.
- Defer detekt: the stable release (1.23.x) targets Kotlin 2.0 and the project uses
  Kotlin 2.2; detekt 2.0, which supports it, is still in alpha.

## Consequences
- Formatting is never discussed in reviews; `gradlew spotlessApply` fixes it.
- Code smell and complexity analysis is not covered yet. Revisit when detekt 2.0 is stable.
