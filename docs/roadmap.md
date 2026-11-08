# Roadmap

Milestones with acceptance criteria. The repo is at **M0**. Gametest counts
grow with the milestones (CI contract: `expectedTests` in the same commit).

## M0 - planning (this commit)

- [x] Repo created from `neoforge-mod-template`; rename checklist complete.
- [x] The three art inventories written from the canon tables.
- [x] Design, integrations, brain dump, open questions, seed ADRs.
- [ ] M0 spike: dump Base Metals 3.10 NeoForge jar registry to pin item ids;
      same pass over `tensura` material item ids from the pack jar.
- [ ] Repo pushed; CI green on the renamed scaffold.

Exit: CI green, docs reviewed, the four open questions that gate M1
(OQ-1..4) decided in their files.

## M1 - the steel spike (first end-to-end burn)

One metal, the whole pipeline: `basemetals:steel` -> vial -> reserve
attachment -> hotbar activation -> ley cost charged -> push impulse with the
mass rule -> overlay draining. Datapack-defined cost.

- Gametests: mod loads (existing), steel push applies impulse; mass rule
  moves self vs target correctly (REQ-MA-03).

Exit: a player can burn steel in a dev world and the hotbar shows it.

## M2 - the basic eight + the world

Iron/steel/tin/pewter/zinc/brass/copper/bronze; flakes and vials; per-metal
reserves; the `metallic_arts:metal` ley pair live with nexus refill;
snapping prototype (config-selected model, OQ-1); consent config for
emotional allomancy (OQ-4).

- Worldgen lands: aluminium + chromium + cadmium ores (REQ-MA-08) - all
  three gaps closed here even though cadmium's burn is M3.

Exit: a Misting can play the full basic-eight loop, on foot and from a
vial.

## M3 - enhancement + temporal (+ Era 2 pair)

Aluminium, duralumin, chromium, nicrosil, gold, electrum, cadmium, bendalloy.
Duralumin interaction with active burns; copper/bronze aura pair; anchored
bubbles. Atium/malatium stay out (M4).

Exit: all eighteen burnables registered; the two-bucket overlay is honest
in every state; decay/drag side effects present.

## M4 - the pack weave

Atium (hihiirokane craft) and malatium; lerasium bead as Mistborn unlock;
Tensura race eligibility rule (config-off); awakening questline hook with
tensura-questlines; malatium/Jade analysis tie-in; burn grades (OQ-6) if
decided.

Exit: the endgame loop exists in-pack: snap -> Misting -> dungeon lerasium
-> Mistborn, with god-metal burns as the finisher.

## M5 - the interop pass

ISS bronze-pulse read, optional chromium/nicrosil touches behind the MMD and
ISS API conversations (OQ-3/OQ-10); Ley Vision hiding if MMD agrees; the
`ability-*` style versioned dependency cut over to required.

Exit: no interop gap left that blocks publishing the mod's first release
alpha.

## M6+ - the sibling arts (scope decision pending OQ-8)

Feruchemy MVP: four metalminds (iron, pewter, gold, steel) - store/tap, the
compression curve, curios wearing (REQ-MA-10). Then Hemalurgy MVP: iron and
steel spikes with decay and Ruin's Grip (REQ-MA-11). Then Compounding, then
medallions, then constructs - each its own milestone slice, each gated on
the sibling-repo-vs-source-set decision.

## Standing rules across milestones

- One milestone = one PR train; version-bump CI owns the version line.
- Any new `@GameTest` updates `expectedTests` in the same commit.
- Interop code always lands behind the `ModList` gate with the bridge-class
  pattern (agents/REPO.md gotchas).
- Balance numbers live in datapacks, not code, from M1 onward.
