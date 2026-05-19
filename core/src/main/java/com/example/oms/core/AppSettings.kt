package com.example.oms.core

/**
 * Data class representing application settings.
 *
 * @param defaultTemplateId ID of the default template to use
 * @param enableSoundFeedback Whether to play sound on successful email send
 * @param enableVibrationFeedback Whether to vibrate on successful email send
 */
data class AppSettings(
    val defaultTemplateId: String? = null,
    val enableSoundFeedback: Boolean = true,
    val enableVibrationFeedback: Boolean = true
)