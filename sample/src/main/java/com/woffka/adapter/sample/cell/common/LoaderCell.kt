package com.woffka.adapter.sample.cell.common

import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.netcosports.components.adapter.recycler.cell.Cell
import com.netcosports.components.adapter.recycler.holder.CellViewHolder
import com.woffka.sample.R

data class LoaderCell(
    override val uniqueId: String = "LoaderCell"
) : Cell<Unit> {

    override val data: Unit = Unit
    override val layoutId: Int = R.layout.common_loader_list_item

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CellViewHolder(FrameLayout(inflater.context).apply {
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

    override fun onBind(holder: RecyclerView.ViewHolder, position: Int) = Unit
}