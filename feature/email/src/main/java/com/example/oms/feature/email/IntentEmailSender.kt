package com.example.oms.feature.email

import android.content.Context
import android.content.Intent
import android.net.Uri
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IntentEmailSender @Inject constructor() : EmailSender {

    override fun sendEmail(
        context: Context,
        recipient: String?,
        subject: String,
        body: String,
        attachmentUri: Uri
    ) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "message/rfc822"
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, body)
            putExtra(Intent.EXTRA_STREAM, attachmentUri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            if (!recipient.isNullOrBlank()) {
                putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
            }
        }
        val chooser = Intent.createChooser(intent, null).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(chooser)
    }
}
