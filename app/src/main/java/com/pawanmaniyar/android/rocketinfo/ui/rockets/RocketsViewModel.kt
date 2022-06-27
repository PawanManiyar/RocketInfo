package com.pawanmaniyar.android.rocketinfo.ui.rockets

import androidx.lifecycle.ViewModel
import com.pawanmaniyar.android.rocketinfo.data.repository.RocketRepository

class RocketsViewModel(repository: RocketRepository) : ViewModel() {
    val rockets = repository.rockets
}
