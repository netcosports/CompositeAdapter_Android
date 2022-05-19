package com.originsdigital.compositeadapter.sample.differentbindings.ui.cell.viewbinding

import android.view.LayoutInflater
import android.view.ViewGroup
import com.originsdigital.compositeadapter.cell.GenericClickItem
import com.originsdigital.compositeadapter.decoration.ItemDecoration
import com.originsdigital.compositeadapter.sample.differentbindings.R
import com.originsdigital.compositeadapter.sample.differentbindings.databinding.DifferentBindingsViewBinding1CellBinding
import com.originsdigital.compositeadapter.sample.differentbindings.ui.cell.viewbinding.base.ViewBindingCell
import com.originsdigital.compositeadapter.sample.differentbindings.ui.cell.viewbinding.base.ViewBindingViewHolder
import com.originsdigital.compositeadapter.sample.differentbindings.ui.entity.DifferentBindingsUI

data class DifferentBindingsViewBinding1MessageCell(
    override val data: DifferentBindingsUI,
    override val decoration: ItemDecoration? = null,
    override val onClickListener: ((GenericClickItem<DifferentBindingsUI>) -> Unit)? = null
) : ViewBindingCell<DifferentBindingsUI, DifferentBindingsViewBinding1CellBinding>() {

    override val uniqueId: String = data.type.name
    override val layoutId: Int = R.layout.different_bindings_view_binding_1_cell

    override fun createViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): DifferentBindingsViewBinding1CellBinding {
        return DifferentBindingsViewBinding1CellBinding.inflate(inflater, parent, false)
    }

    override fun onBindViewHolder(
        holder: ViewBindingViewHolder<DifferentBindingsViewBinding1CellBinding>,
        position: Int
    ) {
        holder.binding.text.text = data.name
    }
}