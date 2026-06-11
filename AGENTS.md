# Agent instructions

**Read this before changing build files or project structure.**

## Build system — non‑negotiable

| Rule | Detail |
|------|--------|
| **No Gradle Wrapper** | Do not run `gradle wrapper`, do not add `gradlew`, `gradlew.bat`, or `gradle/wrapper/`. The environment provides **system Gradle 9.5+**. |
| **Build command** | Always `gradle <task>` from repo root (e.g. `gradle assembleDebug`, `gradle test`). |
| **Version bumps** | If Gradle/AGP/Kotlin mismatch errors appear, edit **`gradle/libs.versions.toml`** only. Compatible set: Gradle 9.5+, AGP 9.1+, Kotlin 2.3+, Hilt 2.59+, KSP 2.3.6+ (catalog uses `2.3.9`). |
| **Do not downgrade Gradle** | Never install an older Gradle or wrapper to “fix” the build. |
| **JDK** | Use JDK 17 or 21. Set `JAVA_HOME` if the default JVM is too new (e.g. JDK 26). |

### Common failure → fix

| Symptom | Wrong fix | Correct fix |
|---------|-----------|-------------|
| `org/gradle/api/internal/HasConvention` | Add gradlew / older Gradle | Bump AGP to 9.x and Hilt to 2.59+ in `libs.versions.toml` |
| Plugin not found for AGP 8.x | Search for gradlew | Upgrade AGP + Kotlin in catalog |
| Hilt / KSP errors on AGP 9 | Re-add `kotlin.android` plugin | Remove `org.jetbrains.kotlin.android`; AGP 9 has built-in Kotlin. Use `kotlin.compose` + KSP. |

## Scope discipline

1. **One module per task** — read that module’s `README.md` first.
2. **Respect dependency direction** — see [docs/ARCHITECTURE.md](docs/ARCHITECTURE.md).
3. **No cross-feature imports** — features talk through `:core` contracts or `:data:*` repositories.
4. **Placeholder UI lives in `:app`** until a feature screen is ready to move into its feature module.

## Manifest policy

- **Minimal static manifest** in `app/src/main/AndroidManifest.xml`: one launcher `Activity`, `Application` class, required `<uses-permission>` only.
- **No** feature Activities, Services, or Receivers in XML.
- Navigation = Compose Navigation; permissions = runtime APIs in feature code.

## File layout inside a feature module

```
feature/<name>/
  README.md
  build.gradle
  src/main/java/com/example/oms/feature/<name>/
    ui/           # Compose (when implemented)
    viewmodel/
    model/        # Feature-specific types only
    repository/   # Interface if not in :core/:data
    di/           # Hilt @Module for this feature
```

## Testing

```bash
gradle :feature:template:test          # unit tests in one module
gradle test                            # all unit tests
gradle :app:connectedDebugAndroidTest  # device/emulator required
```

## When stuck

1. Run `gradle assembleDebug --stacktrace` once; read the **first** cause.
2. Check [docs/MODULES.md](docs/MODULES.md) for the module you are editing.
3. Do not add new top-level modules without updating `settings.gradle`, `docs/MODULES.md`, and `docs/ARCHITECTURE.md`.
