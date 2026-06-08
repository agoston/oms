package com.example.oms.feature.template.repository

import com.example.oms.feature.template.model.Template
import kotlinx.coroutines.flow.Flow

/**
 * Repository for managing email templates.
 *
 * See [Interface Contracts] in DESIGN.md for detailed contract.
 */
interface TemplateRepository {
    /** Get all templates as a flow. */
    fun getAllTemplates(): Flow<List<Template>>

    /** Get a template by its ID as a flow. */
    fun getTemplateById(id: String): Flow<Template?>

    /** Save a template. */
    fun saveTemplate(template: Template)

    /** Delete a template by its ID. */
    fun deleteTemplate(id: String)

    /** Set the default template. */
    fun setDefaultTemplate(id: String)

    /** Get the default template as a flow. */
    fun getDefaultTemplate(): Flow<Template?>
}