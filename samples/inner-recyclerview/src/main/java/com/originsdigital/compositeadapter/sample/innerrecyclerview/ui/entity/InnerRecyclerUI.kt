package com.originsdigital.compositeadapter.sample.innerrecyclerview.ui.entity

import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.sample.innerrecyclerview.ui.stateholder.ScrollStatesHolder

data class InnerRecyclerUI(
    val id: String,
    val cells: List<Cell<*>>,
    val scrollStatesHolder: ScrollStatesHolder
)