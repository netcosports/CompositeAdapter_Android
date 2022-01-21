package com.originsdigital.compositeadapter.sample.decorations.ui.cell

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.cell.ClickItem
import com.originsdigital.compositeadapter.decoration.ItemDecoration
import com.originsdigital.compositeadapter.sample.decorations.R
import com.originsdigital.compositeadapter.sample.decorations.databinding.DecorationsCellBinding
import com.originsdigital.compositeadapter.sample.decorations.ui.entity.DecorationsUI

data class DecorationsCell(
    override val data: DecorationsUI,
    override val decoration: ItemDecoration<out Cell<*>>? = null,
    override val onClickListener: ((ClickItem<DecorationsUI>) -> Unit)? = null
) : Cell<DecorationsUI> {

    override val uniqueId: String = data.id
    override val layoutId: Int = R.layout.decorations_cell

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return SampleViewHolder(DecorationsCellBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SampleViewHolder).binding.apply {
            text.text = data.name
        }
    }

    private class SampleViewHolder(
        val binding: DecorationsCellBinding
    ) : RecyclerView.ViewHolder(binding.root)
}