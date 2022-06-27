package com.pawanmaniyar.android.rocketinfo.data.util.mapper

import com.pawanmaniyar.android.rocketinfo.data.api.model.Rocket
import com.pawanmaniyar.android.rocketinfo.data.db.entity.*

object DataMapper {
    fun mapRocketToRocketEntity(rocket: Rocket): RocketEntity {
        return RocketEntity(id = rocket.id ?: "1",
                name = rocket.name,
                type = rocket.type,
                active = rocket.active,
                stages = rocket.stages,
                boosters = rocket.boosters,
                costPerLaunch = rocket.costPerLaunch,
                successRatePct = rocket.successRatePct,
                firstFlight = rocket.firstFlight,
                company = rocket.company,
                country = rocket.country,
                description = rocket.description,
                flickrImages = rocket.flickrImages,
                height = Height(
                        rocket.height?.meters,
                        rocket.height?.feet
                ),
                diameter = Diameter(
                        rocket.diameter?.meters,
                        rocket.diameter?.feet
                ),
                mass = Mass(
                        rocket.mass?.kg,
                        rocket.mass?.lb
                ),
                firstStage = FirstStage(
                        rocket.firstStage?.reusable,
                        rocket.firstStage?.engines,
                        rocket.firstStage?.fuelAmountTons,
                        rocket.firstStage?.burnTimeSec
                ),
                secondStage = SecondStage(
                        rocket.secondStage?.engines,
                        rocket.secondStage?.fuelAmountTons,
                        rocket.secondStage?.burnTimeSec
                )
        )
    }
}
