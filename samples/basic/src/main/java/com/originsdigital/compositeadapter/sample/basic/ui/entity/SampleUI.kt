package com.originsdigital.compositeadapter.sample.basic.ui.entity

data class SampleUI(
    val name: String,
    val type: Type
) {
    enum class Type {
        DIFFERENT_BINDINGS, DECORATIONS, INNER_RECYCLER
    }
}