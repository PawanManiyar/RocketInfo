package com.pawanmaniyar.android.rocketinfo.data.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "rocketentity")
@Parcelize
data class RocketEntity(
        @PrimaryKey
        var id: String = "1",
        @ColumnInfo(name = "name") val name: String?,
        @ColumnInfo(name = "type") val type: String?,
        @ColumnInfo(name = "active") val active: Boolean?,
        @ColumnInfo(name = "stages") val stages: Int?,
        @ColumnInfo(name = "boosters") val boosters: Int?,
        @ColumnInfo(name = "cost_per_launch") val costPerLaunch: Int?,
        @ColumnInfo(name = "success_rate_pct") val successRatePct: Int?,
        @ColumnInfo(name = "first_flight") val firstFlight: String?,
        @ColumnInfo(name = "country") val country: String?,
        @ColumnInfo(name = "company") val company: String?,
        @Embedded val height: Height?,
        @Embedded val diameter: Diameter?,
        @Embedded val mass: Mass?,
        @Embedded val firstStage: FirstStage?,
        @Embedded val secondStage: SecondStage?,
        @ColumnInfo(name = "description") val description: String?,
        @ColumnInfo(name = "flickr_images") val flickrImages: List<String?>?
) : Parcelable

@Parcelize
data class Mass(
        @ColumnInfo(name = "mass_kg") val kg: Double?,
        @ColumnInfo(name = "mass_lb") val lb: Double?
) : Parcelable

@Parcelize
data class SecondStage(
        @ColumnInfo(name = "second_stage_engines") val engines: Int?,
        @ColumnInfo(name = "second_stage_fuleTons") val fuelAmountTons: Double?,
        @ColumnInfo(name = "second_stage_burnTimeSec") val burnTimeSec: Double?
) : Parcelable

@Parcelize
data class Payloads(
        val option1: String?,
        @Embedded val compositeFairing: CompositeFairing?
) : Parcelable

@Parcelize
data class CompositeFairing(
        @Embedded val height: Height?,
        @Embedded val diameter: Diameter?
) : Parcelable

@Parcelize
data class Height(
        @ColumnInfo(name = "height_meters") val meters: Double?,
        @ColumnInfo(name = "height_feet") val feet: Double?
) : Parcelable

@Parcelize
data class FirstStage(
        val reusable: Boolean?,
        val engines: Int?,
        val fuelAmountTons: Double?,
        val burnTimeSec: Double?
) : Parcelable

@Parcelize
data class Diameter(
        @ColumnInfo(name = "d_m") val meters: Double?,
        @ColumnInfo(name = "d_f") val feet: Double?
) : Parcelable