# Feruchemy inventory - the art of balance, end to end

The second Metallic Art, as it exists in Brandon Sanderson's works (Mistborn
Era 1 and Era 2), how storing and tapping work end to end in canon, how we
would adapt it into Minecraft, and where its metals come from in the Tensura
RPG pack. Companion to `allomancy-inventory.md` and `hemalurgy-inventory.md`.

Canon reference: the Feruchemical table (Era 2 Ars Arcanum), the Compounding
rules, and the Southern Scadrian medallion technology. Unaffiliated fan work -
no novel text or art is reproduced; see `NOTICE`.

**Scope note:** Feruchemy is future scope for this repo (post-M5, see
`roadmap.md` and OQ-8). This document records the research so the scope
decision is made against a complete picture.

---

## 1. Canon end-to-end model (what we are adapting)

Feruchemy is **end-neutral**: no power is gained from an outside source and
none is lost - it is only moved through time.

1. **Store** - a Feruchemist converts one of their own attributes into
   Investiture and stores it in a metal they touch (a **metalmind**). While
   storing, the user is *deprived* of that attribute: store strength and you
   are weak; store weight and you are lighter; store senses and you go dull.
2. **Tap** - later, they draw the attribute back out. Tap at the same rate it
   went in and it is 1:1; tap faster and **compression** loses some of the
   attribute - the harder you compress, the more is lost. There is no
   theoretical cap on how fast you can tap.
3. **Capacity** - a bigger metalmind holds more, on thresholds rather than a
   linear scale. A broken metalmind splits its charge among the pieces. An
   impure alloy holds less.
4. **Keying** - a metalmind is keyed to its maker's **Identity**; nobody else
   can tap it (they can sense it is charged). An **unkeyed** metalmind
   (aluminum Feruchemy, Identity manipulation) can be used by anyone with the
   relevant ability; an **unsealed** metalmind (nicrosil + duralumin
   construction, the Southern Scadrian **medallions**) can be used by anyone
   at all - the nicrosil layer grants the ability itself.
5. **Access** - a **Ferring** can use exactly one metal (each Ferring type has
   a name - Skimmer, Bloodmaker...); a **Full Feruchemist** uses all sixteen.
   Historically Full Feruchemists existed; after the Catacendre the power
   diluted into Ferrings.
6. **Twinborn and Compounders** - a Twinborn has one Allomantic and one
   Feruchemical ability. When both abilities use the **same metal**, they are
   a Compounder: they can burn their own metalmind Allomantically and get a
   massive burst of the stored attribute (the Lord Ruler's immortality was
   atium-youth Compounding). Different-metal Twinborn pairings produce a
   lesser bonus called a **Resonance**.
7. **Interactions** - charged metalminds resist Steelpushes/Ironpulls in
   proportion to their charge (a full metalmind is effectively immune);
   bronze cannot detect Feruchemy without an unknown "tweak"; a Hemalurgic
   spike that stole Feruchemy carries enough of the donor's Identity to
   *unlock the donor's metalminds*; gold Feruchemy can heal Hemalurgic
   damage.

The signature of the art, and what makes it a *different mod* from Allomancy
rather than more of the same: **the cost is paid in yourself, in advance.**
Power is banked, not drawn from the world.

## 2. Our end-to-end pipeline (the adaptation)

```
 craft metalmind        wear / hold                store
 (same 16 source   -->  curios or     -->  toggle storing: attribute is
 metals, ADR-004)       hotbar             suppressed, metalmind charges
                                     (curios: metalmind must touch skin)
                                |
                                v
             tap (ability in the Ability Hotbar)
                                |
              +-----------------+------------------+
              |                                    |
        metalmind charge                   tap-rate selection
        (its own resource -                (normal / flare = compression
         NO ley currency, ADR-005)          curve: faster tap, more loss)
              |                                    |
              v                                    v
        afford check (charge >= cost)      effect applies, debuff cleared
              |
   overlay + sync (charge bar on the item, availability in the hotbar)
```

Key differences from the Allomancy pipeline, deliberately kept:

- **No ley fuel, no vials.** The metalmind's charge is the only resource
  (ADR-005). The hotbar overlay shows a charge bar, not a burn cost - core's
  `AvailabilityProvider`/`Metric` contract handles this fine.
- **You pay while storing.** Storing is a toggle that applies the negative
  state (see table) and charges the item; tapping spends charge. This is
  server-authoritative state on the player, synced through core's sync
  events.
- **Curios integration** (optional): metalminds must be worn (canon: skin
  contact) to store or tap - the pack already uses Curios heavily
  (pseudocore-curios, ability-iss spellbook). Without Curios, held items
  work.
- **Identity keying** - a metalmind you craft and charge is yours alone.
  **Unsealed medallions** (M6+, endgame craft) are the exception: party gear
  anyone can tap, which slots beautifully into the pack's multiplayer loops.
- **Tap-rate = ability modes.** Core's `AbilityModes` contract drives
  normal/flare tapping with the compression curve as the cost multiplier -
  the canon "faster tap, more loss" rule, mechanically free.

## 3. The sixteen feruchemical metals - full inventory

Feruchemy uses the same sixteen metals as Allomancy (plus atium), so the
sourcing matrix in `allomancy-inventory.md` section 4 applies unchanged.
Cost shapes: C = one charge unit stored/tapped.

| # | Metal | Ferring name | Stores | Canon notes | Our adaptation (store cost -> tap effect) |
|---|---|---|---|---|---|
| 1 | Iron | Skimmer | Weight | Wax's signature; heavier pushes/pulls, feather falls | Store: Slowness + heavier knockback taken. Tap: weightlessness - Slow Falling, huge Iron/Steel self-impulse bonus (Allomancy interplay) |
| 2 | Steel | Steelrunner | Speed | Steelrunners are terrifying in a fight | Store: Slowness. Tap: Speed II-IV (flare: Speed V + mining/attack speed) |
| 3 | Tin | Windwhisperer | Senses | Wayne stores senses to ignore smells | Store: Blindness/dull (no night vision, sound dampen). Tap: Night Vision + glowing-hostile outline (shared code with allomantic tin) |
| 4 | Pewter | Brute | Strength | The most common combat Ferring | Store: Weakness. Tap: Strength II, +attack knockback, break blocks bare-handed |
| 5 | Zinc | Sparker | Mental speed | Faster thought and aim | Store: Mining Fatigue I ("slow head"). Tap: faster bow draw / quicker cooldowns while tapped |
| 6 | Brass | Firesoul | Warmth | Fire-immunity storage | Store: you freeze (brief Slowness in cold). Tap: Fire Resistance for the stored duration |
| 7 | Copper | Archivist | Memories | Copperminds archive knowledge | Store: loses XP gain briefly? (OQ-9 - memory has no clean MC analogue; XP is the candidate). Tap: re-read stored knowledge - keep XP, or a "recipe recall" book effect |
| 8 | Bronze | Sentry | Wakefulness | Sleepless vigils | Store: Drowsiness (Slowness in the day). Tap: no sleep needed - skip night, phantom-free |
| 9 | Chromium | Spinner | Fortune | Luck as a bankable attribute | Store: Bad Luck. Tap: Luck (loot rolls, mob drops, Infinite Dungeon rolls) |
| 10 | Nicrosil | Soulbearer | Investiture | The medallion enabler - stores raw Investiture | Store: your own abilities dim (all burns cost +x%). Tap: fuels ability-free casting - charge for other mods' magic (ISS interop candidate, OQ-10) |
| 11 | Aluminium | Trueself | Identity | Unkeyed metalmind enabler | Store: you feel hollow (brief debuff flavor). Tap: identity tools - make a metalmind unkeyed (M6 craft step), strip a hemalurgic spike's identity contamination |
| 12 | Duralumin | Connector | Connection | Bonds and relationships | Store: party systems forget you (brief). Tap: party-share buffs, quick-loot sharing radius, "resonance" with a bonded partner |
| 13 | Gold | Bloodmaker | Health | The great healer - Bloodmakers out-heal everything | Store: take double damage while storing. Tap: rapid Regeneration, undo stored damage - also the **spike removal** mechanic (canon: gold heals Hemalurgy, see `hemalurgy-inventory.md` section 2) |
| 14 | Electrum | Pinnacle | Determination | Willpower against compulsion | Store: fear (flee from mobs). Tap: immunity to allomantic zinc/brass and to mob fear effects |
| 15 | Cadmium | Gasper | Breath | Holds breath / gas reserves | Store: Hypoxia (drowning bar). Tap: extended water breathing (shared with allomantic sourcing below) |
| 16 | Bendalloy | Subsumer | Energy | Food and energy storage | Store: Hunger. Tap: instant saturation + sprint forever (shared with allomantic sourcing below) |
| - | Atium | (god metal) | Youth | The Lord Ruler stored youth and Compounded it into immortality | Speculative (OQ-11): store XP levels ("your years") for a profound tap effect - a full atiummind tap = brief invulnerability. Explicitly flagged as our invention |

**Era note:** cadmium and bendalloy are Era 2 discoveries that replaced
atium/malatium in the known sixteen; they appear in both the Feruchemical and
Allomantic tables, and the allomancy inventory covers their Allomantic side.

## 4. Beyond the sixteen

- **Unkeyed metalminds** (aluminium): craftable in M6 - a charged metalmind
  plus an aluminium "identity wash" makes it usable by any Feruchemist. The
  basis for party stash gear.
- **Unsealed metalminds / medallions** (nicrosil + duralumin): the endgame
  craft - a medallion grants the *ability itself* to whoever wears it. In
  pack terms: craftable class-tokens ("wear this to gain the Steelrunner
  Ferring"). The Bands of Mourning artefact - one of each metal, unsealed -
  is the natural Infinite Dungeon deepest-floor prize.
- **Compounding** (Twinborn, same metal both arts): if a player is both a
  Misting and a Ferring of the same metal, they can burn their own charged
  metalmind as allomantic fuel - one huge burst of the stored attribute
  (20x charge, one shot, metalmind emptied). The crown interaction of the
  whole repo; requires both arts live (M6+).
- **Lerasium / harmonium feruchemy**: canon-unknown. Parked - lerasium is
  already the Mistborn-granting bead in the allomancy scope; harmonium stays
  a tech-tier battery idea.

## 5. Sourcing delta vs the allomancy matrix

No new source metals - Feruchemy consumes the same sixteen (section 4 of
`allomancy-inventory.md`). The new items are **metalminds**: craftable
per-metal (metalmind ring/bracelet/blanket tiers by size = capacity
thresholds, canon-faithful), crafted from the same Base Metals / vanilla /
Tensura source ingots. Two Base Metals gaps carry over unchanged
(aluminium, chromium) plus the same two our-alloys (duralumin, nicrosil).

## 6. Implementation family map (which contracts each art touches)

| Piece | Contracts exercised |
|---|---|
| Storing toggles | data attachments on player + item NBT, sync events |
| Tapping abilities | `AbilityDefinition` + `AbilityModes` (tap rate), hold `ChargingModel` |
| Charge afford check | our own `ResourceProviders`/`ResourceConsumers` pair (`metallic_arts:metalmind`) - a *second* resource pair alongside the ley pair |
| Overlay | `AvailabilityProviders` charge metric, worn-item state |
| Medallions | `AbilityUnlocks.declare` (grant the ability itself), Curios slots |
| Compounding | cross-art check at burn time (both arts' state) |

## 7. Open questions this raises

- OQ-8 - feruchemy as a **sibling repo** (`feruchemy`, following the
  `ability-*` pattern) or a source set inside `metallic-arts`?
- OQ-9 - coppermind (memories): what is "memory" in MC terms - XP, recipes,
  or map-data?
- OQ-10 - nicrosil tapping as universal magic fuel: how far into ISS/Tensura
  interop do we let it reach (interop-only, opt-in)?
- OQ-11 - atium metalmind semantics (youth -> XP) are our invention, not
  canon - accept or park?
- OQ-12 - medallion power level: unsealed Ferrings for everyone, or
  guild-limited?

Living versions sit in `open-questions.md`.
