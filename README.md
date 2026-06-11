# OMS (One-Mail Snap)

A simple Android application that allows users to take a photo and send it via email with a predefined template.

## Overview

OMS emphasizes clean code, modern Android best practices and above all,
modularity to enable AI agent collaboration.

## Features

- Take a photo using the device camera
- Select a predefined, user-configurable email template
- Send the photo as an attachment via the default email app
- Receive visual/audible feedback upon successful sending

## Architecture

- **Modularity**: Clear separation of concerns
- **Modern Standards**: Jetpack Compose, Material 3, Coroutines, Hilt
- **Module Structure**:
  - `app/`: Application module (navigation, DI, resources)
  - `core/`: Shared utilities, constants, base classes
  - `feature/`: Camera, template, email functionality
  - `data/`: Data layer (repositories, local/remote sources)
  - `di/`: Dependency injection modules

## Building the App

Run `gradle build`.

## Testing

Use gradle.

## License

[Apache License 2.0](LICENSE)