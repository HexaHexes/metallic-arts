# Documentation index

The Metallic Arts - planning documents. The repo is in **planning**: the
design is worked out here before code lands (the scaffold already compiles
and CI is green).

| Doc | Contents |
|---|---|
| [`allomancy-inventory.md`](allomancy-inventory.md) | **start here** - every allomantic power end to end, and each metal's source in the pack (vanilla / Base Metals / Tensura) |
| [`feruchemy-inventory.md`](feruchemy-inventory.md) | the second art: storing and tapping, metalminds, medallions, Compounding |
| [`hemalurgy-inventory.md`](hemalurgy-inventory.md) | the third art: spike theft, bind points, decay, constructs |
| [`brain-dump.md`](brain-dump.md) | raw ideas not yet folded into the design - sparks, wild cards, cut lines |
| [`design.md`](design.md) | the working design: resource models per art, progression, requirements |
| [`integrations.md`](integrations.md) | per-mod integration notes against the real APIs (ability-core, Ley Lines, ISS, Tensura, ...) |
| [`roadmap.md`](roadmap.md) | milestones M0-M6+ with acceptance criteria |
| [`open-questions.md`](open-questions.md) | every open question (OQ-1...), with options and a recommendation |
| [`adr/`](adr/) | decisions already taken, and why (ADR-001...) |
| [`mixin-rules.md`](mixin-rules.md) | org mixin rules (from the template) |

## Reading order for a new contributor

1. `../README.md` - the pitch and dependencies.
2. `allomancy-inventory.md` sections 1-2 - the canon model and our pipeline.
3. `design.md` - the three arts, three resource models.
4. `roadmap.md` - what lands when.
5. `open-questions.md` - where help is needed.

## Conventions

- Docs use OQ-NN for open questions and ADR-NNN for decisions; both are
  referenced from the inventories and live in their own files.
- Canon claims come from the published tables (Ars Arcanum) and are kept
  separate from our inventions - every invention is marked as ours.
- Nothing here reproduces novel text or art; attribution lives in `../NOTICE`.
