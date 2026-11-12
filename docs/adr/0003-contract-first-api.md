# ADR 0003: Contract-first API

- Status: Accepted
- Date: 2026-11-12

## Context
The Android app and the backend microservices are built in parallel by the same developer,
and the backend is behind schedule.

## Decision
- The API is defined first in OpenAPI 3.1 (`docs/api/nexbank-api.yaml`). Backend and app adapt
  to the contract, never the other way round.
- Until backend work resumes, the contract lives in this repository. It will then move to the
  backend monorepo, which becomes the source of truth, with this copy updated by pull request.
- During development the app talks to MockWebServer responses derived from the contract.
- Key conventions: amounts as decimal strings with currency (never floating point),
  RFC 9457 problem details for errors, cursor pagination for transactions,
  and an `Idempotency-Key` header on transfers.

## Consequences
- The app can be built and tested end to end before the backend exists.
- Contract changes must be made deliberately, in one place, before code changes.
