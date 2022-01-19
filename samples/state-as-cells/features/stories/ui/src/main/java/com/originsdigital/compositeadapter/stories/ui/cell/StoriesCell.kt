package com.originsdigital.compositeadapter.stories.ui.cell

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.originsdigital.compositeadapter.adapter.CompositeAdapter
import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.decoration.ItemDecoration
import com.originsdigital.compositeadapter.stories.ui.R
import com.originsdigital.compositeadapter.ui.cell.base.BaseCell
import com.originsdigital.compositeadapter.ui.cell.base.BaseViewHolder
import com.originsdigital.compositeadapter.ui.utils.initRecyclerView

data class StoriesCell(
    override val data: List<StoryCell>,
    override val decoration: ItemDecoration<out Cell<*>>? = null
) : BaseCell<List<StoryCell>, StoriesCell.CustomViewHolder>() {

    override val uniqueId: String = "StoriesCell"
    override val layoutId: Int = R.layout.stories_cell

    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        return CustomViewHolder(
            recyclerView = RecyclerView(inflater.context).apply {
                layoutParams = RecyclerView.LayoutParams(
                    RecyclerView.LayoutParams.MATCH_PARENT,
                    RecyclerView.LayoutParams.WRAP_CONTENT
                )
            }
        )
    }

    override fun onBindViewHolder(
        holder: CustomViewHolder,
        position: Int
    ) {
        holder.compositeAdapter.submitList(data)
    }

    override fun getChangePayload(newItem: Cell<*>): Any = "RECYCLER_VIEW"

    class CustomViewHolder(recyclerView: RecyclerView) : BaseViewHolder(recyclerView) {

        val compositeAdapter = CompositeAdapter()

        init {
            initRecyclerView(
                recyclerView = recyclerView,
                adapter = compositeAdapter,
                layoutManager = LinearLayoutManager(
                    recyclerView.context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            )
        }
    }
}