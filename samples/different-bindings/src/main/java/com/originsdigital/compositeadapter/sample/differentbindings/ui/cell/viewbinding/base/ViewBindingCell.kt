package com.originsdigital.compositeadapter.sample.differentbindings.ui.cell.viewbinding.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.originsdigital.compositeadapter.cell.Cell

interface ViewBindingCell<T> : Cell<T> {

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return ViewBindingViewHolder(inflater.inflate(layoutId, parent, false))
    }
}