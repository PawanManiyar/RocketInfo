package com.pawanmaniyar.android.rocketinfo.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pawanmaniyar.android.rocketinfo.data.db.dao.RocketDao
import com.pawanmaniyar.android.rocketinfo.data.db.entity.RocketEntity
import com.pawanmaniyar.android.rocketinfo.data.util.mapper.RoomTypeConverters

@Database(entities = [RocketEntity::class],
        version = 1)
@TypeConverters(RoomTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun rocketDao(): RocketDao

    companion object {
        fun buildDatabase(context: Context) =
                Room.databaseBuilder(context, AppDatabase::class.java, "appRocket.db").build()
    }
}
