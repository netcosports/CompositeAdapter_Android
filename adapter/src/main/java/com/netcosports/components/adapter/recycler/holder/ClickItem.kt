package com.netcosports.components.adapter.recycler.holder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

class ClickItem<T>(
    val context: Context,
    val holder: RecyclerView.ViewHolder,
    val position: Int,
    val item: T
)