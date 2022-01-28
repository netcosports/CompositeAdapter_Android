package com.originsdigital.compositeadapter.sample.innerrecyclerview.ui.cell

import android.content.Context
import android.graphics.Outline
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.recyclerview.widget.RecyclerView
import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.cell.ClickItem
import com.originsdigital.compositeadapter.cell.GenericCell
import com.originsdigital.compositeadapter.decoration.ItemDecoration
import com.originsdigital.compositeadapter.sample.innerrecyclerview.R
import com.originsdigital.compositeadapter.sample.innerrecyclerview.databinding.InnerRecyclerItemCellBinding
import com.originsdigital.compositeadapter.sample.innerrecyclerview.ui.entity.InnerRecyclerItemUI

data class InnerRecyclerItemCell(
    override val data: InnerRecyclerItemUI,
    override val decoration: ItemDecoration<GenericCell>? = null,
    override val onClickListener: ((ClickItem<InnerRecyclerItemUI>) -> Unit)? = null
) : Cell<InnerRecyclerItemUI, InnerRecyclerItemCell.SampleViewHolder> {

    override val uniqueId: String = data.id
    override val layoutId: Int = R.layout.inner_recycler_item_cell

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): SampleViewHolder {
        return SampleViewHolder(
            InnerRecyclerItemCellBinding.inflate(inflater, parent, false).also { holder ->
                holder.root.applyRoundCorners(holder.root.context.dpToPx(6f))
            }
        )
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.binding.text.text = data.name
    }

    private fun Context.dpToPx(dp: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)
    }

    private fun View.applyRoundCorners(radius: Float) {
        outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {
                outline.setRoundRect(
                    0,
                    0,
                    view.width,
                    view.height,
                    radius
                )
            }
        }
        clipToOutline = true
    }

    class SampleViewHolder(
        val binding: InnerRecyclerItemCellBinding
    ) : RecyclerView.ViewHolder(binding.root)
}