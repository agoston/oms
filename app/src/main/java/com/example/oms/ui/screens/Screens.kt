package com.example.oms.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.oms.R
import com.example.oms.feature.template.model.Template

@Composable
fun CameraScreen(
    onCaptureClicked: () -> Unit,
    onRetakeClicked: () -> Unit,
    onUsePhotoClicked: (String) -> Unit
) {
    ScreenScaffold(
        title = stringResource(R.string.screen_camera),
        primaryAction = stringResource(R.string.action_capture),
        onPrimaryAction = onCaptureClicked,
        secondaryAction = stringResource(R.string.action_use_photo),
        onSecondaryAction = { onUsePhotoClicked("content://oms/captured") },
        tertiaryAction = stringResource(R.string.action_retake),
        onTertiaryAction = onRetakeClicked
    )
}

@Composable
fun TemplateSelectionScreen(
    onTemplateSelected: (Template, String) -> Unit,
    onCreateNewTemplateClicked: () -> Unit,
    onUseDefaultTemplateClicked: () -> Unit
) {
    ScreenScaffold(
        title = stringResource(R.string.screen_template_selection),
        primaryAction = stringResource(R.string.action_select_template),
        onPrimaryAction = {
            onTemplateSelected(
                Template("default", "Default", "Photo", "Please see attached photo."),
                "content://oms/captured"
            )
        },
        secondaryAction = stringResource(R.string.action_create_template),
        onSecondaryAction = onCreateNewTemplateClicked,
        tertiaryAction = stringResource(R.string.action_use_default),
        onTertiaryAction = onUseDefaultTemplateClicked
    )
}

@Composable
fun EmailConfirmationScreen(
    onSendEmailClicked: () -> Unit,
    onCancelClicked: () -> Unit
) {
    ScreenScaffold(
        title = stringResource(R.string.screen_email_confirmation),
        primaryAction = stringResource(R.string.action_send_email),
        onPrimaryAction = onSendEmailClicked,
        secondaryAction = stringResource(R.string.action_cancel),
        onSecondaryAction = onCancelClicked
    )
}

@Composable
fun SettingsScreen(
    onTemplateManagementClicked: () -> Unit,
    onSoundFeedbackChanged: (Boolean) -> Unit,
    onVibrationFeedbackChanged: (Boolean) -> Unit,
    onDefaultTemplateChanged: (String?) -> Unit
) {
    ScreenScaffold(
        title = stringResource(R.string.screen_settings),
        primaryAction = stringResource(R.string.action_manage_templates),
        onPrimaryAction = onTemplateManagementClicked,
        secondaryAction = stringResource(R.string.action_enable_sound),
        onSecondaryAction = { onSoundFeedbackChanged(true) },
        tertiaryAction = stringResource(R.string.action_enable_vibration),
        onTertiaryAction = { onVibrationFeedbackChanged(true) }
    )
}

@Composable
fun TemplateListScreen(
    onTemplateClicked: (Template) -> Unit,
    onDeleteTemplateClicked: (String) -> Unit,
    onEditTemplateClicked: (Template) -> Unit
) {
    val sample = Template("1", "Sample", "Subject", "Body")
    ScreenScaffold(
        title = stringResource(R.string.screen_template_list),
        primaryAction = stringResource(R.string.action_edit_template),
        onPrimaryAction = { onEditTemplateClicked(sample) },
        secondaryAction = stringResource(R.string.action_open_template),
        onSecondaryAction = { onTemplateClicked(sample) },
        tertiaryAction = stringResource(R.string.action_delete_template),
        onTertiaryAction = { onDeleteTemplateClicked(sample.id) }
    )
}

@Composable
fun TemplateEditorScreen(
    onSaveClicked: (Template) -> Unit,
    onCancelClicked: () -> Unit,
    initialTemplate: Template?
) {
    val template = initialTemplate ?: Template(
        id = "new",
        name = "New Template",
        subject = "Photo",
        body = "Please see the attached photo."
    )
    ScreenScaffold(
        title = stringResource(R.string.screen_template_editor),
        primaryAction = stringResource(R.string.action_save),
        onPrimaryAction = { onSaveClicked(template) },
        secondaryAction = stringResource(R.string.action_cancel),
        onSecondaryAction = onCancelClicked
    )
}

@Composable
private fun ScreenScaffold(
    title: String,
    primaryAction: String,
    onPrimaryAction: () -> Unit,
    secondaryAction: String? = null,
    onSecondaryAction: (() -> Unit)? = null,
    tertiaryAction: String? = null,
    onTertiaryAction: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title)
        Button(onClick = onPrimaryAction) {
            Text(primaryAction)
        }
        if (secondaryAction != null && onSecondaryAction != null) {
            Button(onClick = onSecondaryAction) {
                Text(secondaryAction)
            }
        }
        if (tertiaryAction != null && onTertiaryAction != null) {
            Button(onClick = onTertiaryAction) {
                Text(tertiaryAction)
            }
        }
    }
}
