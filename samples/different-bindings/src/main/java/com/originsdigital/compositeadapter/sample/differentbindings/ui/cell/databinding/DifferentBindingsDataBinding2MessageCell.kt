package com.originsdigital.compositeadapter.sample.differentbindings.ui.cell.databinding

import android.view.LayoutInflater
import android.view.ViewGroup
import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.cell.GenericClickItem
import com.originsdigital.compositeadapter.decoration.ItemDecoration
import com.originsdigital.compositeadapter.sample.differentbindings.R
import com.originsdigital.compositeadapter.sample.differentbindings.databinding.DifferentBindingsDataBinding2CellBinding
import com.originsdigital.compositeadapter.sample.differentbindings.ui.cell.base.BaseViewHolder
import com.originsdigital.compositeadapter.sample.differentbindings.ui.entity.DifferentBindingsUI

// ViewBinding is better anyway
data class DifferentBindingsDataBinding2MessageCell(
    override val data: DifferentBindingsUI,
    override val decoration: ItemDecoration? = null,
    override val onClickListener: ((GenericClickItem<DifferentBindingsUI>) -> Unit)? = null
) : Cell<DifferentBindingsUI, DifferentBindingsDataBinding2MessageCell.CustomViewHolder> {

    override val uniqueId: String = data.type.name
    override val viewType: Int = R.layout.different_bindings_data_binding_2_cell

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        return CustomViewHolder(
            DifferentBindingsDataBinding2CellBinding.inflate(
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
        val binding: DifferentBindingsDataBinding2CellBinding
    ) : BaseViewHolder(binding.root)
}