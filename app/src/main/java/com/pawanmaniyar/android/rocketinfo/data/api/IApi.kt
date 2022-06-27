package com.pawanmaniyar.android.rocketinfo.data.api

import com.pawanmaniyar.android.rocketinfo.data.api.model.Rocket
import retrofit2.Response

interface IApi {
    suspend fun fetchRocketList(): Response<List<Rocket>>
    suspend fun fetchRocketByName(name: String): Response<Rocket>
}
