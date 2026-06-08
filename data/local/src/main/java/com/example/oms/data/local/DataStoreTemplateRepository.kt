package com.example.oms.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.oms.core.util.Constants
import com.example.oms.feature.template.model.Template
import com.example.oms.feature.template.repository.TemplateRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

private val Context.templateDataStore: DataStore<Preferences> by preferencesDataStore(
    name = Constants.TEMPLATE_DATASTORE_NAME
)

@Singleton
class DataStoreTemplateRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: Gson
) : TemplateRepository {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val templatesKey = stringPreferencesKey(Constants.TEMPLATES_JSON_KEY)
    private val defaultTemplateIdKey = stringPreferencesKey(Constants.DEFAULT_TEMPLATE_ID_KEY)

    override fun getAllTemplates(): Flow<List<Template>> =
        context.templateDataStore.data.map { preferences ->
            decodeTemplates(preferences[templatesKey])
        }

    override fun getTemplateById(id: String): Flow<Template?> =
        getAllTemplates().map { templates -> templates.firstOrNull { it.id == id } }

    override fun saveTemplate(template: Template) {
        scope.launch {
            context.templateDataStore.edit { preferences ->
                val current = decodeTemplates(preferences[templatesKey]).toMutableList()
                val index = current.indexOfFirst { it.id == template.id }
                if (index >= 0) {
                    current[index] = template
                } else {
                    current.add(template)
                }
                preferences[templatesKey] = gson.toJson(current)
            }
        }
    }

    override fun deleteTemplate(id: String) {
        scope.launch {
            context.templateDataStore.edit { preferences ->
                val updated = decodeTemplates(preferences[templatesKey]).filterNot { it.id == id }
                preferences[templatesKey] = gson.toJson(updated)
                if (preferences[defaultTemplateIdKey] == id) {
                    preferences.remove(defaultTemplateIdKey)
                }
            }
        }
    }

    override fun setDefaultTemplate(id: String) {
        scope.launch {
            context.templateDataStore.edit { preferences ->
                preferences[defaultTemplateIdKey] = id
            }
        }
    }

    override fun getDefaultTemplate(): Flow<Template?> =
        context.templateDataStore.data.map { preferences ->
            val defaultId = preferences[defaultTemplateIdKey] ?: return@map null
            decodeTemplates(preferences[templatesKey]).firstOrNull { it.id == defaultId }
        }

    private fun decodeTemplates(json: String?): List<Template> {
        if (json.isNullOrBlank()) {
            return emptyList()
        }
        val type = object : TypeToken<List<Template>>() {}.type
        return gson.fromJson(json, type) ?: emptyList()
    }
}
