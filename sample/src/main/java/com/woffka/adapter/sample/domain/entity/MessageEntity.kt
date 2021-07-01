package com.woffka.adapter.sample.domain.entity

data class MessageEntity(
    val id: String,
    val type: Type,
    val text: String?
) {
    enum class Type {
        VIEW, VIEW_BINDING, DATA_BINDING
    }
}