package com.originsdigital.composite.adapter.sample.ui.sample.cell.viewbinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.originsdigital.composite.adapter.cell.Cell
import com.originsdigital.composite.adapter.sample.ui.sample.cell.common.SampleCellViewHolder

interface SampleViewBindingCell<T> : Cell<T> {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SampleCellViewHolder(inflater.inflate(layoutId, parent, false))
    }
}