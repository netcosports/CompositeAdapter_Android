package com.originsdigital.compositeadapter.sample.innerrecyclerview.ui.entity

import com.originsdigital.compositeadapter.cell.Cell

data class InnerRecyclerUI(
    val id: String,
    val cells: List<Cell<*>>
)