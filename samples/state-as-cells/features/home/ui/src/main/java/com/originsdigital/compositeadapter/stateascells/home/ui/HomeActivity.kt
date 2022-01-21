package com.originsdigital.compositeadapter.stateascells.home.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.originsdigital.compositeadapter.stateascells.home.ui.databinding.HomeActivityBinding
import com.originsdigital.compositeadapter.ui.utils.initRecyclerView
import com.originsdigital.compositeadapter.ui.utils.submitAdapterData
import org.koin.androidx.viewmodel.ext.android.getViewModel

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel: HomeViewModel = getViewModel()

        binding.apply {
            initRecyclerView(
                recyclerView = recyclerView,
                swipeRefreshLayout = swipeRefreshLayout,
                viewModel = viewModel,
                lifecycleOwner = this@HomeActivity,
                stateDelegate = { adapter, state ->
                    submitAdapterData(
                        adapter = adapter,
                        swipeRefreshLayout = swipeRefreshLayout,
                        isRefreshing = state.isRefreshing,
                        cells = state.cells
                    )
                }
            )
        }
    }

    companion object {
        fun getLaunchIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }
}