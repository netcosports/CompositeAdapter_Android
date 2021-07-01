package com.netcosports.composite.adapter.cell

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

class ClickItem<T>(
    val context: Context,
    val holder: RecyclerView.ViewHolder,
    val position: Int,
    val item: T
)