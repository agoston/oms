package com.example.oms.ui.navigation

import androidx.compose.foundation.layout.padding
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
    Scaffold(modifier = modifier) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "camera",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("camera") {
                CameraScreen(
                    onCaptureClicked = {},
                    onRetakeClicked = {},
                    onUsePhotoClicked = { navController.navigate("template_selection") }
                )
            }
            composable("template_selection") {
                TemplateSelectionScreen(
                    onTemplateSelected = { _, _ -> navController.navigate("email_confirmation") },
                    onCreateNewTemplateClicked = { navController.navigate("template_editor") },
                    onUseDefaultTemplateClicked = { navController.navigate("email_confirmation") }
                )
            }
            composable("email_confirmation") {
                EmailConfirmationScreen(
                    onSendEmailClicked = { navController.popBackStack("camera", inclusive = false) },
                    onCancelClicked = { navController.popBackStack() }
                )
            }
            composable("settings") {
                SettingsScreen(
                    onTemplateManagementClicked = { navController.navigate("template_list") },
                    onSoundFeedbackChanged = {},
                    onVibrationFeedbackChanged = {},
                    onDefaultTemplateChanged = {}
                )
            }
            composable("template_list") {
                TemplateListScreen(
                    onTemplateClicked = { navController.navigate("template_editor") },
                    onDeleteTemplateClicked = {},
                    onEditTemplateClicked = { navController.navigate("template_editor") }
                )
            }
            composable("template_editor") {
                TemplateEditorScreen(
                    onSaveClicked = { navController.popBackStack() },
                    onCancelClicked = { navController.popBackStack() },
                    initialTemplate = null
                )
            }
        }
    }
}
