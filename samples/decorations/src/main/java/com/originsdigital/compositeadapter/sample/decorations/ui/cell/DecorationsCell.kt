package com.originsdigital.compositeadapter.sample.decorations.ui.cell

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.cell.GenericClickItem
import com.originsdigital.compositeadapter.decoration.ItemDecoration
import com.originsdigital.compositeadapter.sample.decorations.R
import com.originsdigital.compositeadapter.sample.decorations.databinding.DecorationsCellBinding
import com.originsdigital.compositeadapter.sample.decorations.ui.entity.DecorationsUI

data class DecorationsCell(
    override val data: DecorationsUI,
    override val decoration: ItemDecoration<*>? = null,
    override val onClickListener: ((GenericClickItem<DecorationsUI>) -> Unit)? = null
) : Cell<DecorationsUI, DecorationsCell.SampleViewHolder> {

    override val uniqueId: String = data.id
    override val layoutId: Int = R.layout.decorations_cell

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): SampleViewHolder {
        return SampleViewHolder(DecorationsCellBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.binding.text.text = data.name
    }

    class SampleViewHolder(
        val binding: DecorationsCellBinding
    ) : RecyclerView.ViewHolder(binding.root)
}