package com.originsdigital.compositeadapter.news.ui.cell

import android.view.LayoutInflater
import android.view.ViewGroup
import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.cell.ClickItem
import com.originsdigital.compositeadapter.decoration.ItemDecoration
import com.originsdigital.compositeadapter.news.core.entity.NewsEntity
import com.originsdigital.compositeadapter.news.ui.R
import com.originsdigital.compositeadapter.news.ui.databinding.NewsCellBinding
import com.originsdigital.compositeadapter.ui.cell.viewbinding.ViewBindingCell
import com.originsdigital.compositeadapter.ui.cell.viewbinding.ViewBindingViewHolder

data class NewsCell(
    override val data: NewsEntity,
    override val decoration: ItemDecoration<out Cell<*>>?,
    override val onClickListener: ((ClickItem<NewsEntity>) -> Unit)?
) : ViewBindingCell<NewsEntity, NewsCellBinding>() {

    override val uniqueId: String = data.id
    override val layoutId: Int = R.layout.news_cell

    override fun createViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): NewsCellBinding {
        return NewsCellBinding.inflate(inflater, parent, false)
    }

    override fun onBindViewHolder(
        holder: ViewBindingViewHolder<NewsCellBinding>,
        position: Int
    ) {
        holder.binding.text.text = data.text
    }
}