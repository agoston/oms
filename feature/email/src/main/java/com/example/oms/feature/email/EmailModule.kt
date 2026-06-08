package com.example.oms.feature.email

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class EmailModule {

    @Binds
    @Singleton
    abstract fun bindEmailSender(sender: IntentEmailSender): EmailSender
}
