package com.originsdigital.compositeadapter.ui.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.originsdigital.compositeadapter.adapter.CompositeAdapter
import com.originsdigital.compositeadapter.decoration.CompositeItemDecoration
import com.originsdigital.compositeadapter.ui.vm.BaseStateViewModel

fun initRecyclerView(
    recyclerView: RecyclerView,
    adapter: CompositeAdapter = CompositeAdapter(),
    layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(recyclerView.context)
) {
    recyclerView.adapter = adapter
    recyclerView.layoutManager = layoutManager
    recyclerView.addItemDecoration(CompositeItemDecoration())
}

fun initRecyclerView(
    recyclerView: RecyclerView,
    adapter: CompositeAdapter = CompositeAdapter(),
    layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(recyclerView.context),
    swipeRefreshLayout: SwipeRefreshLayout,
    onRefresh: () -> Unit
) {
    initRecyclerView(
        recyclerView = recyclerView,
        adapter = adapter,
        layoutManager = layoutManager
    )
    initSwipeRefresh(
        swipeRefreshLayout = swipeRefreshLayout,
        onRefresh = onRefresh
    )
}

fun <STATE : Any, VIEW_MODEL : BaseStateViewModel<STATE>> initRecyclerView(
    recyclerView: RecyclerView,
    adapter: CompositeAdapter = CompositeAdapter(),
    layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(recyclerView.context),
    swipeRefreshLayout: SwipeRefreshLayout,
    viewModel: VIEW_MODEL,
    lifecycleOwner: LifecycleOwner,
    lifecycleState: Lifecycle.State = Lifecycle.State.RESUMED,
    stateDelegate: (CompositeAdapter, state: STATE) -> Unit
) {
    initRecyclerView(
        recyclerView = recyclerView,
        adapter = adapter,
        layoutManager = layoutManager,
        swipeRefreshLayout = swipeRefreshLayout,
        onRefresh = viewModel::onRefresh
    )

    observeCellViewModel(
        viewModel = viewModel,
        lifecycleOwner = lifecycleOwner,
        lifecycleState = lifecycleState,
        stateDelegate = { state -> stateDelegate(adapter, state) }
    )
}

fun <STATE : Any, VIEW_MODEL : BaseStateViewModel<STATE>> initRecyclerView(
    recyclerView: RecyclerView,
    adapter: CompositeAdapter = CompositeAdapter(),
    layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(recyclerView.context),
    viewModel: VIEW_MODEL,
    lifecycleOwner: LifecycleOwner,
    lifecycleState: Lifecycle.State = Lifecycle.State.RESUMED,
    stateDelegate: (CompositeAdapter, state: STATE) -> Unit
) {
    initRecyclerView(
        recyclerView = recyclerView,
        adapter = adapter,
        layoutManager = layoutManager
    )

    observeCellViewModel(
        viewModel = viewModel,
        lifecycleOwner = lifecycleOwner,
        lifecycleState = lifecycleState,
        stateDelegate = { state -> stateDelegate(adapter, state) }
    )
}