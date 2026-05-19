package com.example.oms.feature.template.repository

import com.example.oms.feature.template.model.Template
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectFirst
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.contrib.java.lang.system.TextFromStandardInputStream
import org.junit.contrib.java.lang.system.TextSystemStream

/**
 * Unit tests for the [FakeTemplateRepository].
 */
@ExperimentalCoroutinesApi
class FakeTemplateRepositoryTest {

    private lateinit var repository: FakeTemplateRepository

    @Before
    fun setUp() {
        repository = FakeTemplateRepository()
    }

    @Test
    fun `test save and get template`() = runTest {
        // Given
        val template = Template("1", "Test", "Subject", "Body", false)

        // When
        repository.saveTemplate(template)

        // Then
        val result = repository.getTemplateById("1").collectFirst()
        assertEquals(template, result)
    }

    @Test
    fun `test get all templates`() = runTest {
        // Given
        val template1 = Template("1", "Test1", "Subject1", "Body1", false)
        val template2 = Template("2", "Test2", "Subject2", "Body2", true)

        // When
        repository.saveTemplate(template1)
        repository.saveTemplate(template2)

        // Then
        val result = repository.getAllTemplates().collectFirst()
        assertEquals(2, result.size)
        assertTrue(result.contains(template1))
        assertTrue(result.contains(template2))
    }

    @Test
    fun `test delete template`() = runTest {
        // Given
        val template = Template("1", "Test", "Subject", "Body", false)
        repository.saveTemplate(template)

        // When
        repository.deleteTemplate("1")

        // Then
        val result = repository.getTemplateById("1").collectFirst()
        assertNull(result)
    }

    @Test
    fun `test set and get default template`() = runTest {
        // Given
        val template1 = Template("1", "Test1", "Subject1", "Body1", false)
        val template2 = Template("2", "Test2", "Subject2", "Body2", false)
        repository.saveTemplate(template1)
        repository.saveTemplate(template2)

        // When
        repository.setDefaultTemplate("2")

        // Then
        val result = repository.getDefaultTemplate().collectFirst()
        assertEquals(template2, result)
    }

    @Test
    fun `test get default template when none set`() = runTest {
        // Given
        val template = Template("1", "Test", "Subject", "Body", false)
        repository.saveTemplate(template)

        // When/Then
        val result = repository.getDefaultTemplate().collectFirst()
        assertNull(result)
    }
}