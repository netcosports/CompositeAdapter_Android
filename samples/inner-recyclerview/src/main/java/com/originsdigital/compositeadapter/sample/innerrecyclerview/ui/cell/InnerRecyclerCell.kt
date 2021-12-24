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

data class InnerRecyclerCell(
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
        return SampleViewHolder(InnerRecyclerListItemBinding.inflate(inflater, parent, false))
    }

    // This is the correct way to optimize bindings with payloads
    // But you can skip this overload, so `onBindViewHolder` with `payload` will call
    // `onBindViewHolder` without `payload`, and result will be the same in this case
    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: List<Any>
    ): Boolean {
        return if (payloads.isNotEmpty() && payloads.all { payload -> RECYCLER_VIEW_PAYLOAD == payload }) {
            submitData(holder)
            true
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        submitData(holder)
    }

    private fun submitData(holder: RecyclerView.ViewHolder) {
        (holder as SampleViewHolder).submitList(data.cells)
    }

    // We do not need to animate the InnerRecyclerCell, because it has its own CompositeAdapter,
    // which will calculate the diffs of his cells and animate these changes within itself.
    // The same for the ViewPager1/ViewPager2/Webview/VideoPlayer/other complex view.
    override fun getChangePayload(newItem: Cell<*>): Any = RECYCLER_VIEW_PAYLOAD

    companion object {
        private const val RECYCLER_VIEW_PAYLOAD = "RECYCLER_VIEW"
    }

    private class SampleViewHolder(
        binding: InnerRecyclerListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val adapter = CompositeAdapter()

        init {
            binding.apply {
                recyclerView.adapter = adapter
                recyclerView.layoutManager = PercentWidthLinearLayoutManager(binding.root.context)
                recyclerView.addItemDecoration(CompositeItemDecoration())
            }
        }

        fun submitList(cells: List<Cell<*>>) {
            adapter.submitList(cells)
        }
    }
}