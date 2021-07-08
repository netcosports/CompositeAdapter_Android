package com.originsdigital.compositeadapter.sample.ui.sample.cell.common

import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.sample.R

data class SampleLoaderCell(
    override val uniqueId: String = "LoaderCell"
) : Cell<Unit> {

    override val data: Unit = Unit
    override val layoutId: Int = R.layout.common_loader_list_item

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SampleCellViewHolder(FrameLayout(inflater.context).apply {
            layoutParams = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
            addView(ProgressBar(inflater.context, null, android.R.attr.progressBarStyleLarge).apply {
                layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    Gravity.CENTER_HORIZONTAL
                )
            })
        })
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = Unit
}