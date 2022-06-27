package com.pawanmaniyar.android.rocketinfo.data.repository.datasource

import com.pawanmaniyar.android.rocketinfo.data.api.Api
import com.pawanmaniyar.android.rocketinfo.data.util.core.BaseDataSource

class RocketDataSource(private val api: Api) : BaseDataSource() {
    suspend fun fetchRocketList() = getResult { api.fetchRocketList() }

    suspend fun fetchRocketByName(name: String) = getResult { api.fetchRocketByName(name) }
}
