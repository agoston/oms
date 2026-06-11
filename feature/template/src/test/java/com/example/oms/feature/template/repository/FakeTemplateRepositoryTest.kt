package com.example.oms.feature.template.repository

import com.example.oms.feature.template.model.Template
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class FakeTemplateRepositoryTest {

    private lateinit var repository: FakeTemplateRepository

    @Before
    fun setUp() {
        repository = FakeTemplateRepository()
    }

    @Test
    fun `save and get template by id`() = runTest {
        val template = Template("1", "Test", "Subject", "Body")

        repository.saveTemplate(template)

        assertEquals(template, repository.getTemplateById("1").first())
    }

    @Test
    fun `get all templates`() = runTest {
        val template1 = Template("1", "Test1", "Subject1", "Body1")
        val template2 = Template("2", "Test2", "Subject2", "Body2")

        repository.saveTemplate(template1)
        repository.saveTemplate(template2)

        val result = repository.getAllTemplates().first()
        assertEquals(2, result.size)
        assertTrue(result.contains(template1))
        assertTrue(result.contains(template2))
    }

    @Test
    fun `delete template`() = runTest {
        repository.saveTemplate(Template("1", "Test", "Subject", "Body"))

        repository.deleteTemplate("1")

        assertNull(repository.getTemplateById("1").first())
    }

    @Test
    fun `set and get default template`() = runTest {
        val template1 = Template("1", "Test1", "Subject1", "Body1")
        val template2 = Template("2", "Test2", "Subject2", "Body2")
        repository.saveTemplate(template1)
        repository.saveTemplate(template2)

        repository.setDefaultTemplate("2")

        assertEquals(template2, repository.getDefaultTemplate().first())
    }

    @Test
    fun `default template is null when unset`() = runTest {
        repository.saveTemplate(Template("1", "Test", "Subject", "Body"))

        assertNull(repository.getDefaultTemplate().first())
    }
}
