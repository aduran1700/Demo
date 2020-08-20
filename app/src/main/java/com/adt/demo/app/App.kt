package com.adt.demo.app

import com.adt.demo.di.component.ApplicationComponent
import com.adt.demo.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import dagger.android.HasAndroidInjector

class App : DaggerApplication(), HasAndroidInjector {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component: ApplicationComponent = DaggerApplicationComponent.builder().application(this).build()
        component.inject(this)

        return component
    }
}