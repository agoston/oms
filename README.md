# OMS — One-Mail Snap

Android app: capture a photo, pick an email template, send via the system email client.

**This project is maintained by AI agents.** Start here, then follow links — do not read the whole tree at once.

## Quick start

```bash
# Requires system Gradle (9.5+) and JDK 17+. Do NOT add gradlew.
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64   # example; use JDK 17+ on your machine
gradle assembleDebug
gradle test
```

If the build fails with `HasConvention` or plugin errors, **bump versions in `gradle/libs.versions.toml`** — do not add Gradle Wrapper or downgrade Gradle.

## Documentation map

| Read first | Purpose |
|------------|---------|
| [AGENTS.md](AGENTS.md) | Rules for agents: build, Gradle, scope, pitfalls |
| [docs/ARCHITECTURE.md](docs/ARCHITECTURE.md) | Module layout, dependency rules, minimal manifest |
| [docs/DESIGN.md](docs/DESIGN.md) | Product flows, data models, v1 checklist |
| [docs/MODULES.md](docs/MODULES.md) | Index of per-module README files |

## Module index

| Module | README |
|--------|--------|
| `:app` | [app/README.md](app/README.md) |
| `:core` | [core/README.md](core/README.md) |
| `:feature:camera` | [feature/camera/README.md](feature/camera/README.md) |
| `:feature:template` | [feature/template/README.md](feature/template/README.md) |
| `:feature:email` | [feature/email/README.md](feature/email/README.md) |
| `:data:local` | [data/local/README.md](data/local/README.md) |
| `:data:remote` | [data/remote/README.md](data/remote/README.md) |

## Stack (2026)

- **UI:** Jetpack Compose, Material 3, single-Activity, Navigation Compose
- **Architecture:** UDF, ViewModel, Flow
- **DI:** Hilt (KSP), modules colocated per feature — no standalone `:di` module
- **Build:** AGP 9.x + system Gradle 9.5+, version catalog in `gradle/libs.versions.toml`
- **Storage (planned):** Proto DataStore in `:data:local`

## License

Apache License 2.0
