package com.andromesh.movieslistassignment.di

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class LegoAPI

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MovieAPI

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class CoroutineScropeIO

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class CoroutineScropeSupervisor

