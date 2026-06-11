package com.example.oms.feature.template.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oms.feature.template.model.Template
import com.example.oms.feature.template.repository.TemplateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the template feature.
 * Handles the state of template list and selected template.
 */
@HiltViewModel
class TemplateViewModel @Inject constructor(
    private val templateRepository: TemplateRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        loadTemplates()
    }

    private fun loadTemplates() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            templateRepository.getAllTemplates().collect { templates ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        templates = templates
                    )
                }
            }
        }
    }

    fun onTemplateClicked(template: Template) {
        // In a real implementation, we would navigate to email confirmation or send email
        // For now, we just update the selected template
        _uiState.update { it.copy(selectedTemplate = template) }
    }

    fun onCreateNewTemplateClicked() {
        // Navigate to template editor
        _uiState.update { it.copy(isCreatingNew = true) }
    }

    fun onSaveTemplate(template: Template) {
        viewModelScope.launch {
            templateRepository.saveTemplate(template)
            loadTemplates() // Reload to get the updated list
            _uiState.update { it.copy(isCreatingNew = false) }
        }
    }

    fun onCancelCreateNewTemplate() {
        _uiState.update { it.copy(isCreatingNew = false) }
    }

    fun onDeleteTemplate(templateId: String) {
        viewModelScope.launch {
            templateRepository.deleteTemplate(templateId)
            loadTemplates()
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val templates: List<Template> = emptyList(),
        val selectedTemplate: Template? = null,
        val isCreatingNew: Boolean = false
    ) {
        companion object {
            val Loading = UiState(isLoading = true)
        }
    }
}