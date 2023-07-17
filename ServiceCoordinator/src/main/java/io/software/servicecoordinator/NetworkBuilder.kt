package io.software.servicecoordinator

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.software.functionalutilities.Constants
import io.software.servicecoordinator.service.MovieAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkBuilder
{
    @Singleton
    @Provides
    fun providesRetrofit() : Retrofit
    {
        val clientBuilder = OkHttpClient.Builder()
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientBuilder.build())
            .build()
    }

    @Singleton
    @Provides
    fun providesParrotAPI(retrofit: Retrofit) : MovieAPI{
        return retrofit.create(MovieAPI::class.java)
    }
}