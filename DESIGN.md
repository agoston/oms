# OMS App Design Document

## 1. Overview
OMS (One-Mail Snap) is a simple Android application that allows users to:
1. Take a photo using the device camera
2. Select a predefined, user-configurable email template
3. Send the photo as an attachment via the default email app using an Intent
4. Receive visual/audible feedback upon successful sending

The app emphasizes modularity, clean code, and modern Android best practices (2026 standards) to enable AI agent collaboration.

## 2. Architecture Goals
- **Modularity**: Clear separation of concerns to allow small agents to understand and modify individual modules
- **Agent-Friendly**: Medium agents can redesign module scopes and delegate subagent implementation
- **Vibe Coding**: Short, clean code with minimal boilerplate
- **Modern Standards**: Jetpack Compose, Material 3, Coroutines, Hilt, and Architecture Components

## 3. High-Level Module Structure
```
 oms/
├── app/                     # Application module (navigation, DI, resources)
├── core/                    # Shared utilities, constants, base classes
├── feature/
│   ├── camera/              # Camera capture functionality
│   ├── template/            # Template management and selection
│   └── email/               # Email sending via Intents
├── data/                    # Data layer (repositories, local/remote sources)
│   ├── local/               # Local data storage (DataStore, Room if needed)
│   └── remote/              # Remote data (none for v1, placeholder for future)
└── di/                      # Dependency injection modules
```

### Module Responsibilities:
- **app**: Hosts NavGraph, Application class, top-level resources
- **core**: Extension functions, constants, base classes, utility classes
- **feature.camera**: Camera preview, capture, permissions handling
- **feature.template**: Template data model, template list UI, template editing
- **feature.email**: Email composition via Intent, sending logic
- **data.local**: Persistent storage for templates and settings (using DataStore)
- **di**: Hilt modules for dependency injection

## 4. Data Models
### Template.kt
```kotlin
data class Template(
    val id: String,
    val name: String,
    val subject: String,
    val body: String,   // May contain placeholders like {date}, {time}
    val isDefault: Boolean = false
)
```

### AppSettings.kt
```kotlin
data class AppSettings(
    val defaultTemplateId: String? = null,
    val enableSoundFeedback: Boolean = true,
    val enableVibrationFeedback: Boolean = true
)
```

## 5. UI Flows
### 5.1 Main Flow (Camera Capture)
1. App launches to CameraScreen (full-screen camera preview)
2. Toolbar with:
   - Back button (disabled on first launch)
   - Settings icon (navigates to SettingsScreen)
   - Gallery icon (opens image picker - future enhancement)
3. Capture button (center bottom)
4. After photo capture:
   - Show preview of captured image
   - Options: "Retake" or "Use Photo"
   - On "Use Photo": navigate to TemplateSelectionScreen

### 5.2 Template Selection
1. TemplateSelectionScreen shows:
   - List of available templates (name and preview)
   - "Create New Template" button (navigates to TemplateEditorScreen)
   - "Use Default" button (if default template set)
2. On template selection:
   - Navigate to EmailConfirmationScreen (brief) or directly send email
   - Show sending progress
   - On success: show success snackbar + sound/vibration feedback
   - On error: show error snackbar

### 5.3 Settings Flow
1. SettingsScreen contains:
   - Template management section (navigates to TemplateListScreen)
   - Feedback settings (sound/vibration toggles)
   - About section
   - Default template selector

### 5.4 Template Management
- TemplateListScreen: Shows all templates with edit/delete options
- TemplateEditorScreen: Form to edit/create template (name, subject, body)
- Template preview shows how placeholders would be replaced

## 6. Technical Stack
- **Language**: Kotlin 1.9+
- **UI**: Jetpack Compose (Material 3)
- **Architecture**: Unidirectional Data Flow (UDF) with ViewModel
- **Async**: Kotlin Coroutines + Flow
- **DI**: Hilt
- **Storage**: Proto DataStore for settings/templates
- **Permissions**: Accompanied Permissions (or Activity Result APIs)
- **Camera**: CameraX (via Activity Result API for simplicity in v1)
- **Testing**: JUnit5, MockK, Turbine (for Flows), Compose Testing

## 7. Modularity Guidelines for Agent Work
### Small Agent Tasks (Single Module Understanding):
- Modify Template data model (add field, change validation)
- Update TemplateEditorScreen UI
- Change camera resolution settings
- Modify feedback logic (sound/vibration patterns)
- Add new template category field

### Medium Agent Tasks (Module Redesign + Delegation):
- Redesign template storage (from DataStore to Room)
- Refactor email sending to support multiple email clients
- Implement template synchronization (placeholder for remote)
- Redesign camera module to support video capture
- Implement template sharing feature

### Interface Contracts (for inter-module communication):
#### TemplateRepository Interface
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

#### EmailSender Interface
```kotlin
interface EmailSender {
    fun sendEmail(
        context: Context,
        recipient: String?,
        subject: String,
        body: String,
        attachmentUri: Uri
    )
}
```

### Dependency Boundaries:
- Feature modules depend only on core and data.local
- data.local depends on core
- app depends on all feature modules and di
- No circular dependencies allowed

## 8. Implementation Notes for Agents
### Code Style Principles:
- **Single Responsibility**: Each class/function has one clear purpose
- **Minimal Parameters**: Prefer data objects over long parameter lists
- **Immutable Data**: Use Kotlin data classes and immutable state
- **Early Returns**: Guard clauses over nested conditionals
- **Extension Functions**: For readable utility code
- **Null Safety**: Leverage Kotlin's type system
- **Constants**: Keep in core.util.Constants
- **Strings**: All UI strings in resources (for localization)

### Template for Feature Modules:
Each feature module should contain:
- `ui/`: Compose screens and components
- `viewmodel/`: ViewModels handling UI state
- `model/`: Data classes specific to feature
- `repository/`: Repository interface and implementation (if complex)
- `di/`: Hilt module for feature dependencies

## 9. Future Extension Points
- Template synchronization via remote server
- Support for multiple photos per email
- Direct SMTP sending (bypassing Intent)
- Template variables (date/time, location, custom fields)
- Batch template operations
- Dark/light theme auto-switching
- Accessibility improvements (talkback, switch access)

## 10. Success Criteria for v1
- [ ] Camera captures and saves photo temporarily
- [ ] Template list displays and allows selection
- [ ] Email Intent launches with correct attachment and template content
- [ ] Feedback mechanisms work (visual + auditory)
- [ ] Settings persist across app launches
- [ ] Module boundaries respected in code
- [ ] No memory leaks or crashes in core flow
- [ ] Compose preview works for all screens
- [ ] Unit tests for ViewModels and repositories
- [ ] Basic instrumentation tests for main flows

This design document provides sufficient detail for implementation while maintaining the flexibility for agent-driven development. The modular structure enables parallel work by different agents on separate features, with clear interfaces minimizing integration conflicts.
