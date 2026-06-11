package com.example.oms.ui.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.oms.ui.screens.CameraScreen
import com.example.oms.ui.screens.EmailConfirmationScreen
import com.example.oms.ui.screens.SettingsScreen
import com.example.oms.ui.screens.TemplateEditorScreen
import com.example.oms.ui.screens.TemplateListScreen
import com.example.oms.ui.screens.TemplateSelectionScreen

@Composable
fun OmsAppNavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    Scaffold(modifier = modifier) {
        NavHost(
            navController = navController,
            startDestination = Routes.CAMERA,
            modifier = Modifier,
        ) {
            composable(Routes.CAMERA) {
                CameraScreen(
                    onCaptureClicked = { },
                    onRetakeClicked = { },
                    onUsePhotoClicked = { navController.navigate(Routes.TEMPLATE_SELECTION) },
                )
            }
            composable(Routes.TEMPLATE_SELECTION) {
                TemplateSelectionScreen(
                    onTemplateSelected = { _, _ -> navController.navigate(Routes.EMAIL_CONFIRMATION) },
                    onCreateNewTemplateClicked = { navController.navigate(Routes.TEMPLATE_EDITOR) },
                    onUseDefaultTemplateClicked = { navController.navigate(Routes.EMAIL_CONFIRMATION) },
                )
            }
            composable(Routes.EMAIL_CONFIRMATION) {
                EmailConfirmationScreen(
                    onSendEmailClicked = { navController.popBackStack(Routes.CAMERA, inclusive = false) },
                    onCancelClicked = { navController.popBackStack() },
                )
            }
            composable(Routes.SETTINGS) {
                SettingsScreen(
                    onTemplateManagementClicked = { navController.navigate(Routes.TEMPLATE_LIST) },
                    onSoundFeedbackChanged = { },
                    onVibrationFeedbackChanged = { },
                    onDefaultTemplateChanged = { },
                )
            }
            composable(Routes.TEMPLATE_LIST) {
                TemplateListScreen(
                    onTemplateClicked = { navController.navigate(Routes.TEMPLATE_EDITOR) },
                    onDeleteTemplateClicked = { },
                    onEditTemplateClicked = { navController.navigate(Routes.TEMPLATE_EDITOR) },
                )
            }
            composable(Routes.TEMPLATE_EDITOR) {
                TemplateEditorScreen(
                    onSaveClicked = { navController.popBackStack() },
                    onCancelClicked = { navController.popBackStack() },
                    initialTemplate = null,
                )
            }
        }
    }
}

object Routes {
    const val CAMERA = "camera"
    const val TEMPLATE_SELECTION = "template_selection"
    const val EMAIL_CONFIRMATION = "email_confirmation"
    const val SETTINGS = "settings"
    const val TEMPLATE_LIST = "template_list"
    const val TEMPLATE_EDITOR = "template_editor"
}
