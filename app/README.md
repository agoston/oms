# :app

Application module — **composition root**.

## Owns

- `OmsApplication` — `@HiltAndroidApp`
- `MainActivity` — single Activity, Compose content
- `ui/navigation/` — NavHost, route constants
- `ui/theme/` — Material 3 theme
- `ui/screens/` — placeholder composables until moved into feature modules
- `AndroidManifest.xml` — minimal (see [docs/ARCHITECTURE.md](../docs/ARCHITECTURE.md))
- App resources (`strings.xml`, launcher icon)

## Depends on

All `:feature:*`, `:data:*`, and `:core`.

## Agent tasks here

- Wire navigation between feature screens
- Move a screen from `ui/screens/` into its `:feature:*` module when implemented
- Add instrumentation / Compose UI tests

## Build

```bash
gradle :app:assembleDebug
```
