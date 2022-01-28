package com.originsdigital.compositeadapter.sample.basic.ui.cell

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.cell.ClickItem
import com.originsdigital.compositeadapter.cell.GenericCell
import com.originsdigital.compositeadapter.decoration.ItemDecoration
import com.originsdigital.compositeadapter.sample.basic.R
import com.originsdigital.compositeadapter.sample.basic.databinding.SampleCellBinding
import com.originsdigital.compositeadapter.sample.basic.ui.entity.SampleUI

data class SampleCell(
    override val data: SampleUI,
    override val decoration: ItemDecoration<GenericCell>? = null,
    override val onClickListener: ((ClickItem<SampleUI>) -> Unit)? = null
) : Cell<SampleUI, SampleCell.SampleViewHolder> {

    override val uniqueId: String = data.type.name
    override val layoutId: Int = R.layout.sample_cell

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): SampleViewHolder {
        return SampleViewHolder(SampleCellBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.binding.text.text = data.name
    }

    class SampleViewHolder(
        val binding: SampleCellBinding
    ) : RecyclerView.ViewHolder(binding.root)
}