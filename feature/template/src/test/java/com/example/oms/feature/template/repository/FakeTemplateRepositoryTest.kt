package com.example.oms.feature.template.repository

import com.example.oms.feature.template.model.Template
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class FakeTemplateRepositoryTest {

    private lateinit var repository: FakeTemplateRepository

    @Before
    fun setUp() {
        repository = FakeTemplateRepository()
    }

    @Test
    fun `test save and get template`() = runTest {
        val template = Template("1", "Test", "Subject", "Body", false)

        repository.saveTemplate(template)

        val result = repository.getTemplateById("1").first()
        assertEquals(template, result)
    }

    @Test
    fun `test get all templates`() = runTest {
        val template1 = Template("1", "Test1", "Subject1", "Body1", false)
        val template2 = Template("2", "Test2", "Subject2", "Body2", true)

        repository.saveTemplate(template1)
        repository.saveTemplate(template2)

        val result = repository.getAllTemplates().first()
        assertEquals(2, result.size)
        assertTrue(result.contains(template1))
        assertTrue(result.contains(template2))
    }

    @Test
    fun `test delete template`() = runTest {
        val template = Template("1", "Test", "Subject", "Body", false)
        repository.saveTemplate(template)

        repository.deleteTemplate("1")

        val result = repository.getTemplateById("1").first()
        assertNull(result)
    }

    @Test
    fun `test set and get default template`() = runTest {
        val template1 = Template("1", "Test1", "Subject1", "Body1", false)
        val template2 = Template("2", "Test2", "Subject2", "Body2", false)
        repository.saveTemplate(template1)
        repository.saveTemplate(template2)

        repository.setDefaultTemplate("2")

        val result = repository.getDefaultTemplate().first()
        assertEquals(template2, result)
    }

    @Test
    fun `test get default template when none set`() = runTest {
        val template = Template("1", "Test", "Subject", "Body", false)
        repository.saveTemplate(template)

        val result = repository.getDefaultTemplate().first()
        assertNull(result)
    }
}
