package com.reactions.fixt.mvvm.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import com.reactions.fixt.mvvm.MyApplication
import com.reactions.fixt.mvvm.di.home.HomeModule
import com.reactions.fixt.mvvm.di.main.GoogleMainModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ViewModelModule::class,
    AppModule::class,
    MainModule::class,
    HomeModule::class,
    GoogleMainModule::class
])
interface AppComponent : AndroidInjector<MyApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MyApplication>()
}