package com.example.oms.feature.template.model

import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class TemplateTest {

    private val gson = Gson()

    @Test
    fun `test template creation`() {
        val template = Template("1", "Test Template", "Test Subject", "Test Body", true)

        assertNotNull(template)
        assertEquals("1", template.id)
        assertEquals("Test Template", template.name)
        assertEquals("Test Subject", template.subject)
        assertEquals("Test Body", template.body)
        assertEquals(true, template.isDefault)
    }

    @Test
    fun `test template default values`() {
        val template = Template("2", "Another Template", "Another Subject", "Another Body")

        assertEquals(false, template.isDefault)
    }

    @Test
    fun `test template json serialization`() {
        val template = Template("3", "JSON Template", "JSON Subject", "JSON Body", true)

        val json = gson.toJson(template)
        val parsedTemplate = gson.fromJson(json, Template::class.java)

        assertNotNull(parsedTemplate)
        assertEquals(template, parsedTemplate)
    }
}
