package com.originsdigital.compositeadapter.sample.innerrecyclerview.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.originsdigital.compositeadapter.adapter.CompositeAdapter
import com.originsdigital.compositeadapter.cell.GenericCell
import com.originsdigital.compositeadapter.decoration.CompositeItemDecoration
import com.originsdigital.compositeadapter.decoration.ItemDecoration
import com.originsdigital.compositeadapter.decoration.SpaceItemDecoration
import com.originsdigital.compositeadapter.sample.innerrecyclerview.ui.cell.InnerRecycler1Cell
import com.originsdigital.compositeadapter.sample.innerrecyclerview.ui.cell.InnerRecycler2Cell
import com.originsdigital.compositeadapter.sample.innerrecyclerview.ui.cell.InnerRecyclerItemCell
import com.originsdigital.compositeadapter.sample.innerrecyclerview.ui.entity.InnerRecyclerItemUI
import com.originsdigital.compositeadapter.sample.innerrecyclerview.ui.entity.InnerRecyclerUI
import com.originsdigital.compositeadapter.sample.innerrecyclerview.ui.stateholder.ScrollStatesHolder
import kotlin.random.Random

class InnerRecyclerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataHolder = DataHolder(dpToPx(8f).toInt())
        setContentView(generateView(dataHolder))
    }

    private fun Context.dpToPx(dp: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)
    }

    private fun generateView(dataHolder: DataHolder): View {
        val compositeAdapter = CompositeAdapter().apply {
            submitList(dataHolder.generateData())
        }
        val swipeRefreshLayout = SwipeRefreshLayout(this).apply {
            setOnRefreshListener {
                compositeAdapter.submitList(dataHolder.generateData()) {
                    isRefreshing = false
                }
            }
        }
        val recyclerView = RecyclerView(this).apply {
            adapter = compositeAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(CompositeItemDecoration())
        }
        swipeRefreshLayout.addView(
            recyclerView,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        return swipeRefreshLayout
    }


    companion object {
        fun getLaunchIntent(context: Context): Intent {
            return Intent(context, InnerRecyclerActivity::class.java)
        }
    }

    //should be inside ViewModel/UIMapper
    private class DataHolder(space: Int) {

        private val scrollStatesHolder = ScrollStatesHolder()

        private val singleItemDecoration: ItemDecoration
        private val firstItemDecoration: ItemDecoration
        private val middleItemDecoration: ItemDecoration
        private val lastItemDecoration: ItemDecoration

        init {
            singleItemDecoration = SpaceItemDecoration(
                top = space,
                bottom = space,
                start = space,
                end = space
            )
            firstItemDecoration = SpaceItemDecoration(
                top = space,
                bottom = space,
                start = space,
                end = space / 2
            )
            middleItemDecoration = SpaceItemDecoration(
                top = space,
                bottom = space,
                start = space / 2,
                end = space / 2
            )
            lastItemDecoration = SpaceItemDecoration(
                top = space,
                bottom = space,
                start = space / 2,
                end = space
            )
        }

        //should be inside ViewModel/UIMapper
        fun generateData(): List<GenericCell> {
            // Cells do the same, but
            // InnerRecycler1Cell - more code but more clearer and `correct`
            // InnerRecycler2Cell - less code (can be less with `ViewBindingCell`)
            val useClearerVersion = Random.nextBoolean()
            return (0..20).map { recyclerId ->
                val size = 10
                val cells = (0..size).mapIndexed { index, itemId ->
                    val decoration = when {
                        size == 0 -> singleItemDecoration
                        index == 0 -> firstItemDecoration
                        index == size -> lastItemDecoration
                        else -> middleItemDecoration
                    }
                    InnerRecyclerItemCell(
                        data = InnerRecyclerItemUI(
                            id = itemId.toString(),
                            name = "Value $itemId is ${Random.nextBoolean()}"
                        ),
                        decoration = decoration
                    )
                }
                val recyclerUI = InnerRecyclerUI(
                    id = recyclerId.toString(),
                    cells = cells,
                    scrollStatesHolder = scrollStatesHolder
                )
                if (useClearerVersion) {
                    InnerRecycler1Cell(
                        data = recyclerUI
                    )
                } else {
                    InnerRecycler2Cell(
                        data = recyclerUI
                    )
                }
            }
        }
    }
}