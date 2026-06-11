# Architecture

## Principles

1. **Single Activity** — `MainActivity` hosts Compose; all screens are composables.
2. **Minimal manifest** — only launcher Activity + Application + permissions. No feature components in XML.
3. **Modular Gradle projects** — each layer is a separate `:module` with its own `README.md`.
4. **Colocated DI** — Hilt modules live in the feature or data module they wire, not a global `:di` module.

## Module graph

```
                    ┌─────────┐
                    │  :app   │  NavHost, theme, MainActivity, app-wide Hilt entry
                    └────┬────┘
         ┌───────────────┼───────────────┐
         ▼               ▼               ▼
  :feature:camera  :feature:template  :feature:email
         │               │               │
         └───────────────┼───────────────┘
                         ▼
                    ┌─────────┐
                    │  :core  │  Shared models, constants, utilities
                    └────┬────┘
              ┌──────────┴──────────┐
              ▼                     ▼
        :data:local            :data:remote
        (DataStore, Room)      (future API — stub)
```

## Dependency rules

| Module | May depend on | Must not depend on |
|--------|---------------|-------------------|
| `:app` | all feature + data modules, `:core` | — |
| `:feature:*` | `:core`, `:data:*` (via interfaces) | other `:feature:*` modules |
| `:data:local` | `:core` | `:app`, `:feature:*` |
| `:data:remote` | `:core` | `:app`, `:feature:*` |
| `:core` | Kotlin stdlib, coroutines | Android UI, features, data |

**Target state:** repository **interfaces** in `:core` (or a future `:domain`), **implementations** in `:data:local`. The skeleton temporarily binds `FakeTemplateRepository` in `:feature:template` for compile/test; migrate to `:data:local` when implementing DataStore.

## Layer responsibilities

| Layer | Responsibility |
|-------|----------------|
| **app** | Application class, MainActivity, NavGraph, theme, string resources |
| **core** | `AppSettings`, `Constants`, shared domain types |
| **feature.*** | ViewModels, feature UI, feature-specific Hilt modules |
| **data.local** | DataStore / Room, repository implementations |
| **data.remote** | Retrofit/API clients (empty stub in v1) |

## Minimal manifest (agile / Jetpack)

Android still requires a manifest file, but this project keeps it **fixed and tiny**:

- One `<activity>` (`MainActivity`) with `MAIN` / `LAUNCHER`.
- One `<application android:name=".OmsApplication">` for Hilt.
- Permissions declared here; **requested at runtime** in feature code.

Everything else (navigation, deep links, FileProvider if needed) is added in Kotlin with explicit justification in the module README.

## Build configuration

- Version catalog: `gradle/libs.versions.toml` — **single source of truth** for AGP, Kotlin, libraries.
- Root `build.gradle`: plugin aliases only.
- No Gradle Wrapper — see [AGENTS.md](../AGENTS.md).

## Validity of this layout

This matches **modern modular Android** (Google’s recommended feature-module / clean layering):

- `:core` ≈ domain + shared kernel (keep it Android-light).
- `:data:*` = data layer isolated from UI.
- `:feature:*` = vertical slices by capability (camera, template, email).
- `:app` = composition root.

A standalone `:di` module is **intentionally omitted** — it becomes a god-module and hides ownership. Prefer `@Module` in the module that owns the bindings.

## Related docs

- Product flows and models: [DESIGN.md](DESIGN.md)
- Module index: [MODULES.md](MODULES.md)
