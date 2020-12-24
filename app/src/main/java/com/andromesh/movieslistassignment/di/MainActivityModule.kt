package com.andromesh.movieslistassignment.di


import com.andromesh.movieslistassignment.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])

    abstract fun contributeMainActivity(): MainActivity

}
