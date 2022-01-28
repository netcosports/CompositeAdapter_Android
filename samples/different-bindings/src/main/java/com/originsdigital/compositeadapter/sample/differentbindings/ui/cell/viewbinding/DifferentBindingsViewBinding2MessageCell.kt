package com.originsdigital.compositeadapter.sample.differentbindings.ui.cell.viewbinding

import android.view.LayoutInflater
import android.view.ViewGroup
import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.cell.ClickItem
import com.originsdigital.compositeadapter.cell.GenericCell
import com.originsdigital.compositeadapter.decoration.ItemDecoration
import com.originsdigital.compositeadapter.sample.differentbindings.R
import com.originsdigital.compositeadapter.sample.differentbindings.databinding.DifferentBindingsViewBinding2CellBinding
import com.originsdigital.compositeadapter.sample.differentbindings.ui.cell.base.BaseViewHolder
import com.originsdigital.compositeadapter.sample.differentbindings.ui.entity.DifferentBindingsUI

data class DifferentBindingsViewBinding2MessageCell(
    override val data: DifferentBindingsUI,
    override val decoration: ItemDecoration<GenericCell>? = null,
    override val onClickListener: ((ClickItem<DifferentBindingsUI>) -> Unit)? = null
) : Cell<DifferentBindingsUI, DifferentBindingsViewBinding2MessageCell.CustomViewHolder> {

    override val uniqueId: String = data.type.name
    override val layoutId: Int = R.layout.different_bindings_view_binding_2_cell

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        return CustomViewHolder(
            DifferentBindingsViewBinding2CellBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.binding.text.text = data.name
    }

    class CustomViewHolder(
        val binding: DifferentBindingsViewBinding2CellBinding
    ) : BaseViewHolder(binding.root)
}