package com.originsdigital.compositeadapter.sample.differentbindings.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.originsdigital.compositeadapter.adapter.CompositeAdapter
import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.decoration.CompositeItemDecoration
import com.originsdigital.compositeadapter.sample.differentbindings.ui.cell.databinding.DifferentBindingsDataBinding1MessageCell
import com.originsdigital.compositeadapter.sample.differentbindings.ui.cell.databinding.DifferentBindingsDataBinding2MessageCell
import com.originsdigital.compositeadapter.sample.differentbindings.ui.cell.view.DifferentBindingsViewMessageCell
import com.originsdigital.compositeadapter.sample.differentbindings.ui.cell.viewbinding.DifferentBindingsViewBinding1MessageCell
import com.originsdigital.compositeadapter.sample.differentbindings.ui.cell.viewbinding.DifferentBindingsViewBinding2MessageCell
import com.originsdigital.compositeadapter.sample.differentbindings.ui.entity.DifferentBindingsUI

class DifferentBindingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(generateView())
    }

    private fun generateView(): View {
        val compositeAdapter = CompositeAdapter().apply {
            submitList(generateData())
        }
        val swipeRefreshLayout = SwipeRefreshLayout(this).apply {
            setOnRefreshListener {
                compositeAdapter.submitList(generateData()) {
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

    //should be inside ViewModel
    private fun generateData(): List<Cell<*>> {
        return DifferentBindingsUI.Type.values().map { type ->
            when (type) {
                DifferentBindingsUI.Type.VIEW -> {
                    listOf(
                        DifferentBindingsViewMessageCell(
                            data = DifferentBindingsUI(
                                name = "Cell with a programmatically generated View",
                                type = type
                            )
                        )
                    )
                }
                DifferentBindingsUI.Type.VIEW_BINDING -> {
                    listOf(
                        DifferentBindingsViewBinding1MessageCell(
                            data = DifferentBindingsUI(
                                name = "Cell with default ViewHolder",
                                type = type
                            )
                        ),
                        DifferentBindingsViewBinding2MessageCell(
                            data = DifferentBindingsUI(
                                name = "Cell with custom ViewHolder",
                                type = type
                            )
                        )
                    )
                }
                DifferentBindingsUI.Type.DATA_BINDING -> {
                    listOf(
                        DifferentBindingsDataBinding1MessageCell(
                            data = DifferentBindingsUI(
                                name = "Cell with DataBindings inside xml",
                                type = type
                            )
                        ),
                        DifferentBindingsDataBinding2MessageCell(
                            data = DifferentBindingsUI(
                                name = "Cell with DataBindings inside Cell",
                                type = type
                            )
                        )
                    )
                }
            }
        }
            .flatten()
            .shuffled()
    }

    companion object {
        fun getLaunchIntent(context: Context): Intent {
            return Intent(context, DifferentBindingsActivity::class.java)
        }
    }
}