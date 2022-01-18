package com.originsdigital.compositeadapter.sample.differentbindings.ui.cell.databinding

import android.view.LayoutInflater
import android.view.ViewGroup
import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.cell.ClickItem
import com.originsdigital.compositeadapter.decoration.ItemDecoration
import com.originsdigital.compositeadapter.sample.differentbindings.R
import com.originsdigital.compositeadapter.sample.differentbindings.databinding.DifferentBindingsDataBinding2ListItemBinding
import com.originsdigital.compositeadapter.sample.differentbindings.ui.cell.base.BaseCell
import com.originsdigital.compositeadapter.sample.differentbindings.ui.cell.base.BaseViewHolder
import com.originsdigital.compositeadapter.sample.differentbindings.ui.entity.DifferentBindingsUI

// ViewBinding is better anyway
data class DifferentBindingsDataBinding2MessageCell(
    override val data: DifferentBindingsUI,
    override val decoration: ItemDecoration<out Cell<*>>? = null,
    override val onClickListener: ((ClickItem<DifferentBindingsUI>) -> Unit)? = null
) : BaseCell<DifferentBindingsUI, DifferentBindingsDataBinding2MessageCell.CustomViewHolder>() {

    override val uniqueId: String = data.type.name
    override val layoutId: Int = R.layout.different_bindings_data_binding_2_list_item

    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        return CustomViewHolder(
            DifferentBindingsDataBinding2ListItemBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: CustomViewHolder,
        position: Int
    ) {
        // But we can have a custom onBindViewHolder instead of custom BindingAdapter functions
        holder.binding.text.text = data.name
    }

    class CustomViewHolder(
        val binding: DifferentBindingsDataBinding2ListItemBinding
    ) : BaseViewHolder(binding.root)
}