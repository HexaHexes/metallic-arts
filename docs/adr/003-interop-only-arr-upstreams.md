# ADR-003: ARR upstreams stay interop-only

Status: accepted

## Context

The pack's magic-adjacent upstreams carry All Rights Reserved licences:
Iron's Spells 'n Spellbooks (ARR with an explicit grant for dependent
addons, no asset reuse) and Tensura: Reincarnated (ARR, structural API use
as the published-addon precedent). Base Metals, Sky's Ley Lines, ability-core
and the hotbar are LGPL-2.1 and safe to read.

## Decision

Adopt ability-core's ADR-005 posture for this repo: ARR upstreams are
consumed through events, public fields/registries, datapack formats and
runtime API calls only; never copy source or assets into our jars; no
mixins into Iron's Spells or Tensura. Every upstream-typed call sits behind
a `ModList` gate in a single bridge class per upstream, with no upstream
type named in any public signature.

## Consequences

- Bronze/nicrosil/malatium interop ideas (OQ-10, OQ-14) live or die by the
  availability of a public API seam - degrade to no-op otherwise.
- Attribution obligations live in `NOTICE` (already lists the upstreams).
- CI leak-gate greps for upstream package names in contract code, mirroring
  core's approach.

Links: ability-core `docs/adr/005-arr-interop-stance.md` (the org
doctrine this records locally), `integrations.md`, `NOTICE`.
