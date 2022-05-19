package com.originsdigital.compositeadapter.stories.ui.cell

import android.view.LayoutInflater
import android.view.ViewGroup
import com.originsdigital.compositeadapter.cell.GenericClickItem
import com.originsdigital.compositeadapter.decoration.ItemDecoration
import com.originsdigital.compositeadapter.stories.core.entity.StoryEntity
import com.originsdigital.compositeadapter.stories.ui.R
import com.originsdigital.compositeadapter.stories.ui.databinding.StoryCellBinding
import com.originsdigital.compositeadapter.ui.cell.viewbinding.ViewBindingCell
import com.originsdigital.compositeadapter.ui.cell.viewbinding.ViewBindingViewHolder

data class StoryCell(
    override val data: StoryEntity,
    override val decoration: ItemDecoration? = null,
    override val onClickListener: ((GenericClickItem<StoryEntity>) -> Unit)? = null
) : ViewBindingCell<StoryEntity, StoryCellBinding>() {

    override val uniqueId: String = data.id
    override val layoutId: Int = R.layout.story_cell

    override fun createViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): StoryCellBinding {
        return StoryCellBinding.inflate(inflater, parent, false)
    }

    override fun onBindViewHolder(
        holder: ViewBindingViewHolder<StoryCellBinding>,
        position: Int
    ) {
        holder.binding.apply {
            image.setColorFilter(data.colorInt)
            text.text = data.name
        }
    }
}