package com.adt.demo.di.module

import com.adt.demo.ui.headlineslist.HeadlinesListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBindingModule {

    @ContributesAndroidInjector
    internal abstract fun provideHeadlinesListFragment(): HeadlinesListFragment
}