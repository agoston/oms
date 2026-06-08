# OMS Build Instructions

## Prerequisites

- Android Studio Flamingo (2022.2.1) or later
- JDK 17
- Android SDK 34 (Android 14)
- Kotlin 1.9.0

## Step-by-Step Build Guide

### 1. Clone the Repository
```bash
git clone <repository-url>
cd oms
```

### 2. Set Up Local Properties
Create `local.properties` in the project root (see `local.properties.example`):
```properties
sdk.dir=/path/to/android/sdk
```

Use JDK 17 or 21 for Gradle. If your default `java` is newer (for example Java 26), set `JAVA_HOME` before building:
```bash
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
```

### 3. Install Dependencies
The project uses Gradle Wrapper. No additional installation needed.

### 4. Build the Application
```bash
# Debug build
./gradlew assembleDebug

# Release build
./gradlew assembleRelease
```

### 5. Run Tests
```bash
# Unit tests
./gradlew testDebugUnitTest

# Instrumentation tests (requires connected device or emulator)
./gradlew connectedDebugAndroidTest
```

### 6. Run the Application
```bash
# Install and run on connected device/emulator
./gradlew installDebug
./gradlew runDebug
```

### 7. Generate APK/AAB
```bash
# Debug APK
./gradlew assembleDebug

# Release AAB
./gradlew bundleRelease
```

## Project Structure
```
oms/
├app/                 # Application module
│├src/
││├main/
│││├java/com/example/oms/   # Kotlin source
│││└res/                  # Resources
││└test/                  # Unit tests
│└androidTest/            # Instrumentation tests
├core/                # Shared utilities
├feature/             # Feature modules (camera, template, email)
├data/                # Data layer
└di/                  # Dependency injection
```

## Troubleshooting

### Common Issues
1. **SDK not found**: Ensure `local.properties` points to valid Android SDK
2. **Java version mismatch**: Use JDK 17
3. **Build cache issues**: Run `./gradlew clean build`

### Getting Help
- Check Android Studio's Build > View Log
- Consult Gradle output for specific error messages
- Refer to Android Developer documentation

## CI/CD Integration
The project is configured for GitHub Actions. See `.github/workflows/build.yml` for details.

## License
[Apache License 2.0](LICENSE)