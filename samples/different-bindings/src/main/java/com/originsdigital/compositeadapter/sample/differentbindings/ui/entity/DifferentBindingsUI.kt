package com.originsdigital.compositeadapter.sample.differentbindings.ui.entity

data class DifferentBindingsUI(
    val name: String,
    val type: Type
) {
    enum class Type {
        VIEW, VIEW_BINDING, DATA_BINDING
    }
}