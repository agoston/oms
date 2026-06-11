# Design — OMS v1

Product and UX specification. For structure and Gradle rules see [ARCHITECTURE.md](ARCHITECTURE.md) and [../AGENTS.md](../AGENTS.md).

## Overview

OMS (One-Mail Snap) lets users:

1. Capture a photo with the camera
2. Choose a predefined email template (user-configurable)
3. Send the photo as an attachment via the default email app (Intent)
4. Get visual / sound / vibration feedback on success

## Data models

### Template

```kotlin
data class Template(
    val id: String,
    val name: String,
    val subject: String,
    val body: String,   // placeholders: {date}, {time}
    val isDefault: Boolean = false,
)
```

Location: `feature/template/.../model/Template.kt` (move to `:core` when `:data:local` implements persistence).

### AppSettings

```kotlin
data class AppSettings(
    val defaultTemplateId: String? = null,
    val enableSoundFeedback: Boolean = true,
    val enableVibrationFeedback: Boolean = true,
)
```

Location: `core/.../AppSettings.kt`.

## Repository contracts

### TemplateRepository

```kotlin
interface TemplateRepository {
    fun getAllTemplates(): Flow<List<Template>>
    fun getTemplateById(id: String): Flow<Template?>
    fun saveTemplate(template: Template)
    fun deleteTemplate(id: String)
    fun setDefaultTemplate(id: String)
    fun getDefaultTemplate(): Flow<Template?>
}
```

### EmailSender

```kotlin
interface EmailSender {
    fun sendEmail(
        context: Context,
        recipient: String?,
        subject: String,
        body: String,
        attachmentUri: Uri,
    )
}
```

## UI flows

### Main — camera

1. Launch → `CameraScreen` (full-screen preview)
2. Toolbar: settings, (future: gallery)
3. Capture → preview → Retake / Use Photo
4. Use Photo → `TemplateSelectionScreen`

Routes: see `app/.../navigation/Routes.kt`.

### Template selection

- List templates, create new, use default
- Select → send or confirm on `EmailConfirmationScreen`
- Success → snackbar + sound/vibration; error → snackbar

### Settings

- Template management → list / editor
- Feedback toggles
- Default template picker

## Technical stack

| Area | Choice |
|------|--------|
| Language | Kotlin 2.3+ (via AGP built-in) |
| UI | Compose Material 3 |
| Architecture | UDF + ViewModel |
| Async | Coroutines + Flow |
| DI | Hilt + KSP |
| Persistence | Proto DataStore in `:data:local` (planned) |
| Camera | CameraX or Activity Result (v1) |
| Tests | JUnit, MockK, coroutines-test; Compose UI tests in `:app` |

## Agent task sizing

**Small (single module):** change Template fields, editor UI, camera resolution, feedback toggles.

**Medium (module + delegation):** DataStore migration, CameraX integration, email client chooser, move screens from `:app` into feature modules.

## v1 success criteria

- [ ] Camera capture → temp file URI
- [ ] Template list + selection
- [ ] Email Intent with attachment + template body
- [ ] Feedback on success
- [ ] Settings persist (DataStore)
- [ ] Module boundaries respected
- [ ] Unit tests for ViewModels / repositories
- [ ] `gradle assembleDebug` and `gradle test` pass

## Future (out of v1 scope)

Remote template sync, multi-photo, SMTP, rich template variables, accessibility pass.
