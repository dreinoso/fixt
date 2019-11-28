package com.reactions.fixt.mvvm.di.home

import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.reactions.fixt.presentation.ui.features.home.HomeFragment

@Module
abstract class HomeFragmentModule {

    @ContributesAndroidInjector
    abstract fun homeFragment(): HomeFragment
}