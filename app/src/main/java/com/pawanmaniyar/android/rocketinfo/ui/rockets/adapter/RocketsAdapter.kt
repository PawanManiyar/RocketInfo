package com.pawanmaniyar.android.rocketinfo.ui.rockets.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.pawanmaniyar.android.rocketinfo.R
import com.pawanmaniyar.android.rocketinfo.data.db.entity.RocketEntity
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class RocketsAdapter(private var rockets: List<RocketEntity>,
                     private var listener: OnRocketItemClicked) : androidx.recyclerview.widget.RecyclerView.Adapter<RocketsAdapter.RocketViewHolder>() {

    init {
        rockets = rockets.reversed()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.rocket_item, parent, false)
        return RocketViewHolder(v)
    }

    override fun onBindViewHolder(holder: RocketViewHolder, position: Int) {
        holder.fillWith(rockets[position])
    }

    override fun getItemCount(): Int = rockets.size

    inner class RocketViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        var rocketName: TextView
        var country: TextView
        var status: TextView
        var flickerImage: ImageView

        init {
            rocketName = itemView.findViewById(R.id.rocketName)
            country = itemView.findViewById(R.id.country)
            status = itemView.findViewById(R.id.engineCount)
            flickerImage = itemView.findViewById(R.id.flickerImage)
        }

        fun fillWith(item: RocketEntity) {
            rocketName.text = item.name
            country.text = item.country

            Glide.with(itemView)
                    .load(item.flickrImages?.get(0))
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(flickerImage)

            // Status
            item.active?.let {
                if (it) {
                    status.text = String.format(
                            getString(R.string.status),
                            getString(R.string.active))
                } else {
                    status.text = String.format(
                            getString(R.string.status),
                            getString(R.string.inactive))
                }
            }

            itemView.setOnClickListener {
                listener.onRocketItemClicked(item)
            }
        }

        private fun formatCost(cost: Int): String {
            val formatter = NumberFormat.getInstance(Locale.US) as DecimalFormat
            val symbols = formatter.decimalFormatSymbols

            symbols.groupingSeparator = ' '
            formatter.decimalFormatSymbols = symbols
            return formatter.format(cost)
        }

        private fun getString(@StringRes id: Int): String = itemView.context.getString(id)
    }
}
