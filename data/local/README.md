# :data:local

On-device persistence.

## Owns

- (planned) DataStore for templates + settings
- (planned) `TemplateRepository` implementation
- (planned) `di/DataLocalModule`

## Depends on

`:core` only

## Agent tasks here

- Replace `FakeTemplateRepository` with DataStore-backed repo
- Bind implementation in Hilt module here; remove binding from `:feature:template`

## Must not

- Depend on `:feature:*` or `:app`
