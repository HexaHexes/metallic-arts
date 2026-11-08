# ADR-005: Feruchemy is end-neutral - metalminds are its only resource

Status: accepted (for the feruchemy scope, pre-M6)

## Context

ADR-001 makes ley power Allomancy's fuel. Feruchemy in canon is end-neutral:
nothing comes from outside the user, nothing is lost - attributes are moved
through time at the cost of being deprived of them while storing. Wiring
metalmind taps into the ley currency would flatten the one economy that
makes the second art feel different, and would contradict the canon
storing-is-the-cost loop the design wants to preserve.

## Decision

Metalmind charge is Feruchemy's only resource. Storing toggles apply the
deprivation debuffs and charge the worn/held metalmind; tapping spends that
charge; the compression curve (tap faster, lose more) is implemented as the
tap-rate ability-mode cost curve. No ley interaction, no vials, no unlock
currency beyond gaining the Ferring ability itself.

## Consequences

- Feruchemy works anywhere - deep dungeon, other dimensions, nexus-drought
  regions - which positions it as the pack's "explorer's art".
- A second resource pair (`metallic_arts:metalmind`) joins the ley pair in
  core's provider/consumer declarations; the two must not be cross-charged.
- Compounding (M6+) is the single sanctioned bridge between the economies -
  burn a *self-charged* metalmind as allomantic fuel, canon-faithful.

Links: ADR-001 (the contrast), `feruchemy-inventory.md` sections 1-2,
REQ-MA-10.
