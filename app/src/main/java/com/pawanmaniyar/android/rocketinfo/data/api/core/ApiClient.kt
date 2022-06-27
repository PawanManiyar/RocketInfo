package com.pawanmaniyar.android.rocketinfo.data.api.core

import com.pawanmaniyar.android.rocketinfo.BuildConfig
import com.pawanmaniyar.android.rocketinfo.data.api.Api
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://api.spacexdata.com"

class ApiClient {
    private val requests: Requests
    var spacexApi: Api

    init {
        val okHttpClient = OkHttpClient().newBuilder()
                .readTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .addInterceptor(mHttpLoggingInterceptor)
                .build()

        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .build()

        requests = retrofit.create(Requests::class.java)
        spacexApi = Api(requests)
    }

    private val mHttpLoggingInterceptor: HttpLoggingInterceptor
        get() {
            val logging = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                logging.level = HttpLoggingInterceptor.Level.BODY
            } else {
                logging.level = HttpLoggingInterceptor.Level.NONE
            }
            return logging
        }
}
