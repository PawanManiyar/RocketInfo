package com.pawanmaniyar.android.rocketinfo.ui.rockets.adapter

import com.pawanmaniyar.android.rocketinfo.data.db.entity.RocketEntity

interface OnRocketItemClicked {
    fun onRocketItemClicked(launch: RocketEntity)
}
