package com.originsdigital.compositeadapter.ui.mapper

import androidx.annotation.DimenRes
import com.originsdigital.compositeadapter.ui.entity.CommonLoaderUI

class LoaderUIMapper {

    fun mapLoader(id: String, @DimenRes heightId: Int?): CommonLoaderUI {
        return CommonLoaderUI(
            id = id,
            heightId = heightId
        )
    }
}