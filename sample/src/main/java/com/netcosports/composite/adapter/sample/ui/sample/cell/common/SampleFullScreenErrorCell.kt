package com.netcosports.composite.adapter.sample.ui.sample.cell.common

import androidx.recyclerview.widget.RecyclerView
import com.netcosports.composite.adapter.cell.ClickItem
import com.netcosports.composite.adapter.sample.R
import com.netcosports.composite.adapter.sample.databinding.CommonFullscreenErrorListItemBinding
import com.netcosports.composite.adapter.sample.ui.sample.cell.viewbinding.SampleViewBindingCell
import com.netcosports.composite.adapter.utils.getViewBinding

data class SampleFullScreenErrorCell(
    override val uniqueId: String = "FullScreenErrorCell",
    override val data: String,
    override val onClickListener: ((ClickItem<String>) -> Unit)
) : SampleViewBindingCell<String> {

    override val layoutId: Int = R.layout.common_fullscreen_error_list_item

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder.getViewBinding(CommonFullscreenErrorListItemBinding::bind)).apply {
            message.text = data
        }
    }
}