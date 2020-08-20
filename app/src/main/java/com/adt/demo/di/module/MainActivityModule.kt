package com.adt.demo.di.module

import com.adt.demo.MainActivity
import dagger.Binds
import dagger.Module

@Module
abstract class MainActivityModule {

    @Binds
    internal abstract fun provideMainActivity(activity: MainActivity): MainActivity
}