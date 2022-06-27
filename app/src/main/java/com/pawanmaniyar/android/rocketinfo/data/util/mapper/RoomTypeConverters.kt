package com.pawanmaniyar.android.rocketinfo.data.util.mapper

import androidx.room.TypeConverter

class RoomTypeConverters {

    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return value.joinToString("$%$")
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        return value.split("$%$")
    }
}
