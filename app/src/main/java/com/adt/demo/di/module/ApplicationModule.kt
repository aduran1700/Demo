package com.adt.demo.di.module

import com.adt.demo.api.HeadlineApi
import com.adt.demo.data.repository.HeadlineRepository

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun provideHeadlineRepository(api: HeadlineApi) = HeadlineRepository(api)
}