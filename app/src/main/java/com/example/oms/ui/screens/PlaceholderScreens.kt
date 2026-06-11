package com.example.oms.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.oms.feature.template.model.Template

/** Placeholder screens until feature UI is implemented. */

@Composable
fun CameraScreen(
    onCaptureClicked: () -> Unit,
    onRetakeClicked: () -> Unit,
    onUsePhotoClicked: (String) -> Unit,
) {
    PlaceholderScreen("Camera")
}

@Composable
fun TemplateSelectionScreen(
    onTemplateSelected: (Template, String) -> Unit,
    onCreateNewTemplateClicked: () -> Unit,
    onUseDefaultTemplateClicked: () -> Unit,
) {
    PlaceholderScreen("Template selection")
}

@Composable
fun EmailConfirmationScreen(
    onSendEmailClicked: () -> Unit,
    onCancelClicked: () -> Unit,
) {
    PlaceholderScreen("Email confirmation")
}

@Composable
fun SettingsScreen(
    onTemplateManagementClicked: () -> Unit,
    onSoundFeedbackChanged: (Boolean) -> Unit,
    onVibrationFeedbackChanged: (Boolean) -> Unit,
    onDefaultTemplateChanged: (String?) -> Unit,
) {
    PlaceholderScreen("Settings")
}

@Composable
fun TemplateListScreen(
    onTemplateClicked: (Template) -> Unit,
    onDeleteTemplateClicked: (String) -> Unit,
    onEditTemplateClicked: (Template) -> Unit,
) {
    PlaceholderScreen("Template list")
}

@Composable
fun TemplateEditorScreen(
    onSaveClicked: (Template) -> Unit,
    onCancelClicked: () -> Unit,
    initialTemplate: Template?,
) {
    PlaceholderScreen("Template editor")
}

@Composable
private fun PlaceholderScreen(label: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = label)
    }
}
