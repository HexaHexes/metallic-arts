# ADR-002: Every metal power is an Ability Core ability

Status: accepted

## Context

The pack runs one configurable hotbar (ability-hotbar) over one ability
contract layer (ability-core), already serving Tensura skills and magic,
battlewills, and Iron's Spells spells. A bespoke activation path for metal
powers would fork targeting, costs, cooldowns, unlocking, overlay data and
sync - all problems core has already solved - and would leave metal powers
invisible to the pack's UI.

## Decision

All three arts (eventually) register their powers as ordinary
`AbilityDefinition` entries and consume core's extension points: activation
handlers per effect family, ability modes for flare/tap-rate, unlock
providers for awakening, eligibility rules for race/consent gates, resource
pairs for the ley currency and the metal reserve. No bespoke hotbar, no
bespoke activation, no bespoke cost path.

## Consequences

- The mod is a pure ability provider - exactly the shape `ability-iss` and
  `ability-tensura` proved.
- With `ability_core` absent the mod loads as a no-op (REQ-MA-06); the
  toml dependency flips to required when interop lands (M5).
- Datapack-first costs and gating ride core's existing override formats.

Links: `design.md`, core `docs/api-surface.md` (the fourteen extension
points), ADR-001.
