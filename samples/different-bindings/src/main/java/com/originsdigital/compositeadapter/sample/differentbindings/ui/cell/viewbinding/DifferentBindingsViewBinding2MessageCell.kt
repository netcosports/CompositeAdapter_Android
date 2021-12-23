package com.originsdigital.compositeadapter.sample.differentbindings.ui.cell.viewbinding

import androidx.recyclerview.widget.RecyclerView
import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.cell.ClickItem
import com.originsdigital.compositeadapter.decoration.ItemDecoration
import com.originsdigital.compositeadapter.sample.differentbindings.R
import com.originsdigital.compositeadapter.sample.differentbindings.databinding.DifferentBindingsViewBinding2ListItemBinding
import com.originsdigital.compositeadapter.sample.differentbindings.ui.cell.viewbinding.base.ViewBindingCell
import com.originsdigital.compositeadapter.sample.differentbindings.ui.entity.DifferentBindingsUI
import com.originsdigital.compositeadapter.utils.getViewBinding

data class DifferentBindingsViewBinding2MessageCell(
    override val data: DifferentBindingsUI,
    override val decoration: ItemDecoration<out Cell<*>>? = null,
    override val onClickListener: ((ClickItem<DifferentBindingsUI>) -> Unit)? = null
) : ViewBindingCell<DifferentBindingsUI> {

    override val uniqueId: String = data.type.name
    override val layoutId: Int = R.layout.different_bindings_view_binding_2_list_item

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // Unfortunately the ViewBinding doesn't have a cache for bindings inside the root view tags
        // like the DataBinding. To remove repeated findViewById calls,
        // we can call the `getViewBinding` to bind and save the binding or get the saved binding
        // Or we can have a custom ViewHolder for each Cell, see SampleViewBinding1MessageCell
        (holder.getViewBinding(DifferentBindingsViewBinding2ListItemBinding::bind)).apply {
            text.text = data.name
        }
    }
}