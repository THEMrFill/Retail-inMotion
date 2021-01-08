package com.retailinmotion.philip.arnold.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.retailinmotion.philip.arnold.BuildConfig
import com.retailinmotion.philip.arnold.network.ApiNetwork
import com.retailinmotion.philip.arnold.repo.ForecastRepository
import com.retailinmotion.philip.arnold.repo.ForecastRepositoryImpl
import com.retailinmotion.philip.arnold.repo.TimeRepository
import com.retailinmotion.philip.arnold.repo.TimeRepositoryImpl
import com.retailinmotion.philip.arnold.ui.main.MainViewModel
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

val appModules = module {
    // The Retrofit service using our custom HTTP client instance as a singleton
    single {
        createWebService(
            okHttpClient = createHttpClient(),
            baseUrl = BuildConfig.API_BASE_URL
        )
    }
    // factories for Koin
    single<ForecastRepository> { ForecastRepositoryImpl(get()) }
    single<TimeRepository> { TimeRepositoryImpl() }

    // viewModel for Koin
    viewModel { MainViewModel(get(), get()) }
}

/* Returns a custom OkHttpClient instance with interceptor. Used for building Retrofit service */
fun createHttpClient(): OkHttpClient {
    val client = OkHttpClient.Builder()
    client.readTimeout(5 * 60, TimeUnit.SECONDS)
    return client.addInterceptor {
        val original = it.request()
        val requestBuilder = original.newBuilder()
        requestBuilder.header("Content-Type", "text/xml")
        val request = requestBuilder.method(original.method(), original.body()).build()
        return@addInterceptor it.proceed(request)
    }.build()
}

/* function to build our Retrofit service */
fun createWebService(
    okHttpClient: OkHttpClient,
    baseUrl: String
): ApiNetwork {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(TikXmlConverterFactory.create())
        .client(okHttpClient)
        .build()
    return retrofit.create(ApiNetwork::class.java)
}