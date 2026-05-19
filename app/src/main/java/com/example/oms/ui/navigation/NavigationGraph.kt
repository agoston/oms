package com.example.oms.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.navArgument
import com.example.oms.ui.screens.CameraScreen
import com.example.oms.ui.screens.EmailConfirmationScreen
import com.example.oms.ui.screens.SettingsScreen
import com.example.oms.ui.screens.TemplateEditorScreen
import com.example.oms.ui.screens.TemplateListScreen
import com.example.oms.ui.screens.TemplateSelectionScreen

/**
 * Navigation graph for the OMS app.
 */
@OptIn(ExperimentalCoroutinesApi::class)
fun OmsAppNavGraph(
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier
) {
    val navController = rememberNavController()
    androidx.compose.material3.Scaffold(
        modifier = modifier,
        content = {
            androidx.navigation.compose.NavHost(
                navController = navController,
                startDestination = "camera"
            ) {
                composable("camera") {
                    CameraScreen(
                        onCaptureClicked = { /* Handle capture */ },
                        onRetakeClicked = { /* Handle retake */ },
                        onUsePhotoClicked = { imageUri -> /* Navigate to template selection with imageUri */ }
                    )
                }
                composable("template_selection") {
                    TemplateSelectionScreen(
                        onTemplateSelected = { template, imageUri -> /* Navigate to email confirmation */ },
                        onCreateNewTemplateClicked = { /* Navigate to template editor */ },
                        onUseDefaultTemplateClicked = { /* Use default template */ }
                    )
                }
                composable("email_confirmation") {
                    EmailConfirmationScreen(
                        onSendEmailClicked = { /* Send email */ },
                        onCancelClicked = { /* Go back */ }
                    )
                }
                composable("settings") {
                    SettingsScreen(
                        onTemplateManagementClicked = { /* Navigate to template list */ },
                        onSoundFeedbackChanged = { /* Update setting */ },
                        onVibrationFeedbackChanged = { /* Update setting */ },
                        onDefaultTemplateChanged = { /* Update default template */ }
                    )
                }
                composable("template_list") {
                    TemplateListScreen(
                        onTemplateClicked = { template -> /* Navigate to template editor */ },
                        onDeleteTemplateClicked = { templateId -> /* Delete template */ },
                        onEditTemplateClicked = { template -> /* Navigate to template editor */ }
                    )
                }
                composable("template_editor") {
                    TemplateEditorScreen(
                        onSaveClicked = { template -> /* Save template and go back */ },
                        onCancelClicked = { /* Go back without saving */ },
                        initialTemplate = /* Pass template if editing */ null
                    )
                }
            }
        }
    )
}