package com.originsdigital.composite.adapter.sample.ui.sample.cell.common

import androidx.recyclerview.widget.RecyclerView
import com.originsdigital.composite.adapter.cell.ClickItem
import com.originsdigital.composite.adapter.sample.R
import com.originsdigital.composite.adapter.sample.databinding.CommonErrorListItemBinding
import com.originsdigital.composite.adapter.sample.ui.sample.cell.viewbinding.SampleViewBindingCell
import com.originsdigital.composite.adapter.utils.getViewBinding

data class SampleErrorCell(
    override val uniqueId: String = "ErrorCell",
    override val data: String,
    override val onClickListener: ((ClickItem<String>) -> Unit)
) : SampleViewBindingCell<String> {

    override val layoutId: Int = R.layout.common_error_list_item

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder.getViewBinding(CommonErrorListItemBinding::bind)).apply {
            message.text = data
        }
    }
}