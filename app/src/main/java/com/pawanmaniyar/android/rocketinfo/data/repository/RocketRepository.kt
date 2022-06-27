package com.pawanmaniyar.android.rocketinfo.data.repository

import com.pawanmaniyar.android.rocketinfo.data.db.dao.RocketDao
import com.pawanmaniyar.android.rocketinfo.data.repository.datasource.RocketDataSource
import com.pawanmaniyar.android.rocketinfo.data.util.core.BaseDataSource
import com.pawanmaniyar.android.rocketinfo.data.util.core.resultLiveData
import com.pawanmaniyar.android.rocketinfo.data.util.mapper.DataMapper

class RocketRepository(private val dataSource: RocketDataSource,
                       private val rocketDao: RocketDao) : BaseDataSource() {

    val rockets = resultLiveData(
            databaseQuery = { rocketDao.getAll() },
            networkCall = { dataSource.fetchRocketList() },
            saveCallResult = {
                rocketDao.insert(it.map { rocket ->
                    DataMapper.mapRocketToRocketEntity(rocket)
                })
            })
}
