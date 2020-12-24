package com.andromesh.movieslistassignment.di

import android.app.Application
import com.andromesh.movieslistassignment.BuildConfig
import com.andromesh.movieslistassignment.api.AuthInterceptor
import com.andromesh.movieslistassignment.api.MoviesService
import com.andromesh.movieslistassignment.database.AppDatabase
import com.andromesh.movieslistassignment.movies.data.MovieRemoteDataSource
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, CoreDataModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideMovieService(@MovieAPI okhttpClient: OkHttpClient,
                            converterFactory: GsonConverterFactory
    ) = provideService(okhttpClient, converterFactory, MoviesService::class.java)

    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(moviesService: MoviesService) = MovieRemoteDataSource(moviesService)


    @MovieAPI
    @Provides
    fun providePrivateOkHttpClient(
            upstreamClient: OkHttpClient
    ): OkHttpClient {
        return upstreamClient.newBuilder()
                .addInterceptor(AuthInterceptor(BuildConfig.API_KEY)).build()
    }

    @Singleton
    @Provides
    fun provideDb(app: Application) = AppDatabase.getInstance(app)

    @Singleton
    @Provides
    fun provideLegoSetDao(db: AppDatabase) = db.moviesDao()


    @CoroutineScropeIO
    @Provides
    fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)

    @CoroutineScropeSupervisor
    @Provides
    fun provideCoroutineScropeSupervisor() = CoroutineScope(SupervisorJob())


    private fun createRetrofit(
            okhttpClient: OkHttpClient,
            converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
                .baseUrl(MoviesService.ENDPOINT)
                .client(okhttpClient)
                .addConverterFactory(converterFactory)
                .build()
    }

    private fun <T> provideService(okhttpClient: OkHttpClient,
                                   converterFactory: GsonConverterFactory, clazz: Class<T>): T {
        return createRetrofit(okhttpClient, converterFactory).create(clazz)
    }
}
