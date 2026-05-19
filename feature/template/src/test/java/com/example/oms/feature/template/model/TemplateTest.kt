package com.example.oms.feature.template.model

import com.google.gson.Gson
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

/**
 * Unit tests for the [Template] data class.
 */
class TemplateTest {

    private val gson = Gson()

    @Test
    fun `test template creation`() {
        // Given
        val id = "1"
        val name = "Test Template"
        val subject = "Test Subject"
        val body = "Test Body"
        val isDefault = true

        // When
        val template = Template(id, name, subject, body, isDefault)

        // Then
        assertNotNull(template)
        assertEquals(id, template.id)
        assertEquals(name, template.name)
        assertEquals(subject, template.subject)
        assertEquals(body, template.body)
        assertEquals(isDefault, template.isDefault)
    }

    @Test
    fun `test template default values`() {
        // Given
        val id = "2"
        val name = "Another Template"
        val subject = "Another Subject"
        val body = "Another Body"

        // When
        val template = Template(id, name, subject, body) // isDefault defaults to false

        // Then
        assertNotNull(template)
        assertEquals(id, template.id)
        assertEquals(name, template.name)
        assertEquals(subject, template.subject)
        assertEquals(body, template.body)
        assertEquals(false, template.isDefault) // default value
    }

    @Test
    fun `test template json serialization`() {
        // Given
        val template = Template(
            "3",
            "JSON Template",
            "JSON Subject",
            "JSON Body",
            true
        )

        // When
        val json = gson.toJson(template)
        val parsedTemplate = gson.fromJson(json, Template::class.java)

        // Then
        assertNotNull(parsedTemplate)
        assertEquals(template.id, parsedTemplate.id)
        assertEquals(template.name, parsedTemplate.name)
        assertEquals(template.subject, parsedTemplate.subject)
        assertEquals(template.body, parsedTemplate.body)
        assertEquals(template.isDefault, parsedTemplate.isDefault)
    }
}