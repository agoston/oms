package com.example.oms.data.local

import com.example.oms.feature.template.repository.TemplateRepository
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataModule {

    @Binds
    @Singleton
    abstract fun bindTemplateRepository(
        repository: DataStoreTemplateRepository
    ): TemplateRepository

    companion object {
        @Provides
        @Singleton
        fun provideGson(): Gson = Gson()
    }
}
