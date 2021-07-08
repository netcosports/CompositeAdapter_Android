package com.originsdigital.composite.adapter.sample.ui.sample.cell.common

import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.originsdigital.composite.adapter.cell.Cell
import com.originsdigital.composite.adapter.sample.R

data class SampleFullScreenLoaderCell(
    override val uniqueId: String = "FullScreenLoaderCell"
) : Cell<Unit> {

    override val data: Unit = Unit
    override val layoutId: Int = R.layout.common_fullscreen_loader_list_item

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SampleCellViewHolder(FrameLayout(inflater.context).apply {
            layoutParams = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.MATCH_PARENT
            )
            addView(ProgressBar(inflater.context, null, android.R.attr.progressBarStyleLarge).apply {
                layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    Gravity.CENTER
                )
            })
        })
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = Unit
}