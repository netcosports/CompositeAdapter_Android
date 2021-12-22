package com.originsdigital.compositeadapter.sample.ui

data class SampleUI(
    val name: String,
    val type: Type
) {
    enum class Type {
        DECORATIONS, INNER_RECYCLER
    }
}