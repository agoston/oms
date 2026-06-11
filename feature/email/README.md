# :feature:email

Send photo + template via system email Intent.

## Owns

- `EmailSender` interface
- (planned) `IntentEmailSender` implementation
- (planned) `di/EmailModule`

## Depends on

`:core`

## Agent tasks here

- Implement `EmailSender` with `ACTION_SEND` Intent + `FileProvider` if needed
- If FileProvider is required, add **only** the provider entry to manifest with comment — prefer sharing via content URI from `:data:local`

## Must not

- Import other `:feature:*` modules
