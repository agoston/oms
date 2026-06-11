package com.example.oms.feature.template.di

import com.example.oms.feature.template.repository.FakeTemplateRepository
import com.example.oms.feature.template.repository.TemplateRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TemplateModule {

    @Binds
    @Singleton
    abstract fun bindTemplateRepository(
        impl: FakeTemplateRepository
    ): TemplateRepository
}
