# Integration notes - per mod, against the real APIs

How each mod in the Tensura RPG pack touches this one. Stance everywhere:
**interop-only** for ARR upstreams (ADR-003, org doctrine recorded in
ability-core's ADR-005) - public API, events and datapack formats; never
source or asset reuse; never a mixin into an upstream.

## Ability Core (`HexaHexes/ability-core`, LGPL-2.1) - the spine

- Depend via GitHub Packages maven (coordinates in core's
  `docs/api-surface.md`); optional in the mods toml until interop lands
  (REQ-MA-06), required after.
- Registration: `AbilityDefinition` entries at `RegisterEvent` for
  `AbilityRegistries.ABILITY_REGISTRY_KEY`; one `ActivationHandler` +
  `ChargingModel` per effect family (impulse, toggle, targeted, aura, burst,
  touch, vision).
- Costs: datapack `costs` entries read `"resource": "metallic_arts:metal"`
  (the ley pair) and `"resource": "metallic_arts:reserve"` (our per-metal
  reserve pair - declared provider-first then consumer per the pairing
  rule).
- Flare: `AbilityModes.declare` + an `AmountResolvers` pricer.
- Unlock: `AbilityUnlocks.declare` for snapping/awakening; later the Tensura
  race gate rides the same `EligibilityChain`.
- Hotbar: `IconProviders`/`AvailabilityProviders`/`PresentationProviders`/
  `VisibilityHandlers` - nothing bespoke, the bar already renders
  cooldown/cost overlays (REQ-HOT-08 in core).
- Sync: `AbilitySyncNeededEvent` carries reserve/burn state to the client.
- The Ley Lines bridge: core's `interop.leylines.LeyLinesResources.declare(
  currencyId)` - we are the adopter that chooses the currency id
  (`metallic_arts:metal`); `quoteAt`/`spendAt` at the player's position map
  to afford/charge. Gate before touch: no Ley Lines class links when the
  mod is absent.

## Ability Hotbar (`HexaHexes/ability-hotbar`) - the shared UI

Nothing to build: every registered ability appears in the pack's one hotbar
beside Tensura skills, battlewills and ISS spells. Optional dep in the toml
for version-range honesty only. Reserve/charge bars render through core's
metric contract, not hotbar code.

## Sky's Ley Lines (`MinecraftModDevelopmentMods/SkysLeyLines`, LGPL-2.1) - the fuel

- MC 1.21.1 NeoForge (0.5.6.121012 beta line, same build server+client).
- Register our currency with Ley Lines (their `docs/INTEGRATION.md` quick
  start), then declare the pair through core's bridge at mod construction.
- Personal pools: adopt their player-pool + draw-from-nexus-over-time model -
  standing at a nexus refills `metallic_arts:metal`; away from lines you
  burn on fumes. Dowsing rods/Ley Meter/Ley Vision are the player-facing
  geography this mod leans on.
- Interop asks for MMD (all optional, all OQ-3): (a) can a coppercloud
  suppress Ley Vision pings? (b) can chromium siphon a nexus field?
  (c) does bronze get a nexus bearing? One conversation, no pressure - each
  degrades to no-op.

## Base Metals (`MinecraftModDevelopmentMods/BaseMetals`, LGPL-2.1) - the metals

- Pack build: `BaseMetals 3.10 For NeoForge 1.21.1` (MMD OreSpawn is its
  required dep). Roster verified from the project docs: Copper, Tin, Zinc,
  Lead, Mercury, Nickel, Platinum, Silver, Antimony, Bismuth; alloys Brass,
  Bronze, Cupronickel, Electrum, Invar, **Pewter**, **Steel**; fantasy
  Adamantine, Aquarium, Cold Iron, Mithril, Starsteel.
- We consume (flakes/vials/metalminds/spikes crafted from): steel, tin,
  pewter, zinc, brass, bronze, silver (electrum stock), electrum, bismuth
  (bendalloy stock).
- M0 spike: dump the 3.10 NeoForge jar's registry to pin exact item ids
  (the 1.12-era names like `basemetals:steel_ingot` are the planning
  assumption, not a promise).
- Registry-name note: Base Metals 3.x kept `com.mcmoddev.basemetals`
  packages/registry names on purpose - good for us.

## Tensura: Reincarnated (ARR) - the frame

- Interop-only: events, public API, datapack formats (ADR-003).
- Race gating: eligibility rule reading the ManasCore race attachment
  (the `capital-tensura-reincarnated` SET_RACE hook pattern) - which races
  may snap, and racial burn modifiers (M4, config-off by default).
- EP/skill framing: allomancy presented as an *intrinsic skill family* in
  analysis (Great Sage / Jade window); malatium vision opens that window
  without the Sage (see allomancy inventory).
- Burn grades (OQ-6): magisteel/mithril/adamantite/orichalcum/hihiirokane as
  pure->god grade stocks; atium flakes from hihiirokane (verified item
  family from the pack's datagen: `tensura:low/high/pure_magisteel`,
  `tensura:mithril_*`, `tensura:adamantite_*`, `tensura:orichalcum_*`,
  `tensura:hihiirokane_*`).
- Awakening questline via tensura-questlines (M4); hemalurgy skill-theft
  idea parked at OQ-14.

## Iron's Spells 'n Spellbooks (ARR, with an explicit addon grant) - the peers

- Interop-only; their LICENSE grants dependent addons (no asset reuse).
- Same hotbar via core - parity already exists (ability-iss proved the
  pattern).
- Candidate interop (all optional, all behind ModList): bronze reads active
  spellcasts as pulses; nicrosil tapping as generic cast fuel (OQ-10);
  chromium leech on a caster drains cast resources *through their public
  API only*. Nothing here gates the mod.

## Curios - the wearing layer

Metalminds and kandra blessings want Curios slots (canon: skin contact);
spikes implant into body slots. Optional dep; held-items fallback. The pack
already runs Curios (pseudocore-curios, ability-iss), so this is the
expected path, not the fallback.

## Jade / tensura-jade - the analysis window

Malatium visions and Tensura analysis share the tooltip window
(tensura-jade already embeds the analysis UI) - a read-only tie-in for
flavor, M4.

## Infinite Dungeon - the rewards

Lerasium bead and hihiirokane (-> atium) as deepest-floor rewards; the
hemalurgist miniboss archetype (coppercloud-piercing, drops charged spikes)
as a later dungeon archetype. Ties the arts into the pack's risk ladder.
