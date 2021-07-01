package com.netcosports.composite.adapter.sample.ui.sample.cell.viewbinding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.netcosports.composite.adapter.cell.Cell

interface SampleViewBindingCell<T> : Cell<T> {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewBindingViewHolder(inflater.inflate(layoutId, parent, false))
    }

    class ViewBindingViewHolder(val viewBinding: View) : RecyclerView.ViewHolder(viewBinding)
}