package com.netcosports.composite.adapter.sample.ui.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.netcosports.composite.adapter.adapter.CompositeAdapter
import com.netcosports.composite.adapter.decoration.CompositeItemDecoration
import com.netcosports.composite.adapter.sample.databinding.SampleActivityBinding
import com.netcosports.composite.adapter.sample.ui.sample.vm.SampleViewModel

class SampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this)[SampleViewModel::class.java]

        val binding = SampleActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            swipeRefreshLayout.setOnRefreshListener { viewModel.onRefresh() }

            val adapter = CompositeAdapter()
            val layoutManager = LinearLayoutManager(this@SampleActivity)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = layoutManager
            recyclerView.addItemDecoration(CompositeItemDecoration())
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    viewModel.onLastItemVisibleChanged(layoutManager.findLastVisibleItemPosition())
                }
            })

            viewModel.state.refreshingData.observe(this@SampleActivity, Observer { isRefreshing ->
                swipeRefreshLayout.isRefreshing = isRefreshing
            })
            viewModel.state.cellsData.observe(this@SampleActivity, Observer { items ->
                adapter.submitList(items)
            })
        }
    }
}