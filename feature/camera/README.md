# :feature:camera

Camera capture feature.

## Owns

- `viewmodel/CameraViewModel` — capture / preview state (skeleton)
- (planned) `ui/` — CameraScreen composables
- (planned) `di/` — CameraX bindings

## Depends on

`:core` only (add `:data:*` if persisting captures).

## Agent tasks here

- Integrate CameraX or Activity Result capture
- Runtime `CAMERA` permission flow
- Move `CameraScreen` from `:app` when ready

## Public API

Expose composables and navigation callbacks; do not expose ViewModel to other features.
