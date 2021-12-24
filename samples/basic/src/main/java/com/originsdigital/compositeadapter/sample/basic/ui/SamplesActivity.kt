package com.originsdigital.compositeadapter.sample.basic.ui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.originsdigital.compositeadapter.adapter.CompositeAdapter
import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.cell.ClickItem
import com.originsdigital.compositeadapter.decoration.CompositeItemDecoration
import com.originsdigital.compositeadapter.sample.basic.ui.cell.SampleCell
import com.originsdigital.compositeadapter.sample.basic.ui.entity.SampleUI
import com.originsdigital.compositeadapter.sample.decorations.ui.DecorationsActivity
import com.originsdigital.compositeadapter.sample.differentbindings.ui.DifferentBindingsActivity
import com.originsdigital.compositeadapter.sample.innerrecyclerview.ui.InnerRecyclerActivity

class SamplesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(generateView())
    }

    private fun generateView(): View {
        val compositeAdapter = CompositeAdapter().apply {
            submitList(generateData())
        }
        val recyclerView = RecyclerView(this).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            adapter = compositeAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(CompositeItemDecoration())
        }
        return recyclerView
    }

    //should be inside ViewModel
    private fun generateData(): List<Cell<*>> {
        return SampleUI.Type.values().map { type ->
            val name = when (type) {
                SampleUI.Type.DECORATIONS -> "Decorations"
                SampleUI.Type.DIFFERENT_BINDINGS -> "ViewBindings/DataBindings/Programmatically View"
                SampleUI.Type.INNER_RECYCLER -> "Inner RecyclerView/ViewPager/Etc"
            }
            val onClickListener: (ClickItem<SampleUI>) -> Unit = { item ->
                startActivity(
                    when (item.item.type) {
                        SampleUI.Type.DECORATIONS -> {
                            DecorationsActivity.getLaunchIntent(this)
                        }
                        SampleUI.Type.DIFFERENT_BINDINGS -> {
                            DifferentBindingsActivity.getLaunchIntent(this)
                        }
                        SampleUI.Type.INNER_RECYCLER -> {
                            InnerRecyclerActivity.getLaunchIntent(this)
                        }
                    }
                )
            }
            SampleCell(
                data = SampleUI(
                    name = name,
                    type = type
                ),
                onClickListener = onClickListener
            )
        }
    }
}