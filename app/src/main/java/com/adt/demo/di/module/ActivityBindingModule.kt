package com.adt.demo.di.module

import com.adt.demo.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class, MainFragmentBindingModule::class])
    internal abstract fun bindMainActivity() : MainActivity
}