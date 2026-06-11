package com.example.oms.feature.template.repository

import com.example.oms.feature.template.model.Template
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

/**
 * In-memory [TemplateRepository] for the skeleton build.
 * Replace with a DataStore-backed implementation in `:data:local`.
 */
@Singleton
class FakeTemplateRepository @Inject constructor() : TemplateRepository {
    private val _templates = MutableStateFlow(emptyList<Template>())
    private val templates: StateFlow<List<Template>> = _templates.asStateFlow()
    private var defaultTemplateId: String? = null

    override fun getAllTemplates(): Flow<List<Template>> = templates

    override fun getTemplateById(id: String): Flow<Template?> =
        templates.map { list -> list.firstOrNull { it.id == id } }

    override fun saveTemplate(template: Template) {
        _templates.update { currentList ->
            val index = currentList.indexOfFirst { it.id == template.id }
            if (index >= 0) {
                currentList.toMutableList().apply { this[index] = template }
            } else {
                currentList + template
            }
        }
    }

    override fun deleteTemplate(id: String) {
        _templates.update { currentList -> currentList.filterNot { it.id == id } }
        if (defaultTemplateId == id) {
            defaultTemplateId = null
        }
    }

    override fun setDefaultTemplate(id: String) {
        defaultTemplateId = id
    }

    override fun getDefaultTemplate(): Flow<Template?> =
        templates.map { list -> list.firstOrNull { it.id == defaultTemplateId } }
}
