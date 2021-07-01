package com.woffka.adapter.sample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.netcosports.components.adapter.recycler.adapter.CompositeAdapter
import com.netcosports.components.adapter.recycler.decoration.CompositeItemDecoration
import com.woffka.sample.databinding.AdapterSampleActivityBinding

class AdapterSampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this)[AdapterSampleViewModel::class.java]

        val binding = AdapterSampleActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            swipeRefreshLayout.setOnRefreshListener { viewModel.onRefresh() }

            val adapter = CompositeAdapter()
            val layoutManager = LinearLayoutManager(this@AdapterSampleActivity)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = layoutManager
            recyclerView.addItemDecoration(CompositeItemDecoration())
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    viewModel.onLastItemVisibleChanged(layoutManager.findLastVisibleItemPosition())
                }
            })

            viewModel.state.refreshingData.observe(this@AdapterSampleActivity, Observer { isRefreshing ->
                swipeRefreshLayout.isRefreshing = isRefreshing
            })
            viewModel.state.cellsData.observe(this@AdapterSampleActivity, Observer { items ->
                adapter.submitList(items)
            })
        }
    }
}