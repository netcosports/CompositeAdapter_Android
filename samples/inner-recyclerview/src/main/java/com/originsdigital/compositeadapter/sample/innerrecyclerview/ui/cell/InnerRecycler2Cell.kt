package com.originsdigital.compositeadapter.sample.innerrecyclerview.ui.cell

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.originsdigital.compositeadapter.adapter.CompositeAdapter
import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.cell.ClickItem
import com.originsdigital.compositeadapter.decoration.CompositeItemDecoration
import com.originsdigital.compositeadapter.decoration.ItemDecoration
import com.originsdigital.compositeadapter.sample.innerrecyclerview.R
import com.originsdigital.compositeadapter.sample.innerrecyclerview.databinding.InnerRecyclerListItemBinding
import com.originsdigital.compositeadapter.sample.innerrecyclerview.ui.entity.InnerRecyclerUI
import com.originsdigital.compositeadapter.sample.innerrecyclerview.ui.layoutmanager.PercentWidthLinearLayoutManager

data class InnerRecycler2Cell(
    override val data: InnerRecyclerUI,
    override val decoration: ItemDecoration<out Cell<*>>? = null,
    override val onClickListener: ((ClickItem<InnerRecyclerUI>) -> Unit)? = null
) : Cell<InnerRecyclerUI> {

    override val uniqueId: String = data.id
    override val layoutId: Int = R.layout.inner_recycler_list_item

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return SampleViewHolder(
            InnerRecyclerListItemBinding.inflate(inflater, parent, false).also { binding ->
                // Don't forget that Cell can survive the configuration changes inside the ViewModel
                // or in some other way. So you MUST NOT store any link to
                // View/ViewHolder/Fragment/Context/etc in the Cell otherwise it will be leaked.
                binding.recyclerView.adapter = CompositeAdapter()
                binding.recyclerView.layoutManager =
                    PercentWidthLinearLayoutManager(binding.root.context)
                binding.recyclerView.addItemDecoration(CompositeItemDecoration())
            }
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SampleViewHolder).binding.apply {
            (recyclerView.adapter as CompositeAdapter).submitList(data.cells)
        }
    }

    // We do not need to animate the InnerRecyclerCell, because it has its own CompositeAdapter,
    // which will calculate the diffs of his cells and animate these changes within itself.
    // The same for the ViewPager1/ViewPager2/Webview/VideoPlayer/other complex view.
    // `onBindViewHolder` with `payload` will be called. But its empty so it will call
    // `onBindViewHolder` without `payload` where we use submitList
    override fun getChangePayload(newItem: Cell<*>): Any = RECYCLER_VIEW_PAYLOAD

    companion object {
        private const val RECYCLER_VIEW_PAYLOAD = "RECYCLER_VIEW"
    }

    private class SampleViewHolder(
        val binding: InnerRecyclerListItemBinding
    ) : RecyclerView.ViewHolder(binding.root)
}