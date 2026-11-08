# ADR-001: Ley magic is the fuel, metals are the catalysts

Status: accepted

## Context

Canon Allomancers burn metal reserves directly: the metal *is* the fuel, so
a player carrying stacks of ingots is functionally infinite. Our frame has a
shared world resource anyway - Sky's Ley Lines gives the Overworld a
seed-based nexus network whose reserve mods draw on. If metals remain the
fuel, the mod ignores the one piece of world infrastructure the pack already
runs and the inventory trivialises balance.

## Decision

Metal reserves (allomancy bucket two) throttle *what* you can burn; the ley
currency (`metallic_arts:metal`, declared through ability-core's
`LeyLinesResources` bridge) is the actual fuel every burn draws. Nexuses
refill personal pools over time; away from the lines, you burn on fumes.
Flare multiplies both costs.

## Consequences

- Geography matters: dowsing rods, Ley Vision and the Ley Meter become
  meaningful to metalburners.
- The pack's shared magic map stays shared - we add one currency, not a
  parallel power system.
- Feruchemy deliberately does NOT follow this model (ADR-005).

Links: `allomancy-inventory.md` section 2, `design.md`, core #96 (the
bridge this decision is the first adopter of).
