package com.woffka.adapter.sample.cell.viewbinding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.netcosports.components.adapter.recycler.cell.Cell

interface ViewBindingCell<T> : Cell<T> {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewBindingViewHolder(inflater.inflate(layoutId, parent, false))
    }

    class ViewBindingViewHolder(val viewBinding: View) : RecyclerView.ViewHolder(viewBinding)
}