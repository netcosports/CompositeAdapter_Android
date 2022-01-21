package com.originsdigital.compositeadapter.ui.extensions

import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.ui.cell.CommonErrorCell
import com.originsdigital.compositeadapter.ui.cell.CommonFullEmptyCell
import com.originsdigital.compositeadapter.ui.cell.CommonFullErrorCell
import com.originsdigital.compositeadapter.ui.cell.CommonFullLoaderCell
import com.originsdigital.compositeadapter.ui.cell.CommonLoaderCell

val Cell<*>.isStateCell: Boolean
    get() {
        return this is CommonLoaderCell
                || this is CommonFullLoaderCell
                || this is CommonErrorCell
                || this is CommonFullErrorCell
                || this is CommonFullEmptyCell
    }