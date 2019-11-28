package com.reactions.fixt.mvvm.di

import com.reactions.fixt.presentation.ui.features.fixtures.FixturesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.reactions.fixt.presentation.ui.features.home.HomeFragment
import com.reactions.fixt.presentation.ui.features.main.MainFragment
import com.reactions.fixt.presentation.ui.features.results.ResultsFragment

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract fun mainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun fixturesFragment(): FixturesFragment

    @ContributesAndroidInjector
    abstract fun resultsFragment(): ResultsFragment
}