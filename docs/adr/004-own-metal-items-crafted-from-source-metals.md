# ADR-004: Allomantic metals are our items, crafted from pack metals

Status: accepted

## Context

Canon metals must be ingested. The obvious mapping - swallow a
`basemetals:steel_ingot` - breaks in three ways: it consumes other mods'
items in a use they never intended (armor stock, machines, trades), it
gives no control over unit size or vial economy, and the metals Base Metals
does not carry (aluminium, chromium, cadmium) would need second-class
handling.

## Decision

The mod registers its own allomantic forms - flakes, vials, coins, and
later metalminds and spikes - crafted from the pack's source metals
(vanilla, Base Metals, Tensura grades) via datapack recipe data.
Raw ingots are never swallowable; they are crafting stock. This also gives
every art one shared item language (flakes -> reserves, metalminds ->
charges, blanks -> spikes).

## Consequences

- One item family to texture and localize; balance knobs (units per flake,
  per vial) are ours.
- Base Metals/Tensura remain pure suppliers - no dependency on their item
  behaviours beyond recipe ingredients.
- The M0 spike must pin exact source item ids for the recipe set.

Links: `allomancy-inventory.md` section 2 and 4 (sourcing matrix),
`feruchemy-inventory.md` section 5, `hemalurgy-inventory.md` section 5,
OQ-2, OQ-7.
