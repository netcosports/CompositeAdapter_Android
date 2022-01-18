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
import com.originsdigital.compositeadapter.sample.innerrecyclerview.databinding.InnerRecycler2CellBinding
import com.originsdigital.compositeadapter.sample.innerrecyclerview.ui.entity.InnerRecyclerUI
import com.originsdigital.compositeadapter.sample.innerrecyclerview.ui.layoutmanager.PercentWidthLinearLayoutManager

// less code (can be less with `ViewBindingCell`) than `InnerRecycler1Cell`
data class InnerRecycler2Cell(
    override val data: InnerRecyclerUI,
    override val decoration: ItemDecoration<out Cell<*>>? = null,
    override val onClickListener: ((ClickItem<InnerRecyclerUI>) -> Unit)? = null
) : Cell<InnerRecyclerUI> {

    override val uniqueId: String = data.id
    override val layoutId: Int = R.layout.inner_recycler_2_cell

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return SampleViewHolder(
            InnerRecycler2CellBinding.inflate(inflater, parent, false).also { binding ->
                // Don't forget that Cell can survive the configuration changes inside the ViewModel
                // or in some other way. So you MUST NOT store any link to
                // Adapter/View/ViewHolder/Fragment/Context/etc in the Cell
                // otherwise it will be leaked.
                binding.recyclerView.adapter = CompositeAdapter()
                binding.recyclerView.layoutManager =
                    PercentWidthLinearLayoutManager(binding.root.context)
                binding.recyclerView.addItemDecoration(CompositeItemDecoration())
            }
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SampleViewHolder).binding.apply {
            if (recyclerView.recycledViewPool != data.recycledViewPool) {
                recyclerView.setRecycledViewPool(data.recycledViewPool)
            }
            (recyclerView.adapter as CompositeAdapter).submitList(data.cells)
            data.scrollStatesHolder.setupRecyclerView(uniqueId, holder.binding.recyclerView)
        }
    }

    // We do not need to animate the InnerRecyclerCell, because it has its own CompositeAdapter,
    // which will calculate the diffs of his cells and animate these changes within itself.
    // The same for the ViewPager1/ViewPager2/Webview/VideoPlayer/other complex view.
    // `onBindViewHolder` with `payload` will be called. But its empty so it will call
    // `onBindViewHolder` without `payload` where submitList is called
    override fun getChangePayload(newItem: Cell<*>): Any = RECYCLER_VIEW_PAYLOAD

    companion object {
        private const val RECYCLER_VIEW_PAYLOAD = "RECYCLER_VIEW"
    }

    private class SampleViewHolder(
        val binding: InnerRecycler2CellBinding
    ) : RecyclerView.ViewHolder(binding.root)
}