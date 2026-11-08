# Brain dump - raw ideas, sparks, wild cards

Everything here is unreviewed input. Good ideas graduate into `design.md`
(and, when they are decisions, an ADR); bad ideas die here quietly. Roughly
in the order the sparks arrived. Nothing in this file is a commitment.

## The core adaptation sparks (now load-bearing - see design/ADRs)

- Ley lines as Preservation: the ley network is the world's Investiture; metals
  are keyed catalysts. This one idea solves the fuel problem, the geography
  problem, and the "why can't I carry 10 stacks of iron and be a god" problem.
  -> ADR-001.
- Every metal is an ordinary ability-core ability. No bespoke hotbar, no bespoke
  activation. -> ADR-002.
- Flares are ability modes, not a second power set. -> design.md.
- Our own flake/vial/spike items, crafted from pack metals - never swallow raw
  ingots. -> ADR-004.

## Progression ideas

- **Snapping** candidates (OQ-1): (a) near-death at a nexus triggers the
  awakening event - trauma + place, very canon; (b) a questline beat
  (tensura-questlines) with a scripted "break" moment; (c) pure config -
  first metal unlocks at a level/EP threshold. Lean: (a) with (c) as the
  server knob.
- Misting first, Mistborn last (lerasium bead). Twinborn = Misting + Ferring
  later; the Compounder combo is the capstone (feruchemy-inventory section 4).
- Southern Scadrian arc: medallions as craftable class tokens; the Bands of
  Mourning as the pack's final artifact - one of each unsealed metalmind.
- Era names as pack eras: "Era 1" gameplay = Final Empire vibes (mist, ash,
  nobility?) - reskin opportunity only, no scope.

## Combat and synergy sparks

- Coin item (our allomantic coin): cheap steel/iron ammunition - the mod's
  iconic movement toy. Coinshot flight = push coin + push self, the canon
  physics loop.
- Iron/steel mass rule as *the* skill expression: pushing a wall sends you
  flying; pushing a coin sends it flying. Newton is the balance lever.
- Pewter drag as a real mechanic: soak damage, pay it back with interest
  when the burn ends. High-skill, canon-faithful.
- Atium duels: two seers see each other's shadows - implement as mutual
  dodge halving; electrum splits shadows. PVP rule, off by default.
- Cadmium/bendalloy anchored bubbles: build a "field generator" block later
  (Southern Scadrian tech direction) - stationary, battery-fed (ettmetal
  battery idea), Creative-flavor for pack builds.
- Zinc/brass on boss mobs: cheap, because bosses resist - a % instead of a
  hard override.

## Pack integration sparks

- Bronze Seeker "tweak" (canon says Feruchemy needs an unknown tweak to be
  bronze-visible - perfect excuse): our bronze pings ley nexuses and, if
  interop lands, active ISS spellcasts as metal-like pulses.
- Malatium vision = temporary Great Sage analysis window (Jade) - reading a
  mob's "echoes". Ties into tensura-greatsage-growth's analysis family.
- Hemalurgy + pseudocore: spike a *captured* mob (pseudocore holds the data)
  instead of killing it - "humane" spike farming, and a great crossover.
- Koloss as pseudocore-companions; Inquisitor archetype as an Infinite
  Dungeon miniboss that pierces copperclouds.
- Duralumin (Connection) metalmind taps: quick-loot party radius, faction
  capital standing bump - Connection = standing in Tensura terms.
- Chromium leech on a nexus field: siphon ley into your pool - needs MMD API
  check (OQ-3), would make chromium the PvE-relevant enhancement metal.
- Coppercloud hides the party from Ley Vision pings - sneaking past other
  metalburners on a shared ley map. Needs the same MMD conversation.
- Atium flakes from hihiirokane: the pack's final ore is the god metal. The
  Lord Ruler's atium stash = a capital-vault story beat?

## Wild cards (probably never, but written down)

- Feruchemical coppermind as a literal book item: store a "memory" by
  snapping a map/cartographer table; tap to get the knowledge back. XP is
  simpler (OQ-9).
- Aluminum "identity wash" as a server-admin tool: strip a stolen spike from
  a griefer's build server-side.
- Ettmetal reactor block for infinite-dungeon rooms: ley-fed trap that
  explodes on water contact (canon-faithful hazard).
- Kandra companion: pseudocore + 2 blessing spikes + mistwraith-analogue mob
  = shapeshifting pet. Huge scope; park until everything else ships.
- Era 3 vibes: computers + metal magic = none of our business for this pack.

## Cut lines (explicitly out of scope)

- No Feruchemy/Hemalurgy in v1 - allomancy first, whole arts later (OQ-8).
- No worldgen beyond three ores (aluminium, chromium, cadmium - OQ-2).
- No novel text, names of book characters, or book art anywhere - system
  inspiration only (NOTICE).
- No mixins into ARR upstreams (ADR-003) - interop-only, always.
