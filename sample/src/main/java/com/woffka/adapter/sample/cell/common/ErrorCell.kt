package com.woffka.adapter.sample.cell.common

import androidx.recyclerview.widget.RecyclerView
import com.netcosports.components.adapter.recycler.holder.ClickItem
import com.netcosports.components.adapter.recycler.utils.getViewBinding
import com.woffka.adapter.sample.cell.viewbinding.ViewBindingCell
import com.woffka.sample.R
import com.woffka.sample.databinding.CommonErrorListItemBinding

data class ErrorCell(
    override val uniqueId: String = "ErrorCell",
    override val data: String,
    val onRetryClickListener: () -> Unit
) : ViewBindingCell<String> {

    override val layoutId: Int = R.layout.common_error_list_item
    override val onClickListener: ((ClickItem<String>) -> Unit) = { onRetryClickListener() }

    override fun onBind(holder: RecyclerView.ViewHolder, position: Int) {
        (holder.getViewBinding(CommonErrorListItemBinding::bind)).apply {
            message.text = data
        }
    }
}