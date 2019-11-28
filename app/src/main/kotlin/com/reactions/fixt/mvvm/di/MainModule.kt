package com.reactions.fixt.mvvm.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.reactions.fixt.mvvm.di.home.HomeFragmentModule
import com.reactions.fixt.presentation.ui.features.main.MainActivity

@Module(includes = [FragmentsModule::class])
abstract class MainModule {

    //@PerActivity
    @ContributesAndroidInjector
    abstract fun get(): MainActivity
}