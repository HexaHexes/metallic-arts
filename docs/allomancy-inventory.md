# Allomancy inventory - every power, end to end, and its metal in this pack

The complete inventory of allomantic powers from the Mistborn novels, how each
works end to end in canon, how we adapt it into Minecraft through Ability Core
and Sky's Ley Lines, and where each metal comes from in the Tensura RPG pack
(Minecraft, Base Metals, Tensura: Reincarnated).

Sibling arts: `feruchemy-inventory.md` (storing and tapping) and
`hemalurgy-inventory.md` (spike theft).

Canon reference: Brandon Sanderson's Mistborn Era 1 (the sixteen metals) and
Era 2 (divine metals in technology). This is an unaffiliated fan work - no
novel text or art is reproduced; see `NOTICE`.

---

## 1. Canon end-to-end model (what we are adapting)

In the books an allomancer's power works as one pipeline:

1. **Ingest** - metals are swallowed, usually as flakes suspended in a vial of
   alcohol. A vial holds a measured reserve of one or several metals.
2. **Reserve** - the metal sits in the stomach as a finite reserve. Allomancers
   feel the reserve like a fullness that drains; they know roughly how much
   remains.
3. **Burn** - the allomancer "burns" a metal: the metal is consumed steadily
   and converted into the effect. Only pure metals and correctly proportioned
   alloys work; the wrong alloy is useless or poisonous.
4. **Flare** - burning faster for a stronger, shorter effect.
5. **Depletion** - when the reserve empties the effect stops mid-stroke.
6. **Targets** - external metals (iron/steel) need actual metal to act on; the
   allomancer sees "lines" to metal sources, and relative mass decides whether
   the object moves, the allomancer moves, or both (Newton's third law).
7. **Access** - a Misting can burn exactly one of the sixteen; a Mistborn burns
   all sixteen (plus atium in Era 1).
8. **Interactions** - duralumin burns every active reserve in one flare;
   aluminum wipes your own reserves; a coppercloud hides its occupants from
   Seekers (and from emotional allomancy); atium shows the near future and
   electrum's self-shadows are its counter.

## 2. Our end-to-end pipeline (the adaptation)

Two buckets, one pipeline. Metals are the catalyst, ley power is the fuel
(ADR-001); every burn consumes both.

```
 craft flakes/vials        ingest                unlock gate
 BaseMetals/vanilla  -->  allomantic   -->      (snapping /
 Tensura materials        reserve            AbilityUnlocks)
                              |
                              v
   Ability Hotbar activation request (ability-core)
                              |
              +---------------+----------------+
              |                                |
      eligibility chain                 afford check
      (unlocked? PvP config?)           BOTH buckets:
              |                          - metal reserve attachment
              |                          - ley currency via
              |                            core's LeyLinesResources pair
              v                                (quoteAt = can I?)
        ACTED: effect runs        --------------> spendAt = charge it
              |
   overlay + sync (reserve bar, burn state in the hotbar HUD)
```

Concretely, per burn:

| Step | Mechanism |
|---|---|
| Metal stock | `metallic_arts` flake/vial items (ADR-004). Raw ingots are **not** swallowable - they are crafting stock for flakes/vials, which keeps the economy ours and stops players "eating" other mods' gear metals. |
| Ingest | Right-click a vial/flakes: adds N units to the per-metal reserve data attachment on the player. Vials stack; the attachment is the real store. |
| Unlock | `AbilityUnlocks.declare(...)` - a metal is only usable after its awakening (see section 5). |
| Activation | Each metal is an `AbilityDefinition` in `AbilityRegistries.ABILITY_REGISTRY_KEY`; effects run through our `ActivationHandler`s; flare is an ability **mode** (`AbilityModes.declare`) with an `AmountResolver` scaling both costs. |
| Fuel check/charge | One shared Ley Lines currency `metallic_arts:metal` declared at startup through `LeyLinesResources.declare(...)`; datapack costs read `"resource": "metallic_arts:metal"`. `quoteAt`/`spendAt` at the player's position handle affordability and charge against the personal pool that nexuses refill. |
| Catalyst charge | Our `ResourceConsumers` pair for `metallic_arts:reserve` drains the per-metal attachment (this is the second, metal-specific bucket). |
| Hold burns | `ChargingModel` hold/toggle drains the attachment and ley pool per second; the hotbar overlay shows both draining (core's availability/metric contract, REQ-HOT-08). |
| Empty | When either bucket runs dry the burn stops mid-effect - the canon "reserve gone" feeling. |

## 3. The metals - full inventory (the Era 1 sixteen plus the Era 2 pair)

Cost shapes below are relative (U = one reserve unit, L = one ley unit);
actual numbers live in datapacks and get tuned in playtests.

### Physical quadrant

**Iron - the pull (Lurcher)**
- Canon: burns iron to *pull* on metal sources. Lines to metal; mass decides
  who moves - pull on a light coin drags it to you, pull on a heavy gate and
  you fly toward the gate.
- Ours: instant pulse, self-recastable while held. `TargetResolver` finds
  metal entities/blocks in ~16 blocks (vanilla/BM/Tensura metal items,
  armor, machines); impulse toward the strongest target. If the target is
  heavier, the impulse applies to the caster (grapple/flight, the fun part).
- Reserve: iron (1 U per pulse, scaled by mass moved). Cost: 1 L base + mass.
- Metal in the pack: **vanilla** `minecraft:iron_ingot` (also nuggets/raw).

**Steel - the push (Coinshot)**
- Canon: the mirror of iron - *push* metal away; the classic "throw a coin,
  push the coin, fly".
- Ours: instant push impulse; with light metal items (our allomantic coin
  item) held, this is the movement spell of the mod; with heavy metal targets,
  the caster recoils. Same mass rule as iron.
- Reserve: steel (1 U/pulse + mass). Cost: 1 L base + mass.
- Metal in the pack: **Base Metals** `basemetals:steel_ingot` (iron + carbon
  alloy); **Tensura** `tensura:magisteel` as the pure/graded variant (section 6).

**Tin - the senses (Tineye)**
- Canon: enhances all five senses while burning; flaring tin is painful and
  overwhelming. This is a *hold* burn.
- Ours: toggle. Night Vision, mob outlines within ~24 blocks (Glowing effect
  on hostiles, client-tinted), louder/longer footsteps. Flare mode: range
  doubles, but Wither/Slowness flash when it ends (sensory overload).
- Reserve: tin (1 U per 10 s hold). Cost: 1 L per 10 s.
- Metal in the pack: **Base Metals** `basemetals:tin_ingot`.

**Pewter - the body (Pewterarm / Thug)**
- Canon: the workhorse - strength, speed, endurance, balance, pain
  suppression and faster healing while burning; "pewter drag" - when the
  reserve ends mid-fight, all suppressed damage lands at once.
- Ours: toggle. Strength I (+flare: II), Speed I, Resistance I, Jump Boost I,
  fall-damage forgiveness, slow Regen. End-of-burn "pewter drag": 5 s of
  Weakness/Slowness proportional to damage soaked. This deliberately overlaps
  Tensura body skills and ISS buffs - balancing is an open question (OQ-5).
- Reserve: pewter (1 U per 5 s hold; flares burn 5x). Cost: 2 L per 5 s.
- Metal in the pack: **Base Metals** `basemetals:pewter_ingot` (in the 3.x
  alloy roster; verify registry name in M0).

### Mental quadrant

**Zinc - the riot (Rioter)**
- Canon: *amplifies and agitates* existing emotions in targets - enrage a
  crowd, sharpen a soldier's aggression.
- Ours: targeted mob use first - set the target's anger on a nearby entity
  (pull aggro, turn mobs on each other); area flare version riots a group.
  On players: requires the server's PvP consent config (OQ-4).
- Reserve: zinc (1 U per mob). Cost: 1 L per mob.
- Metal in the pack: **Base Metals** `basemetals:zinc_ingot`.

**Brass - the soothe (Soother)**
- Canon: *dampens* emotions - calm a mob, defuse a crowd.
- Ours: mirror of zinc - clears a mob's aggro, stops flee/anger spreading;
  flare pacifies an area. Players per OQ-4.
- Reserve: brass (1 U per mob). Cost: 1 L per mob.
- Metal in the pack: **Base Metals** `basemetals:brass_ingot` (copper+zinc).

**Copper - the cloud (Smoker)**
- Canon: burns copper to raise a "coppercloud" that hides everyone inside
  from Seekers and emotional allomancy.
- Ours: toggle aura (~8 blocks): occupants are hidden from our bronze
  (section below), immune to zinc/brass, and - the pack-specific treat -
  hidden from Ley Lines' Ley Vision pings (interop question for MMD, OQ-3).
- Reserve: copper (1 U per 10 s). Cost: 1 L per 10 s.
- Metal in the pack: **vanilla** `minecraft:copper_ingot`.

**Bronze - the seek (Seeker)**
- Canon: feels allomantic "pulses" - who nearby is burning what, and in which
  direction.
- Ours: toggle. Periodic ping: renders particle lines to any player with an
  active metal burn (direction + strength, not identity unless flaring);
  extension hooks read other ability mods' active states (an ISS caster
  burning a spell reads like a metal pulse - interop-only, OQ-3) and Ley
  Lines nexus bearings.
- Reserve: bronze (1 U per 10 s). Cost: 1 L per 10 s.
- Metal in the pack: **Base Metals** `basemetals:bronze_ingot` (copper+tin).

### Enhancement quadrant

**Aluminium - the wipe (Aluminum Gnat)**
- Canon: instantly burns away *all of your own* reserves of every metal you
  are burning. Era 1 calls gnats nearly useless; its real value is the purge.
- Ours: instant - dumps every metal reserve attachment to zero and ends all
  active burns. Uses: deny a leecher, reset a duralumin accident, exit a
  pewter drag. Cheap, deliberate, and flavored as the "panic button".
- Reserve: aluminium (all of it, all metals). Cost: 1 L flat.
- Metal in the pack: **not in Base Metals' current roster** (verify against
  the 3.10 NeoForge jar in M0). Fallback: our own aluminium ore through
  MMD OreSpawn, or a nether-merchant recipe. This is the one basic metal with
  a sourcing gap - see OQ-2.

**Duralumin - the burst (Duralumin Gnat)**
- Canon: burns *everything currently burning* in one instant, massively
  amplified flare. Duralumin + pewter = one explosive super-strength moment.
- Ours: instant, requires at least one active burn. Multiplies the current
  effect of every active burn (pewter: one huge hit of Strength; steel: a
  cannon push), consumes **all** reserves of every active metal, costs 2 L.
  The combo spell - and the noob trap (duralumin with nothing burning does
  nothing).
- Reserve: duralumin (1 U) + everything burning. Cost: 2 L flat.
- Metal in the pack: our alloy - real duralumin is **aluminium + copper**;
  craft duralumin flakes from our aluminium flakes + vanilla copper (canon
  proportions, our recipe).

**Chromium - the leech (Leecher)**
- Canon: on touch, *wipes the target's metal reserves*.
- Ours: targeted touch-range instant. On players: zeroes their metal
  attachments (crushing, but non-violent). PvE answer so it is not PvP-only:
  a leech on a **nexus field** siphons a small amount of ley power from the
  local reserve into the caster's pool (Ley Lines `quoteAt`/`spendAt` in
  reverse - needs MMD API check, OQ-3).
- Reserve: chromium (1 U). Cost: 1 L + drained amount.
- Metal in the pack: **ours** - Base Metals has no chromium; add a small
  allomantic chromium ore (OreSpawn, rare, mountain biomes) in M2 (OQ-2).

**Nicrosil - the boost (Nicroburst)**
- Canon: on touch, *supercharges the target's current burn* the way duralumin
  supercharges your own - their choice, their burn, our burst.
- Ours: targeted ally/foe touch. Ally: grants one free flare of their active
  burn (no reserve cost). Foe (PvP): forces their flare - they burn their own
  reserves whether they want to or not.
- Reserve: nicrosil (1 U). Cost: 1 L.
- Metal in the pack: our alloy - real nicrosil is **copper + chromium**;
  craft from chromium flakes + vanilla copper.

### Temporal quadrant

**Gold - the past self (Augur)**
- Canon: shows a vision of who you *might have been* - a disorienting
  self-haunting. No combat use in canon; pure flavor with edge.
- Ours: instant "vision" - a short spectral duplicate of the player walks
  their recent path (client particles + stored position ring buffer), grants
  5 s of slow Regeneration ("facing yourself steadies you"). Low power, high
  flavor; the RP metal.
- Reserve: gold (1 U). Cost: 1 L.
- Metal in the pack: **vanilla** `minecraft:gold_ingot`.

**Electrum - the future self (Oracular)**
- Canon: gold's mirror - shadows of *your own future*, split across possible
  choices; the traditional counter to atium because your atium-shadow splits
  into uncertainty.
- Ours: toggle (short). 8 s of predictive evasion: a chance-based dodge buff
  (projectiles get a missed roll; melee gets a brief Speed burst on block).
  Anti-atiuim rule: while electrum burns, an attacker's atium dodge-bonus is
  halved (both burners see split shadows - the canon counter, as a rule).
- Reserve: electrum (1 U per 5 s). Cost: 2 L per 5 s.
- Metal in the pack: **Base Metals** `basemetals:electrum_ingot` (gold+silver).

**Atium - the god metal (Seer)**
- Canon: Ruin's god metal - burning it shows everyone's next few seconds as
  "atium shadows", letting you dodge everything. The most valuable substance
  in Era 1; it burns fast and nothing beats a seer except another seer.
- Ours: the endgame metal. Short toggle (5 s, expensive): all incoming
  attacks/projectiles in range get a heavy dodge roll for the caster, and
  the caster's next hit lands un-dodgeable (the "seer strikes the shadow").
  Electrum halves it per the rule above. Cost makes it a finisher, not a
  lifestyle.
- Reserve: atium (1 U per 1 s - fastest burn rate of all). Cost: 4 L per s.
- Metal in the pack: **Tensura** `tensura:hihiirokane` as the in-pack god
  metal - the legendary endgame material of Tensura lore, so the pack's
  final metal comes from the pack's final ore tier (atium flakes are crafted
  from hihiirokane + our flux in M4). Own art, own item, Tensura-sourced.

**Malatium - the eleventh metal (atium + gold)**
- Canon: atium's gold-alloy twin - shows another person's *alternate past*,
  who they could have been. The Eleventh Metal mystery of Era 1.
- Ours: targeted vision - reveal the target mob's/stats history briefly.
  The pack tie-in makes it sing: a malatium vision opens the same
  analysis view Tensura's Great Sage uses (Jade window, analysis family -
  see tensura-greatsage-growth) even without the Sage - "reading the
  echoes in a person's metal".
- Reserve: malatium (1 U). Cost: 2 L.
- Metal in the pack: our alloy - atium flakes + vanilla gold.

### The Era 2 pair - cadmium and bendalloy

Era 2 rounds the temporal quadrant to a stable sixteen by replacing
atium/malatium with two discovered metals. We ship both sets; the Era 1 pair
is the rarer, deeper endgame of the two.

**Cadmium - the slow bubble (Pulser)**
- Canon: burns cadmium to create a bubble in which *time flows slower* for
  everyone else - area denial, chokepoints.
- Ours: anchored toggle bubble (~8 blocks, persists briefly after cast):
  entities inside get Slowness and reduced projectile speed; the caster is
  unaffected (canon). Expensive - the crowd-control metal. PvE-first; PvP
  per OQ-4.
- Reserve: cadmium (1 U per 5 s). Cost: 3 L per 5 s.
- Metal in the pack: **ours** - Base Metals has no cadmium; add alongside
  chromium (OQ-2), or gate to nether traders for v1.

**Bendalloy - the fast bubble (Slider)**
- Canon: the mirror - a bubble in which *time flows faster*; Sliders are the
  gunslingers' cover, speeding their own shots.
- Ours: anchored bubble: the *caster and allies* inside act faster (Swiftness
  to arrows/potions, faster growth ticks on crops - the peaceful build),
  brief duration, high cost. Pair with pewter for the canon gunslinger
  fantasy.
- Reserve: bendalloy (1 U per 5 s). Cost: 3 L per 5 s.
- Metal in the pack: **ours** - real bendalloy is a cadmium-family alloy;
  craft from our cadmium + Base Metals bismuth (BM has bismuth as a
  non-generating ore we can request via OreSpawn config or trades).

### Outside the sixteen (divine metals)

**Lerasium - Preservation's metal.** Canon: burning lerasium (rarely, once)
*permanently makes you an allomancer* - the origin of Mistborn lines. Ours:
the endgame item. Consumed on use: unlocks the full sixteen (Mistborn) -
or, in the Tensura frame, converts into a one-time "ultimate skill" moment.
Source: single drops in the deepest Infinite Dungeon floors + the awakening
questline (M4/M5). Not a burnable reserve - a permanent unlock token.

**Ettmetal / Harmonium.** Canon: Harmony's metal - violently unstable
(explodes with water), used as a power *source* in Era 2 technology. Ours:
post-MVP tech tier - a craftable power cell that feeds ley currencies into
machines (TechnomancyDemo direction) rather than an ability. Explicitly out
of scope for v1; parked so the door stays open.

## 4. Metal sourcing matrix (pack mapping)

Legend: **V** = vanilla, **BM** = Base Metals (NeoForge 1.21.1 build 3.10,
registry names verified in M0), **T** = Tensura: Reincarnated, **Ours** =
`metallic_arts` registers it. "Alloy of" is the canon alloy.

| # | Metal | Alloy of | Misting | Source | Item (planned) |
|---|---|---|---|---|---|
| 1 | Iron | - | Lurcher | V | `minecraft:iron_ingot` |
| 2 | Steel | iron+carbon | Coinshot | BM (+T grade) | `basemetals:steel_ingot`, `tensura:*magisteel*` |
| 3 | Tin | - | Tineye | BM | `basemetals:tin_ingot` |
| 4 | Pewter | (its own metal) | Pewterarm | BM | `basemetals:pewter_ingot` |
| 5 | Zinc | - | Rioter | BM | `basemetals:zinc_ingot` |
| 6 | Brass | copper+zinc | Soother | BM | `basemetals:brass_ingot` |
| 7 | Copper | - | Smoker | V | `minecraft:copper_ingot` |
| 8 | Bronze | copper+tin | Seeker | BM | `basemetals:bronze_ingot` |
| 9 | Aluminium | - | Aluminum Gnat | Ours (BM gap) | `metallic_arts:aluminium_*` |
| 10 | Duralumin | aluminium+copper | Duralumin Gnat | Ours (alloy) | `metallic_arts:duralumin_flakes` |
| 11 | Chromium | - | Leecher | Ours (BM gap) | `metallic_arts:chromium_*` |
| 12 | Nicrosil | copper+chromium | Nicroburst | Ours (alloy) | `metallic_arts:nicrosil_flakes` |
| 13 | Gold | - | Augur | V | `minecraft:gold_ingot` |
| 14 | Electrum | gold+silver | Oracular | BM | `basemetals:electrum_ingot` |
| 15 | Atium | (god metal) | Seer | T (rebuilt) | `metallic_arts:atium_flakes` (from `tensura:hihiirokane*`) |
| 16 | Malatium | atium+gold | Eleventh Metal | Ours (alloy) | `metallic_arts:malatium_flakes` |
| 17 | Cadmium | - | Pulser | Ours (BM gap) | `metallic_arts:cadmium_*` |
| 18 | Bendalloy | cadmium+bismuth | Slider | Ours (alloy) | `metallic_arts:bendalloy_flakes` |
| - | Lerasium | (god metal) | - | Ours (loot) | `metallic_arts:lerasium_bead` |
| - | Ettmetal | (god metal) | - | parked | - |
| - | Silver | - | (not burnable) | BM | alloy stock for electrum only - canon-faithful |

Notes on the gaps:

- **Silver is not allomantic in canon** - Base Metals giving us electrum's
  ingredient is exactly right. No ability.
- **Aluminium, chromium and cadmium are the only basic metals Base Metals
  lacks.** All three are ours: aluminium via a small OreSpawn ore or trader
  recipe (OQ-2), chromium via a rare mountain ore, cadmium likewise rare.
  Three ores is the whole worldgen footprint of this mod - everything else
  is crafting on top of the pack's existing materials.
- **Atium/malatium/lerasium** are rebuilt as our items crafted from pack
  materials (hihiirokane, gold) so their rarity inherits the pack's endgame
  economy instead of adding parallel worldgen.

## 5. Access and progression (who can burn what)

- **Snapping** (the canon "trauma awakens power" beat, adapted): the first
  metal unlocks through the awakening event/questline - details parked in
  `open-questions.md` (OQ-1). One metal = **Misting**; the lerasium bead =
  **Mistborn** (all sixteen).
- Every metal is gated through core's `AbilityUnlocks`, so race/skill gating
  from Tensura rides the same eligibility chain later (M4).

## 6. Burn grades - the Tensura material tie-in (proposal, OQ-6)

The same canon metal can exist in grades; the grade multiplies reserve
capacity (and slightly the flare strength), not the effect type:

| Grade | Source | Example (steel family) |
|---|---|---|
| Common | V / BM | vanilla iron, `basemetals:steel_ingot` |
| Pure | T | crafted with `tensura:*magisteel*` |
| Adamant | T | crafted with `tensura:adamantite*` |
| Orichalcum | T | crafted with `tensura:orichalcum*` |
| God | T-rebuilt | atium tier, from `tensura:hihiirokane*` |

Grades are an M4+ refinement; v1 ships flat grades so the inventory above
stays the whole surface.

## 7. Implementation family map (which contracts each metal touches)

| Family | Metals | Core contracts exercised |
|---|---|---|
| Impulse (external) | iron, steel | `TargetResolver`, mass rule, instant cost + mass `AmountResolver` |
| Body/senses (toggle) | tin, pewter | attribute application, hold `ChargingModel`, end-of-burn drag |
| Emotion (targeted) | zinc, brass | mob AI targeting, PvP consent `EligibilityRule` |
| Cloud/seek (aura) | copper, bronze | active-burn state queries, particle lines, Ley Vision interop |
| Burst/wipe (instant) | aluminium, duralumin | reserve attachment writes, multi-metal interaction |
| Touch (targeted) | chromium, nicrosil | other-player reserves, nexus siphon (interop) |
| Vision/temporal | gold, electrum, atium, malatium | dodge/evasion rolls, Jade/analysis tie-in, highest costs |

## 8. Open questions this inventory raises

- OQ-1 - snapping: event vs quest vs config-driven first metal.
- OQ-2 - aluminium + chromium sourcing (ore vs loot vs trader).
- OQ-3 - interop contracts with MMD (Ley Vision hiding, nexus siphon) and
  ISS/Tensura reads for bronze/malatium - all interop-only, all optional.
- OQ-4 - emotional allomancy on players: consent config tiers.
- OQ-5 - pewter/tin overlap with Tensura body skills and ISS buffs: stacking
  rules.
- OQ-6 - burn grades: v1 flat vs graded from the start.
- OQ-7 - vial granularity: one vial per metal vs multi-metal vials like canon.

The living versions of these sit in `open-questions.md`.
