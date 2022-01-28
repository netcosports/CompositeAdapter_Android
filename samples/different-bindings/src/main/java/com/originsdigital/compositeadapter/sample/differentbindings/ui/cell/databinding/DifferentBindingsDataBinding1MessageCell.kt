package com.originsdigital.compositeadapter.sample.differentbindings.ui.cell.databinding

import com.originsdigital.compositeadapter.cell.GenericClickItem
import com.originsdigital.compositeadapter.decoration.ItemDecoration
import com.originsdigital.compositeadapter.sample.differentbindings.R
import com.originsdigital.compositeadapter.sample.differentbindings.databinding.DifferentBindingsDataBinding1CellBinding
import com.originsdigital.compositeadapter.sample.differentbindings.ui.cell.databinding.base.DataBindingCell
import com.originsdigital.compositeadapter.sample.differentbindings.ui.entity.DifferentBindingsUI

// ViewBinding is better anyway
data class DifferentBindingsDataBinding1MessageCell(
    override val data: DifferentBindingsUI,
    override val decoration: ItemDecoration<*>? = null,
    override val onClickListener: ((GenericClickItem<DifferentBindingsUI>) -> Unit)? = null
) : DataBindingCell<DifferentBindingsUI, DifferentBindingsDataBinding1CellBinding>() {

    override val uniqueId: String = data.type.name
    override val layoutId: Int = R.layout.different_bindings_data_binding_1_cell

    // We do not need `onBindViewHolder` because everything is done inside the DataBindingCell
//    override fun onBindViewHolder(
//        holder: DataBindingViewHolder<DifferentBindingsDataBinding1CellBinding>,
//        position: Int
//    ) {
//        super.onBindViewHolder(holder, position)
//    }
}