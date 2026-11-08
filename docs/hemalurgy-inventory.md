# Hemalurgy inventory - the art of ruin, end to end

The third Metallic Art, as it exists in Brandon Sanderson's works (Mistborn
Era 1 and Era 2, plus its canonical reach into other Investitures), how spike
theft works end to end in canon, how we would adapt it into Minecraft, and
where its metals come from in the Tensura RPG pack. Companion to
`allomancy-inventory.md` and `feruchemy-inventory.md`.

Canon reference: the Hemalurgic table (Era 2 Ars Arcanum), the bind-point
table, the Law of Hemalurgic Decay and the Hemalurgic constructs. Unaffiliated
fan work - no novel text or art is reproduced; see `NOTICE`.

**Scope note:** Hemalurgy is future scope (post-M5, `roadmap.md`, OQ-8). It
is also the tonal edge of the repo - the dark art, balanced as such.

---

## 1. Canon end-to-end model (what we are adapting)

Hemalurgy is **end-negative**: power is stolen from one soul and spliced into
another, and some is always destroyed in the transfer.

1. **Charge the spike** - drive a metal spike through a victim at a **bind
   point** with **Intent**; the spike must touch moving blood (the name
   comes from it). Usually the victim dies; a thin spike, care and a
   Command can leave the victim alive and diminished.
2. **Rip it out and implant it** - the charged spike is embedded in the
   recipient at the right bind point, stapling the stolen piece of
   **spiritweb** into them. The metal determines *what category* is stolen;
   the bind point determines *what you get and where it fits* - there are
   200-300 bind points on a human body.
3. **The Law of Hemalurgic Decay** - outside a body, the charge decays on a
   logarithmic curve, most of it lost in the first day; a spike long out of
   a body is a spike in name only. Coating the spike in blood "tricks" it
   into believing it is still in a body and reduces decay; aluminium
   casing may stop it; the charge never fully reaches zero.
4. **The cost** - every spike tears holes in the recipient's spiritweb:
   body-warping (Inquisitors survive spikes through the eyes), mental
   damage past four spikes (mitigated by a steel **linchpin** spike), and
   holes that a Shard - or a strong enough Rioter/Soother - can whisper
   through, up to outright control. Post-Catacendre there is also a hard
   cap: too many spikes stop granting power entirely, and Compounders made
   by Hemalurgy are blocked.
5. **Access** - Hemalurgy needs no genetic gift: *anyone with the knowledge
   can do it*, which is exactly why it is feared. It also reaches across
   Investitures - canonically it can steal Breath, Surgebinding (hard),
   and more.
6. **Constructs** - repeating patterns of spikes create new kinds of being:
   **Steel Inquisitors** (9-11 spikes, steel eye-spikes giving iron/steel
   "sight", a linchpin holding the soul together), **koloss** (four iron
   spikes of human strength; intelligence sacrificed), **kandra** (mistwraiths
   given sapience by a **Blessing** - a *pair* of spikes of the same
   attribute: Potency (iron), Awareness (tin), Presence (copper), Stability
   (zinc)).
7. **Notable thefts** - atium spikes steal *any* power; lerasium spikes
   steal *all* abilities; a spike charged with Feruchemy carries the
   donor's Identity, so the recipient can open the *donor's metalminds*;
   gold Feruchemy can heal the hole Hemalurgy leaves.

## 2. Our end-to-end pipeline (the adaptation)

```
 craft a blank spike      charge                      implant
 same 16 source     -->  killing blow (or capture)   bind-point slot on self
 metals (ADR-004)        with the spike in hand  --> (right-click / curios)
                              |                          |
                    (metal x victim) ->                grants the stolen effect
                    what is stolen, ADR-006              at reduced potency
                              |
                    Law of Hemalurgic Decay
                    (decays toward a floor while carried;
                     blood-coating craft pauses it)
                              |
             spike count > 3  ->  "Ruin's Grip" I/II + whispers
```

Concretely:

- **Blank spikes** are crafted per metal from the same source ingots as
  everything else (ADR-004). Sixteen spike items, no worldgen.
- **Charging** requires a kill: land the killing blow with a blank spike in
  the offhand (or on a pseudocore-captured mob, interop idea) while holding
  intent (sneak-use on the corpse within seconds, to avoid accidental
  charges). The result is a **charged spike** whose stolen attribute is
  decided by `metal x victim` - a data table, datapack-overridable like the
  ability-core catalogs.
- **Implanting** maps canon bind points onto a small set of **slots** (OQ-13):
  eyes/head (helmets-adjacent), heart/chest, shoulders (blessings), upper
  arm, back (linchpin). Same metal, different slot, different result - the
  canon rule that "the art is knowing where to place the spikes", kept as a
  slot grid instead of 300 points.
- **Decay** is real: a charged spike loses potency tiers the longer it is
  carried unimplanted; wrapping it in **raw meat** (canon blood-coating) or
  an aluminium casing pauses decay. Implant freezes decay at its current
  tier. Fully-decayed spikes are craftable stock again (nothing left to
  steal).
- **The dark cost is mechanical, not cosmetic:** at 4+ implanted spikes you
  gain **Ruin's Grip I** (Weakness flash, periodic whisper toasts), at 6+
  **Ruin's Grip II** (occasional forced aggro pulses - you are *readable*
  by Seekers). A **linchpin spike** (steel, implanted in the back slot)
  suppresses one tier. Removal without gold Feruchemy destroys the power
  and applies a lingering debuff; a Bloodmaker's tap (or a medallion) heals
  the hole cleanly - the two arts interlocking, canon-faithful.
- **Stealing across systems** is the canon capability that makes this art
  interesting *for this pack*: nicrosil spikes stealing Investiture is the
  door to stealing an ISS spell slot or a Tensura skill (OQ-14 - consent,
  balance, and ADR-003 constraints; never reading anything we cannot reach
  through public APIs).

## 3. The sixteen metals - hemalurgic inventory

What each metal steals in canon (the Hemalurgic table), and what that means
for us. Victim tables are per-family (mobs grouped by trait: brutes, beasts,
casters, players); exact grants are datapack content.

| # | Metal | Canon steal | Our adaptation (steal from -> gain) |
|---|---|---|---|
| 1 | Iron | Human strength | Kill brutes/bosses -> flat melee damage + knockback resistance (the koloss pattern; 4+ iron spikes = engineered tank) |
| 2 | Steel | Physical Allomantic powers | Kill a metalburner -> one of their iron/steel-family abilities at decayed potency (the Inquisitor pattern) |
| 3 | Tin | Senses | Kill beasts -> permanent minor Night Vision / sound ping range |
| 4 | Pewter | Physical Feruchemical powers | Kill a Ferring -> their stored-attribute tap at reduced rate |
| 5 | Zinc | Emotional fortitude | Kill calm-resistant mobs -> resistance to allomantic zinc/brass and fear effects |
| 6 | Brass | Cognitive Feruchemical powers | Kill a Firesoul -> warmth/energy taps at reduced rate |
| 7 | Copper | Mental fortitude, memory, intelligence | Kill archivist-types -> XP retention on death; pair = kandra Blessing of Presence |
| 8 | Bronze | Mental Allomantic powers | Kill a Seeker/Smoker -> bronze-family sense at decayed potency; Inquisitor path to piercing copperclouds |
| 9 | Chromium | Might steal destiny | Rare oddball - kill a luck-blessed target -> tiny Fortune. Canon hedges ("might"); we keep it rare and weird |
| 10 | Nicrosil | Investiture | Kill any caster (ISS or Tensura, OQ-14) -> one cast of their magic, or fuel a medallion; also the construct-tier spike |
| 11 | Cadmium | Temporal Allomantic powers | Kill a slider/pulser-analogue -> a slowed/speeded bubble ability, heavily decayed |
| 12 | Bendalloy | Spiritual Feruchemical powers | Kill a Connector/Spiritual user -> a minor party-connect perk |
| 13 | Gold | Hybrid Feruchemical powers | Kill a Bloodmaker -> a one-shot self-heal charge (the "surgeon's spike") |
| 14 | Electrum | Enhancement Allomantic powers | Kill an enhancer -> a weakened nicrosil/duralumin-style boost |
| 15 | Aluminium | Removes all powers | Not a thief - an *eraser*: spike a recipient to strip a power (incl. your own spikes), spike an item to unkey a metalmind |
| 16 | Duralumin | Connection / Identity | Kill a bonded target -> borrow one party/faction perk temporarily; the Identity-theft tool |
| - | Atium | Any power (must be refined) | The endgame spike - one charge of *any* single stolen thing; single-slot, lerasium-scarce |
| - | Lerasium | All abilities | Not usable by mortals in canon terms - parked as quest artefact only |
| - | Harmonium / Trellium | Unknown | Parked (harmonium = tech battery idea, as in the allomancy doc; trellium = exotic boss material idea) |

## 4. Constructs as pack content (canon patterns, future scope)

The canon construct patterns translate directly into **crafted mobs/equipment
loops**, all post-M5 ideas:

- **Kandra Blessings** as Curios pairs: two same-attribute spikes worn in the
  shoulder slots grant the Blessing (Potency/Awareness/Presence/Stability).
  Cleanest first implementation of the whole art - pairs of items, no mob AI
  work.
- **Koloss analog**: a pseudocore-style captured brute given 4 iron spikes -
  a tamable/enhanced companion with a savagery tradeoff (interop with
  pseudocore, optional).
- **Inquisitor analog**: an Infinite Dungeon miniboss archetype - a
  hemalurgist NPC with eye-spikes that *sees through copperclouds* (punishes
  all-copper stealth parties) and drops a charged spike on death.
- The **Bands of Mourning**-style artefact belongs to the allomancy/feruchemy
  endgame (a full unsealed set), not hemalurgy; hemalurgy's artefact is the
  atium spike.

## 5. Sourcing delta vs the allomancy matrix

No new source metals. The new items are **blank spikes** (per metal, from the
same source ingots), **charged spikes** (state on the item, decayed tiers),
**blood coating** (raw-meat wrap craft) and **aluminium casing** (pauses
decay; possibly our aluminium, see the sourcing gap note OQ-2 in the
allomancy doc). Two ores, unchanged, are still the whole worldgen footprint.

## 6. Implementation family map (which contracts each art touches)

| Piece | Contracts exercised |
|---|---|
| Spike charging | kill loot hooks, offhand detection, datapack steal-tables |
| Implant effects | passive attribute providers (persistent, not abilities) |
| Decay | item tick + NBT tiers, pause via coating/casing |
| Spike count / Ruin's Grip | our own `EligibilityRule` contributions (a spiked player *is* eligible for control effects by design) |
| Removal / healing | gold feruchemy tap, or destroy-and-debuff path |
| Stolen abilities | registered as decayed-potency `AbilityDefinition`s via the same core contracts as everything else |

## 7. Open questions this raises

- OQ-13 - bind points: which slot grid (curios slots vs a fixed 5-point body
  map) carries the canon "where to place the spikes" depth?
- OQ-14 - cross-system theft (ISS spells, Tensura skills) via nicrosil/steel:
  in scope at all? consent/config tiers? ADR-003 interop-only constraints.
- OQ-15 - player-killing for spikes: hard-gated to consented PvP only, or
  mobs-only for v1?
- OQ-16 - does Hemalurgy live in this repo or a dark sibling (`hemalurgy`)?
  Same decision as OQ-8.

Living versions sit in `open-questions.md`.
