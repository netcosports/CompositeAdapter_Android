package com.originsdigital.compositeadapter.sample.innerrecyclerview.ui.entity

import androidx.recyclerview.widget.RecyclerView
import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.sample.innerrecyclerview.ui.stateholder.ScrollStatesHolder

data class InnerRecyclerUI(
    val id: String,
    val cells: List<Cell<*>>,
    val recycledViewPool: RecyclerView.RecycledViewPool,
    val scrollStatesHolder: ScrollStatesHolder
)