package com.netcosports.composite.adapter.sample.ui.sample.cell.common

import androidx.recyclerview.widget.RecyclerView
import com.netcosports.composite.adapter.holder.ClickItem
import com.netcosports.composite.adapter.sample.R
import com.netcosports.composite.adapter.sample.databinding.CommonFullscreenErrorListItemBinding
import com.netcosports.composite.adapter.sample.ui.sample.cell.viewbinding.SampleViewBindingCell
import com.netcosports.composite.adapter.utils.getViewBinding

data class SampleFullScreenErrorCell(
    override val uniqueId: String = "FullScreenErrorCell",
    override val data: String,
    val onRetryClickListener: () -> Unit
) : SampleViewBindingCell<String> {

    override val layoutId: Int = R.layout.common_fullscreen_error_list_item
    override val onClickListener: ((ClickItem<String>) -> Unit) = { onRetryClickListener() }

    override fun onBind(holder: RecyclerView.ViewHolder, position: Int) {
        (holder.getViewBinding(CommonFullscreenErrorListItemBinding::bind)).apply {
            message.text = data
        }
    }
}