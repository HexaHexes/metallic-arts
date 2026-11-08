# Open questions

Every undecided question, numbered OQ-1 onward, referenced from the design
and inventory docs. Each carries options and a recommendation; decisions get
an ADR and are struck through here.

| # | Question | Options | Recommendation |
|---|---|---|---|
| OQ-1 | How does snapping unlock the first metal? | (a) near-death event at a nexus; (b) questline beat; (c) level/EP threshold | (a), with (c) as the server config fallback - trauma + place is the canon feel and needs no quest dependency for v1 |
| OQ-2 | Sourcing for aluminium, chromium, cadmium (the BM gaps) | (a) three small OreSpawn ores; (b) trader/loot only; (c) substitute other metals | (a) - three ores total is a small worldgen footprint and gives the metals overworld presence; verify BM 3.10 does not already add any of them (M0 spike) |
| OQ-3 | Which MMD interop asks do we make for Ley Lines? | coppercloud hides Ley Vision; chromium siphons nexus fields; bronze gets nexus bearings | Ask all three at once, all marked optional; ship each as no-op until answered |
| OQ-4 | Emotional allomancy on players | (a) off by default; (b) consent-list; (c) full PvP | (a) default with (b) available per server - the pack is co-op-leaning |
| OQ-5 | Tin/pewter stacking with Tensura body skills and ISS buffs | (a) highest-wins; (b) additive caps; (c) no rules | (a) highest-wins per attribute, revisit after the pack's balance pass |
| OQ-6 | Burn grades from Tensura materials: v1 flat or graded from the start | (a) flat v1, grades in M4; (b) graded from M2 | (a) - the inventory stays the whole surface until the core loop is fun |
| OQ-7 | Vial granularity | (a) one vial per metal; (b) canon multi-metal vials | (a) for v1 - clearer overlay math; (b) is a QoL craft later |
| OQ-8 | Feruchemy and Hemalurgy: sibling repos or source sets here? | (a) `feruchemy`/`hemalurgy` siblings like the `ability-*` family; (b) source sets in this repo; (c) single repo, gated feature flags | Decide at M5 exit against real codebase weight; lean (b) if compile times stay healthy, (a) if the arts grow their own interop |
| OQ-9 | Coppermind "memory" analogue | (a) XP levels; (b) recipe knowledge; (c) map data | (a) - XP is the only clean, lossy-resistance store in MC; flag as our invention |
| OQ-10 | Nicrosil tap as universal magic fuel (ISS/Tensura) | (a) our-abilities-only; (b) ISS spells via public API; (c) everything | (a) for v1; (b) only if ISS's API exposes a clean cast-fuel seam |
| OQ-11 | Atium metalmind semantics (youth -> XP) | accept our invention / park atium feruchemy | Park - atium is allomancy-endgame in this pack first |
| OQ-12 | Medallion power level (unsealed Ferrings) | (a) anyone can craft; (b) dungeon/quest loot only; (c) guild/claim-limited | (b) - medallions are endgame party gear, not a substitute for snapping |
| OQ-13 | Hemalurgic bind points | (a) curios slots; (b) fixed 5-point body map; (c) 300-point depth | (b) for readability, (a) as the *wearing* layer - slot grid of ~5 with per-slot results |
| OQ-14 | Cross-system spike theft (ISS spells, Tensura skills) | (a) out of scope; (b) mobs-only degraded grants; (c) full theft with consent | (b) at M6 - degraded grants (a weakened version) avoid both balance and consent cliffs; revisit (c) never without (b) shipping first |
| OQ-15 | Player-killing for spikes | (a) mobs-only v1; (b) consented PvP; (c) always | (a) -> (b) with OQ-4's config |
| OQ-16 | (merged into OQ-8) | - | - |
| OQ-17 | Does the coppercloud hide occupants from *our* bronze only, or from all detection mods? | ours-only / all | ours-only in v1; the MMD ask (OQ-3) decides the rest |
| OQ-18 | Coin item: metal ammunition or reusable? | consumable coin / recyclable | consumable - mass rule + economy both want it cheap and gone |

## Decision log cross-references

Decisions already taken live in `adr/` and are referenced from the docs;
this file holds only the *undecided*. When an OQ resolves: write the ADR
(if it is a durable choice), strike the row, note the ADR number in place
of the recommendation.
