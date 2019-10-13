package com.code.injection.module

import com.code.network.SearchApi
import com.rhyscoronado.codingchallenge.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideDeliveryApi(retrofit: Retrofit): SearchApi {
        return retrofit.create(SearchApi::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://itunes.apple.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(getHttpClientBuilder())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())).build()
    }

    private fun getHttpClientBuilder(): OkHttpClient {
        val client = OkHttpClient().newBuilder()
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            client.addInterceptor(interceptor)
        }
        client.readTimeout(60, TimeUnit.SECONDS)
        client.connectTimeout(60, TimeUnit.SECONDS)
        return client.build()
    }
}