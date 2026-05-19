package com.example.oms.feature.email

import android.content.Context
import android.net.Uri

/**
 * Interface for sending emails via Intent.
 *
 * See [Interface Contracts] in DESIGN.md for detailed contract.
 */
interface EmailSender {
    /**
     * Sends an email with the given parameters.
     *
     * @param context   The application context
     * @param recipient The email recipient (optional)
     * @param subject   The email subject
     * @param body      The email body
     * @param attachmentUri The URI of the attachment to include
     */
    fun sendEmail(
        context: Context,
        recipient: String?,
        subject: String,
        body: String,
        attachmentUri: Uri
    )
}