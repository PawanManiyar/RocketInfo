package com.pawanmaniyar.android.rocketinfo.ui.rockets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pawanmaniyar.android.rocketinfo.R
import com.pawanmaniyar.android.rocketinfo.data.db.entity.RocketEntity
import com.pawanmaniyar.android.rocketinfo.data.util.core.ErrorType
import com.pawanmaniyar.android.rocketinfo.data.util.core.Resource
import com.pawanmaniyar.android.rocketinfo.ui.base.BaseFragment
import com.pawanmaniyar.android.rocketinfo.ui.rockets.adapter.OnRocketItemClicked
import com.pawanmaniyar.android.rocketinfo.ui.rockets.adapter.RocketsAdapter
import com.pawanmaniyar.android.rocketinfo.ui.rockets.details.RocketDetailsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class RocketsFragment : BaseFragment(), OnRocketItemClicked {
    private val viewModel: RocketsViewModel by viewModel()
    lateinit var recyclerView: RecyclerView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        fetchRockets()
    }

    private fun fetchRockets() {

        viewModel.rockets.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Success -> {
                    recyclerView.adapter = RocketsAdapter(result.data, this)
                    hideProgressBar()
                }
                is Resource.Loading -> showProgressBar()
                is Resource.Error -> {
                    hideProgressBar()
                    when (result.error) {
                        is ErrorType.InternetError -> showSnackbar(R.string.no_internet_toast)
                        is ErrorType.ServerError -> showSnackbar(result.error.message)
                    }
                }
            }
        })
    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onRocketItemClicked(launch: RocketEntity) {
        RocketDetailsActivity.start(context, launch)
    }
}
