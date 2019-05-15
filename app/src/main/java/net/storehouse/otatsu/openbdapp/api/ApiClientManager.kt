package net.storehouse.otatsu.openbdapp.api

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

open class ApiClientManager {
    companion object {
        private const val BASE_URL = "https://api.openbd.jp/v1/"
        private val TAG = ApiClientManager::class.simpleName

        val apiClient: ApiClient
            get() = Retrofit.Builder()
                    .client(getClient())
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(Gson()))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build()
                    .create(ApiClient::class.java)

        private fun getClient(): OkHttpClient {
            return OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
        }
    }
}