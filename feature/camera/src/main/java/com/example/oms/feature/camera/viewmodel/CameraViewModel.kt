package com.example.oms.feature.camera.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the camera feature.
 * Handles the state of the camera preview and captured image.
 */
@HiltViewModel
class CameraViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(UiState.Ready)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun onCaptureClicked() {
        viewModelScope.launch {
            _uiState.update { it.copy(isCapturing = true) }
            // Simulate camera capture delay
            delay(500)
            // In a real implementation, we would use CameraX or Activity Result API
            // For now, we simulate a captured image URI
            val fakeImageUri = "content://fake/image/uri"
            _uiState.update {
                it.copy(
                    isCapturing = false,
                    capturedImageUri = fakeImageUri,
                    showPreview = true
                )
            }
        }
    }

    fun onRetakeClicked() {
        _uiState.update {
            it.copy(
                capturedImageUri = null,
                showPreview = false
            )
        }
    }

    fun onUsePhotoClicked() {
        // In a real implementation, we would navigate to the template selection screen
        // For now, we just reset the state
        _uiState.update {
            it.copy(
                capturedImageUri = null,
                showPreview = false
            )
        }
    }

    data class UiState(
        val isCapturing: Boolean = false,
        val capturedImageUri: String? = null,
        val showPreview: Boolean = false
    ) {
        companion object {
            val Ready = UiState()
        }
    }
}