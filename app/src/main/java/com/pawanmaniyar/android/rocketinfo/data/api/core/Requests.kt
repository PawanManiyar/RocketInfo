package com.pawanmaniyar.android.rocketinfo.data.api.core

import com.pawanmaniyar.android.rocketinfo.data.api.model.Rocket
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Requests {
    @GET("/v2/rockets")
    suspend fun fetchRocketList(): Response<List<Rocket>>

    @GET("/v2/rockets/{name}")
    suspend fun fetchRocketByName(@Path("name") name: String): Response<Rocket>
}
