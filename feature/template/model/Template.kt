package com.example.oms.feature.template.model

/**
 * Data class representing an email template.
 *
 * @param id Unique identifier for the template
 * @param name Display name of the template
 * @param subject Email subject line
 * @param body Email body content, may contain placeholders like {date}, {time}
 * @param isDefault Whether this is the default template
 */
data class Template(
    val id: String,
    val name: String,
    val subject: String,
    val body: String,
    val isDefault: Boolean = false
)