package com.example.oms.feature.template.repository

import com.example.oms.feature.template.model.Template
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

/**
 * Fake implementation of TemplateRepository for testing and prototyping.
 * Uses in-memory mutable state flow.
 */
class FakeTemplateRepository : TemplateRepository {
    private val _templates = MutableStateFlow(emptyList<Template>())
    val templates: StateFlow<List<Template>> = _templates.asStateFlow()
    private var _defaultTemplateId: String? = null

    override fun getAllTemplates(): Flow<List<Template>> = templates

    override fun getTemplateById(id: String): Flow<Template?> =
        templates.map { it.firstOrNull { it.id == id } }

    override fun saveTemplate(template: Template) {
        _templates.update { currentList ->
            val index = currentList.indexOfFirst { it.id == template.id }
            if (index >= 0) {
                currentList.also { it[index] = template }
            } else {
                currentList + template
            }
        }
    }

    override fun deleteTemplate(id: String) {
        _templates.update { currentList ->
            currentList.filterNot { it.id == id }
        }
        if (_defaultTemplateId == id) {
            _defaultTemplateId = null
        }
    }

    override fun setDefaultTemplate(id: String) {
        _defaultTemplateId = id
    }

    override fun getDefaultTemplate(): Flow<Template?> =
        templates.map { it.firstOrNull { it.id == _defaultTemplateId } }
}