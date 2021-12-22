package com.originsdigital.compositeadapter.sample.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.cell.ClickItem
import com.originsdigital.compositeadapter.decoration.ItemDecoration
import com.originsdigital.compositeadapter.sample.R
import com.originsdigital.compositeadapter.sample.databinding.SampleListItemBinding
import com.originsdigital.compositeadapter.ui.cell.viewbinding.ViewBindingCell
import com.originsdigital.compositeadapter.utils.getViewBinding

data class SampleCell(
    override val data: SampleUI,
    override val decoration: ItemDecoration<out Cell<*>>? = null,
    override val onClickListener: ((ClickItem<SampleUI>) -> Unit)? = null
) : ViewBindingCell<SampleUI> {

    override val uniqueId: String = data.type.name
    override val layoutId: Int = R.layout.sample_list_item

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return SampleViewHolder(inflater.inflate(layoutId, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder.getViewBinding(SampleListItemBinding::bind)).apply {
            text.text = data.name
        }
    }

    class SampleViewHolder(view: View) : RecyclerView.ViewHolder(view)
}