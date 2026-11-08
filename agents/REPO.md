# Repo rules — metallic-arts

Repo-specific rules and notes. The org-wide `AGENTS.md` beside this file is
maintained in [`neoforge-mod-template`](https://github.com/HexaHexes/neoforge-mod-template)
and pulled in verbatim — **never hand-edit it.** Everything in this file is this
repo's own and is edited freely; keep it aligned with the org rules rather than
restating them.

**Repo:** `HexaHexes/metallic-arts` · **Mod ID:** `metallic_arts` · **Branch:** `main`
**Platform:** Minecraft 1.21.1 / NeoForge 21.1.259 / JDK 21 · **Jar:** `build/libs/metallic_arts-<version>.jar`

An Allomancy-inspired metal magic mod for the Tensura RPG pack: players ingest
allomantic metals and burn them to shape power drawn from Sky's Ley Lines'
nexus network. Every metal power is an Ability Core ability, so the whole set
appears in the Ability Hotbar beside Tensura skills, battlewills and Iron's
Spells spells without bespoke UI. The repo is in **planning** - the design
lives in `docs/` and code lands milestone by milestone (`docs/roadmap.md`).

**Dependencies**
- Required: Minecraft, NeoForge.
- Planned optional (gated via `ModList`, declared optional in the mods toml
  until the interop code lands): `ability_core`, `ability_hotbar`,
  `skysleylines`. Later, still optional: Iron's Spells 'n Spellbooks,
  Tensura: Reincarnated. Absent upstreams must degrade to a no-op, never a
  crash - follow ability-core's `LeyLinesResources` pattern: gate before
  touch, upstream-typed code in one bridge class the JVM never resolves when
  the mod is missing.

**Test:** `./gradlew runGameTestServer` — `MetallicArtsGameTests` (1 test).
`expectedTests = 1` in `build.gradle` must match the `@GameTest` count; grow
both together as milestones land.

**Gotchas**
- Interop posture for ARR upstreams (Iron's Spells, Tensura: Reincarnated) is
  interop-only - public API, events and datapack formats; never copy source or
  assets (org doctrine, recorded in ability-core's ADR-005). Attribution lives
  in `NOTICE`.
- All Ley Lines / ability-core references belong behind the `ModList` gate;
  the mods toml marks them optional until that gate exists in code.
- When a milestone adds `@GameTest` methods, update `expectedTests` in the
  same commit or CI fails the run.
- Client-array mixins load nowhere in CI (gametests run a dedicated server):
  never `@Shadow` inherited Minecraft members, and launch `runClient` once
  before a release - full rules in `docs/mixin-rules.md`.
