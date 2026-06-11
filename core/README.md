# :core

Shared kernel — **no UI, no feature logic**.

## Owns

- `AppSettings` — user preference model
- `util/Constants` — DataStore keys, request codes

## Depends on

Kotlin stdlib, coroutines (minimal).

## Must not contain

- Compose, ViewModels, Hilt modules (except pure utilities)
- Feature-specific code

## Agent tasks here

- Add shared domain types used by multiple modules
- Add extension functions / pure helpers

## Future

Move `Template` and repository interfaces here when `:data:local` owns persistence.
