# The Metallic Arts

An Allomancy-inspired metal magic mod for the Tensura RPG pack, built on Sky's
Ley Lines, Ability Core and the Ability Hotbar.

**Status: planning.** The scaffold compiles and CI is green; the game design is
being worked out in [`docs/`](docs/README.md) before serious code lands.

## The pitch

The world's ley lines are the power; metals are the key. Swallow a vial of
steel shavings, find a nexus where two ley lines cross, and *burn* it - the
metal shapes the ley magic flowing through you into a single raw effect:
push. Sixteen metals, sixteen effects, one shared reserve of power drawn
slowly from the world instead of generated from an inventory. Every burn is
an ordinary Ability Core ability, so metal powers sit in the same hotbar as
your Tensura skills, battlewills and Iron's Spells spells.

## What it will do (planned)

- Sixteen allomantic metals as burnable abilities - iron/steel pulls and
  pushes, tin/pewter sense and strengthen, zinc/brass riot and soothe,
  copper/bronze hide and seek, chromium/nicrosil drain and boost, aluminium
  and duralumin as the panic buttons.
- Power is drawn from Sky's Ley Lines: metals are catalysts, the ley network
  is the fuel, and nexuses refill a personal reserve over time.
- Awakening ("snapping") gates the first metal; a full sixteen-metal Mistborn
  endgame is the pack's capstone arc.
- Tensura: Reincarnated race gating and Iron's Spells interplay as optional
  integration layers - never required.

## Dependencies

| | Mod | Why |
|---|---|---|
| Required | Minecraft | — |
| Required | NeoForge | — |
| Planned optional | Ability Core | ability contracts, activation, costs |
| Planned optional | Ability Hotbar | the shared hotbar UI |
| Planned optional | Sky's Ley Lines | the ley/nexus power source |
| Later, optional | Iron's Spells 'n Spellbooks | spell interplay |
| Later, optional | Tensura: Reincarnated | race gating, pack tie-ins |

## Documentation

| Doc | Contents |
|---|---|
| [`docs/README.md`](docs/README.md) | index of the planning documents |
| [`docs/allomancy-inventory.md`](docs/allomancy-inventory.md) | every allomantic power end to end + each metal's pack source |
| [`docs/feruchemy-inventory.md`](docs/feruchemy-inventory.md) | the second art: storing, tapping, metalminds, Compounding |
| [`docs/hemalurgy-inventory.md`](docs/hemalurgy-inventory.md) | the third art: spike theft, bind points, decay, constructs |
| [`docs/brain-dump.md`](docs/brain-dump.md) | the raw idea dump - concepts, sparks, cut lines |
| [`docs/design.md`](docs/design.md) | the working design and requirements |
| [`docs/integrations.md`](docs/integrations.md) | per-mod integration notes against real APIs |
| [`docs/roadmap.md`](docs/roadmap.md) | milestones from planning to the first playable build |
| [`docs/open-questions.md`](docs/open-questions.md) | undecided questions and their options |
| [`docs/adr/`](docs/adr/) | decisions already taken, and why |

## Credits

- Built on [NeoForge](https://neoforged.net/).
- Power drawn from [Sky's Ley Lines](https://github.com/MinecraftModDevelopmentMods/SkysLeyLines).
- Abilities registered through [Ability Core](https://github.com/HexaHexes/ability-core),
  shown in [Ability Hotbar](https://github.com/HexaHexes/ability-hotbar).
- Inspired by Brandon Sanderson's Mistborn novels (Dragonsteel Entertainment) -
  an unaffiliated fan work, with no novel content included. See [`NOTICE`](NOTICE).

## License

GNU Lesser General Public License v2.1 — see [`LICENSE`](LICENSE).
Copyright (c) 2026 Bitwise Madness.

## AI disclosure

Parts of this project were written with the assistance of AI coding tools.
AI-assisted code, data and documentation ship only after human review by the
maintainers.
