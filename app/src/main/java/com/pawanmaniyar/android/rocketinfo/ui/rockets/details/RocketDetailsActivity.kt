package com.pawanmaniyar.android.rocketinfo.ui.rockets.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.StringRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.pawanmaniyar.android.rocketinfo.R
import com.pawanmaniyar.android.rocketinfo.data.db.entity.RocketEntity
import com.pawanmaniyar.android.rocketinfo.ui.base.BaseActivity
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*


class RocketDetailsActivity : BaseActivity() {
    private lateinit var rocketData: RocketEntity

    companion object {
        private const val EXTRA_ROCKET = "rocket"

        fun start(context: Context?, rocket: RocketEntity) {
            val intent = Intent(context, RocketDetailsActivity::class.java)
            intent.putExtra(EXTRA_ROCKET, rocket)
            context?.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rocket_details)

        val entity = intent.getParcelableExtra(EXTRA_ROCKET) as? RocketEntity
        if (entity == null) {
            showToast(R.string.error)
            finish()
        } else {
            rocketData = entity
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "${rocketData.name}. ${rocketData.description} (${rocketData.stages})"
        initViews()
    }

    private fun initViews() {
        initBaseInformation()
    }

    private fun initBaseInformation() {
        var tvRocketName: TextView = findViewById(R.id.tvRocketName)
        var tvDescription: TextView = findViewById(R.id.tvDescription)
        var tvStatus: TextView = findViewById(R.id.tvStatus)
        var tvCostPerLaunch: TextView = findViewById(R.id.tvCostPerLaunch)
        var tvSuccessRate: TextView = findViewById(R.id.tvSuccessRate)
        var tvWikiLink: TextView = findViewById(R.id.tvWikiLink)
        var tvRocketHeight: TextView = findViewById(R.id.tvRocketHeight)
        var tvRocketDiameter: TextView = findViewById(R.id.tvRocketDiameter)
        tvRocketName.text = rocketData.name
        tvDescription.text = rocketData.description
        rocketData.active?.let {
            if (it) {
                tvStatus.text = String.format(
                        getStringFormat(R.string.status),
                        getStringFormat(R.string.active)
                )
            } else {
                tvStatus.text = String.format(
                        getStringFormat(R.string.status),
                        getStringFormat(R.string.inactive)
                )
            }
        }
        tvCostPerLaunch.text = String.format(
                getStringFormat(R.string.rocket_cost_per_launch),
                formatCost(rocketData.costPerLaunch ?: 0)
        )
        tvSuccessRate.text =
                String.format(getStringFormat(R.string.rocket_success_rate), rocketData.successRatePct)
        tvWikiLink.text = "abc.com"
        tvRocketHeight.text = String.format(
                getStringFormat(R.string.rocket_height),
                rocketData.height?.meters,
                rocketData.height?.feet
        )
        tvRocketDiameter.text = String.format(
                getStringFormat(R.string.rocket_diameter),
                rocketData.diameter?.meters,
                rocketData.diameter?.feet
        )

        for (j in 1..rocketData.flickrImages!!.size) {

            create_img(rocketData.flickrImages?.get(j - 1))
        }
    }

    private fun create_img(url: String?) {
        val linearLayout: LinearLayout = findViewById(R.id.imgLayout)
        val parms = LinearLayout.LayoutParams(200, 200)
        parms.gravity = Gravity.CENTER
        parms.setMargins(20, 20, 20, 20)
        val imageView = ImageView(this)
        imageView.setLayoutParams(parms)
        val id = resources.getIdentifier(url, "id", packageName)
        imageView.setImageResource(id)
        linearLayout.addView(imageView)
        imageView.id = id

        Glide.with(linearLayout)
                .load(url)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.ic_error)
                .into(imageView)

    }

    private fun formatCost(cost: Int): String {
        val formatter = NumberFormat.getInstance(Locale.US) as DecimalFormat
        val symbols = formatter.decimalFormatSymbols

        symbols.groupingSeparator = ' '
        formatter.decimalFormatSymbols = symbols
        return formatter.format(cost)
    }

    private fun getStringFormat(@StringRes id: Int): String = applicationContext.getString(id)
}
