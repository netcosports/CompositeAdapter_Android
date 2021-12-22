package com.originsdigital.compositeadapter.sample.ui

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.originsdigital.compositeadapter.adapter.CompositeAdapter
import com.originsdigital.compositeadapter.decoration.CompositeItemDecoration

class SamplesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val compositeAdapter = CompositeAdapter()
        val recyclerView = RecyclerView(this).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            adapter = compositeAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(CompositeItemDecoration())
        }
        setContentView(recyclerView)

        val cells = SampleUI.Type.values().map { type ->
            val name = when (type) {
                SampleUI.Type.DECORATIONS -> "Decorations"
                SampleUI.Type.INNER_RECYCLER -> "Inner RecyclerView/ViewPager/Webview/Player/Etc"
            }
            SampleCell(
                data = SampleUI(
                    name = name,
                    type = type
                )
            )
        }
        compositeAdapter.submitList(cells)
    }
}