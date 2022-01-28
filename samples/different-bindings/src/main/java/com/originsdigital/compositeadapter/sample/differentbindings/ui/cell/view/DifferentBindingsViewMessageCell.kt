package com.originsdigital.compositeadapter.sample.differentbindings.ui.cell.view

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.cell.ClickItem
import com.originsdigital.compositeadapter.cell.GenericCell
import com.originsdigital.compositeadapter.decoration.ItemDecoration
import com.originsdigital.compositeadapter.sample.differentbindings.R
import com.originsdigital.compositeadapter.sample.differentbindings.ui.cell.base.BaseViewHolder
import com.originsdigital.compositeadapter.sample.differentbindings.ui.entity.DifferentBindingsUI

data class DifferentBindingsViewMessageCell(
    override val data: DifferentBindingsUI,
    override val decoration: ItemDecoration<GenericCell>? = null,
    override val onClickListener: ((ClickItem<DifferentBindingsUI>) -> Unit)? = null
) : Cell<DifferentBindingsUI, DifferentBindingsViewMessageCell.CustomViewHolder> {

    override val uniqueId: String = data.type.name
    override val layoutId: Int = R.layout.different_bindings_view_cell

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        val padding = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            20f,
            parent.resources.displayMetrics
        ).toInt()
        return CustomViewHolder(
            AppCompatTextView(inflater.context).apply {
                id = R.id.text
                layoutParams = RecyclerView.LayoutParams(
                    RecyclerView.LayoutParams.MATCH_PARENT,
                    RecyclerView.LayoutParams.WRAP_CONTENT
                )
                setPadding(padding, padding, padding, padding)
                val outValue = TypedValue()
                context.theme.resolveAttribute(R.attr.selectableItemBackground, outValue, true)
                setBackgroundResource(outValue.resourceId)
                TextViewCompat.setTextAppearance(this, R.style.TextAppearance_AppCompat_Medium)
            }
        )
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.text.text = data.name
    }

    class CustomViewHolder(val text: TextView) : BaseViewHolder(text)
}