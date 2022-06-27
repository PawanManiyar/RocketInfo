package com.pawanmaniyar.android.rocketinfo.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.pawanmaniyar.android.rocketinfo.data.db.BaseDao
import com.pawanmaniyar.android.rocketinfo.data.db.entity.RocketEntity

@Dao
interface RocketDao : BaseDao<RocketEntity> {

    @Query("SELECT * FROM rocketentity")
    fun getAll(): LiveData<List<RocketEntity>>

    @Query("Select * from rocketentity WHERE rocketentity.id == :id")
    fun getSingleRocket(id: String): LiveData<RocketEntity>
}
