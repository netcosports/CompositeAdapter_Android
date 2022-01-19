package com.originsdigital.compositeadapter.ui.entity

import androidx.annotation.DimenRes
import androidx.annotation.StringRes

data class CommonErrorUI(
    val id: String,
    val message: Message,
    @DimenRes val heightId: Int?
) {

    sealed class Message {

        data class Text(val text: String?) : Message()
        data class Resource(@StringRes val textId: Int) : Message()
    }
}