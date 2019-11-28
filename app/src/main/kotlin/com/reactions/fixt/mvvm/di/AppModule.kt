package com.reactions.fixt.mvvm.di

import android.content.Context
import android.content.res.Resources
import dagger.Binds
import dagger.Module
import dagger.Provides
import com.reactions.fixt.mvvm.MyApplication
import com.reactions.fixt.mvvm.di.qualifier.ApplicationContext
import javax.inject.Singleton

@Module(includes = [NetModule::class, DbModule::class])
abstract class AppModule {

    @ApplicationContext
    @Binds
    abstract fun provideApplicationContext(myApplication: MyApplication): Context

    @Module
    companion object {
        @JvmStatic
        @Provides
        @Singleton
        fun provideAppResources(context: Context): Resources {
            return context.resources
        }
    }
}