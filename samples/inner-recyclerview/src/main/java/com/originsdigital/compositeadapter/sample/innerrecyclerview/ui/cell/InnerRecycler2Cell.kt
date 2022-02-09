package com.originsdigital.compositeadapter.sample.innerrecyclerview.ui.cell

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.originsdigital.compositeadapter.adapter.CompositeAdapter
import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.cell.GenericCell
import com.originsdigital.compositeadapter.cell.GenericClickItem
import com.originsdigital.compositeadapter.decoration.CompositeItemDecoration
import com.originsdigital.compositeadapter.decoration.ItemDecoration
import com.originsdigital.compositeadapter.sample.innerrecyclerview.R
import com.originsdigital.compositeadapter.sample.innerrecyclerview.databinding.InnerRecycler2CellBinding
import com.originsdigital.compositeadapter.sample.innerrecyclerview.ui.entity.InnerRecyclerUI
import com.originsdigital.compositeadapter.sample.innerrecyclerview.ui.layoutmanager.PercentWidthLinearLayoutManager

// less code than `InnerRecycler1Cell`
data class InnerRecycler2Cell(
    override val data: InnerRecyclerUI,
    override val decoration: ItemDecoration? = null,
    override val onClickListener: ((GenericClickItem<InnerRecyclerUI>) -> Unit)? = null
) : Cell<InnerRecyclerUI, InnerRecycler2Cell.SampleViewHolder> {

    override val uniqueId: String = data.id
    override val layoutId: Int = R.layout.inner_recycler_2_cell

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): SampleViewHolder {
        return SampleViewHolder(InnerRecycler2CellBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.setData(data)
    }

    // We do not need to animate the InnerRecyclerCell, because it has its own CompositeAdapter,
    // which will calculate the diffs of his cells and animate these changes within itself.
    // The same for the ViewPager1/ViewPager2/Webview/VideoPlayer/other complex view.
    // `onBindViewHolder` with `payload` will be called. But its empty so it will call
    // `onBindViewHolder` without `payload` where submitList is called
    override fun getChangePayload(newItem: GenericCell): Any = RECYCLER_VIEW_PAYLOAD

    companion object {
        private const val RECYCLER_VIEW_PAYLOAD = "RECYCLER_VIEW"
    }

    class SampleViewHolder(
        private val binding: InnerRecycler2CellBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val adapter = CompositeAdapter()

        init {
            binding.apply {
                recyclerView.adapter = adapter
                recyclerView.layoutManager = PercentWidthLinearLayoutManager(binding.root.context)
                recyclerView.addItemDecoration(CompositeItemDecoration())
            }
        }

        fun setData(data: InnerRecyclerUI) {
            adapter.submitList(data.cells)
            data.scrollStatesHolder.setupRecyclerView(data.id, binding.recyclerView)
        }
    }
}