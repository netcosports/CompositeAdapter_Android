package com.netcosports.composite.adapter.sample.ui.sample.cell.common

import androidx.recyclerview.widget.RecyclerView
import com.netcosports.composite.adapter.cell.ClickItem
import com.netcosports.composite.adapter.sample.R
import com.netcosports.composite.adapter.sample.databinding.CommonErrorListItemBinding
import com.netcosports.composite.adapter.sample.ui.sample.cell.viewbinding.SampleViewBindingCell
import com.netcosports.composite.adapter.utils.getViewBinding

data class SampleErrorCell(
    override val uniqueId: String = "ErrorCell",
    override val data: String,
    val onRetryClickListener: () -> Unit
) : SampleViewBindingCell<String> {

    override val layoutId: Int = R.layout.common_error_list_item
    override val onClickListener: ((ClickItem<String>) -> Unit) = { onRetryClickListener() }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder.getViewBinding(CommonErrorListItemBinding::bind)).apply {
            message.text = data
        }
    }
}