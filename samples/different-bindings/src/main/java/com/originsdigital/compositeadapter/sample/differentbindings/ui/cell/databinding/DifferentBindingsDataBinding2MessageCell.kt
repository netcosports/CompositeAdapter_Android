package com.originsdigital.compositeadapter.sample.differentbindings.ui.cell.databinding

import androidx.recyclerview.widget.RecyclerView
import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.cell.ClickItem
import com.originsdigital.compositeadapter.decoration.ItemDecoration
import com.originsdigital.compositeadapter.sample.differentbindings.R
import com.originsdigital.compositeadapter.sample.differentbindings.databinding.DifferentBindingsDataBinding2ListItemBinding
import com.originsdigital.compositeadapter.sample.differentbindings.ui.cell.databinding.base.DataBindingCell
import com.originsdigital.compositeadapter.sample.differentbindings.ui.cell.databinding.base.DataBindingViewHolder
import com.originsdigital.compositeadapter.sample.differentbindings.ui.entity.DifferentBindingsUI

// ViewBinding is better anyway
data class DifferentBindingsDataBinding2MessageCell(
    override val data: DifferentBindingsUI,
    override val decoration: ItemDecoration<out Cell<*>>? = null,
    override val onClickListener: ((ClickItem<DifferentBindingsUI>) -> Unit)? = null
) : DataBindingCell<DifferentBindingsUI> {

    override val uniqueId: String = data.type.name
    override val layoutId: Int = R.layout.different_bindings_data_binding_2_list_item

    // But we can have a custom onBindViewHolder instead of custom BindingAdapter functions
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        ((holder as DataBindingViewHolder).bindings as DifferentBindingsDataBinding2ListItemBinding).apply {
            text.text = data.name
        }
    }
}