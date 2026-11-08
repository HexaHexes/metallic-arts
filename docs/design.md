# The working design

The systems view: what the three arts are in this pack, how each one pays for
its power, and the requirements the implementation must satisfy. Per-metal
detail lives in the three inventory documents; raw ideas live in
`brain-dump.md`.

## The one-sentence design

Three Metallic Arts, three resource models, one ability system: Allomancy
burns ley-fuelled metal reserves, Feruchemy banks your own attributes in
metalminds, Hemalurgy steals power from victims at a permanent price - and
every power in all three arts is an ordinary Ability Core ability shown in
the Ability Hotbar.

## Three arts, three economies

| | Allomancy | Feruchemy | Hemalurgy |
|---|---|---|---|
| Power source | Ley Lines (end-positive in our frame) | Yourself, banked (end-neutral, ADR-005) | Stolen from victims (end-negative) |
| Consumes | Ley currency `metallic_arts:metal` + per-metal reserve | Metalmind charge only | The victim; decay on the spike |
| Access | Snapping -> Misting -> lerasium -> Mistborn | Ferring by craft/medallion -> Full Feruchemist | Knowledge only - anyone, at a cost |
| Cost curve | Burn rate / flare modes | Compression curve (tap fast, lose more) | Spike count, Ruin's Grip, decay |
| Geography | Nexuses refill your pool | None (portable) | None (portable, dark) |
| Milestone | M1-M5 | M6+ | M6+ |

The three models share one piece of infrastructure: the per-metal source
items (ADR-004) and the ability-core contracts (ADR-002). Nothing else is
shared - deliberately, because the canon arts *feel* different mostly
through their economies.

## Architecture sketch (planning-level)

```
org.bitwisemadness.metallicarts
  +-- metal        metal catalog (16 + god metals), source-item bindings
  +-- reserve      per-player metal reserve attachment (allomancy bucket 2)
  +-- burn         ActivationHandlers per family, flare modes, AmountResolvers
  +-- feruchemy    (M6+) metalmind items, storing/tapping state, compression
  +-- hemalurgy    (M6+) spikes, steal tables, decay, bind-point slots
  +-- interop      ModList-gated bridges: ley (via core's LeyLinesResources),
  |                iss, tensura - one bridge class each, no upstream types
  |                in any public signature (org doctrine)
  +-- client       overlays (reserve bars), metal lines, bubble rendering
```

Datapack-first wherever ability-core's contracts allow: costs, steal tables,
unlock gating and burn grades ride `datapack-overrides` patterns rather than
code, so the pack team tunes balance without a rebuild.

## Requirements (working set)

REQ-MA-01 - Every allomantic metal in `allomancy-inventory.md` is an
`AbilityDefinition` charged through the `metallic_arts:metal` ley pair and a
per-metal reserve pair; no bespoke activation path.

REQ-MA-02 - A burn with an empty reserve stops mid-effect (canon depletion),
never at a "clean" boundary; the overlay shows both buckets draining.

REQ-MA-03 - Iron/steel implement the mass rule: relative mass decides whether
the target, the caster, or both move. This is a game test (CI gametest, not
manual-only).

REQ-MA-04 - Flare is an ability mode on every burnable metal, with the cost
multiplier resolved through `AmountResolvers`.

REQ-MA-05 - With `skysleylines` absent the mod runs degraded: abilities read
`no_provider` and refuse cleanly (core's #92 auto-disable chain behaviour);
with it present, nexuses refill personal pools (M2).

REQ-MA-06 - With `ability_core` absent the mod loads and does nothing except
log - the pack can drop this mod without breaking a server.

REQ-MA-07 - Awakening (OQ-1) gates the first metal through
`AbilityUnlocks`; the server config can force any of the three trigger
models.

REQ-MA-08 - The three sourcing gaps (aluminium, chromium, cadmium) are the
mod's only worldgen; everything else is crafting on pack materials.

REQ-MA-09 - Emotional allomancy (zinc/brass) and touch metals
(chromium/nicrosil) respect the server's consent config on players (OQ-4).

REQ-MA-10 - (M6) Feruchemy stores/taps through metalmind items without any
ley interaction; compression curve implemented as the flare-mode cost curve.

REQ-MA-11 - (M6) Hemalurgic decay, blood-coating preservation and Ruin's
Grip are all item/player state with no per-tick world scanning.

REQ-MA-12 - Every gametest milestone grows `expectedTests` in the same
commit (CI contract).

## Non-goals (v1)

- Feruchemy and Hemalurgy (M6+ - research complete, scope pending OQ-8).
- Compounding, medallions, constructs - all documented, all parked.
- Any mixin into ARR upstreams (ADR-003).
- Balance parity with Tensura endgame - the pack's tuning pass owns that.
