package com.originsdigital.compositeadapter.ui.mapper

import androidx.annotation.DimenRes
import com.originsdigital.compositeadapter.core.extensions.isInternetConnectionException
import com.originsdigital.compositeadapter.ui.R
import com.originsdigital.compositeadapter.ui.entity.CommonErrorUI

class ErrorUIMapper {

    fun mapError(
        id: String,
        text: String,
        @DimenRes heightId: Int? = null
    ): CommonErrorUI {
        return CommonErrorUI(
            id = id,
            message = CommonErrorUI.Message.Text(text = text),
            heightId = heightId
        )
    }

    fun mapError(
        id: String,
        throwable: Throwable,
        @DimenRes heightId: Int? = null
    ): CommonErrorUI {
        return CommonErrorUI(
            id = id,
            message = CommonErrorUI.Message.Resource(
                textId = if (throwable.isInternetConnectionException) {
                    R.string.common_no_connection_error
                } else {
                    R.string.common_default_error
                }
            ),
            heightId = heightId
        )
    }
}