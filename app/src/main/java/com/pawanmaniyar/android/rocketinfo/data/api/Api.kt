package com.pawanmaniyar.android.rocketinfo.data.api

import com.pawanmaniyar.android.rocketinfo.data.api.core.Requests
import com.pawanmaniyar.android.rocketinfo.data.api.model.Rocket
import retrofit2.Response

class Api(private val requests: Requests) : IApi {
    override suspend fun fetchRocketList(): Response<List<Rocket>> {
        return requests.fetchRocketList()
    }

    override suspend fun fetchRocketByName(name: String): Response<Rocket> {
        return requests.fetchRocketByName(name)
    }
}
