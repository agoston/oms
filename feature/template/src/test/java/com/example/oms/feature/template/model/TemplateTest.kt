package com.example.oms.feature.template.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class TemplateTest {

    @Test
    fun `template creation preserves fields`() {
        val template = Template(
            id = "1",
            name = "Test Template",
            subject = "Test Subject",
            body = "Test Body",
            isDefault = true,
        )

        assertNotNull(template)
        assertEquals("1", template.id)
        assertEquals("Test Template", template.name)
        assertEquals("Test Subject", template.subject)
        assertEquals("Test Body", template.body)
        assertEquals(true, template.isDefault)
    }

    @Test
    fun `isDefault defaults to false`() {
        val template = Template("2", "Name", "Subject", "Body")

        assertEquals(false, template.isDefault)
    }
}
