package com.tcs.sample.cleanmvvm.di

import com.google.gson.Gson
import com.tcs.sample.cleanmvvm.Constants
import com.tcs.sample.cleanmvvm.data.remote.ApiService
import com.tcs.sample.cleanmvvm.data.remote.ProductsRepositoryImpl
import com.tcs.sample.cleanmvvm.data.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().client(okHttpClient).baseUrl(Constants.PRODUCT_BASE_URL).addConverterFactory(
            GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun provideDefaultOkhttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder().callTimeout(30, TimeUnit.SECONDS).connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS).addInterceptor(loggingInterceptor).build()
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Singleton
    @Provides
    fun provideApiServices(retrofitClient: Retrofit): ApiService {
        return retrofitClient.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAppRepository(
        api: ApiService
    ): RemoteDataSource {
        return ProductsRepositoryImpl(api)
    }
}