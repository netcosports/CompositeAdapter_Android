package com.originsdigital.compositeadapter.sample.innerrecyclerview.ui.stateholder

import android.os.Parcelable
import androidx.recyclerview.widget.RecyclerView

class ScrollStateHolder {

    private var pendingScroll: Boolean = false
    private var currentState: Parcelable? = null

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                saveScrollState(recyclerView)
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (dx != 0) {
                pendingScroll = true
            }
        }
    }

    fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.removeOnScrollListener(scrollListener)
        restoreScrollState(recyclerView)
        recyclerView.addOnScrollListener(scrollListener)
    }

    private fun saveScrollState(recyclerView: RecyclerView) {
        if (pendingScroll) {
            pendingScroll = false
            currentState = recyclerView.layoutManager!!.onSaveInstanceState()
        }
    }

    private fun restoreScrollState(recyclerView: RecyclerView) {
        val layoutManager = recyclerView.layoutManager!!
        val currentState = currentState
        if (currentState == null) {
            layoutManager.scrollToPosition(0)
        } else {
            layoutManager.onRestoreInstanceState(currentState)
        }
    }
}