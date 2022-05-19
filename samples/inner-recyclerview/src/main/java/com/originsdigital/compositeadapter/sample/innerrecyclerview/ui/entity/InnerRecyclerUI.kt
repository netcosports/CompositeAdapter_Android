package com.originsdigital.compositeadapter.sample.innerrecyclerview.ui.entity

import com.originsdigital.compositeadapter.cell.GenericCell
import com.originsdigital.compositeadapter.sample.innerrecyclerview.ui.stateholder.ScrollStatesHolder

data class InnerRecyclerUI(
    val id: String,
    val cells: List<GenericCell>,
    val scrollStatesHolder: ScrollStatesHolder
)