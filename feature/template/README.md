# :feature:template

Email template selection and editing.

## Owns

- `model/Template`
- `repository/TemplateRepository`, `FakeTemplateRepository` (temporary in-memory impl)
- `viewmodel/TemplateViewModel`
- `di/TemplateModule` — Hilt binding
- Unit tests under `src/test/`

## Depends on

`:core`

## Agent tasks here

- Implement list / editor Compose UI in `ui/`
- Move repository impl to `:data:local` + DataStore; keep interface, update Hilt module
- Move screens from `:app` placeholder package

## Tests

```bash
gradle :feature:template:test
```
