package com.originsdigital.compositeadapter.sample.decorations.ui

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.originsdigital.compositeadapter.adapter.CompositeAdapter
import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.decoration.CompositeItemDecoration
import com.originsdigital.compositeadapter.decoration.ItemDecoration
import com.originsdigital.compositeadapter.sample.decorations.ui.cell.DecorationsCell
import com.originsdigital.compositeadapter.sample.decorations.ui.decorations.SampleItemDecoration
import com.originsdigital.compositeadapter.sample.decorations.ui.entity.DecorationsUI
import kotlin.random.Random

class DecorationsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataHolder = DataHolder(
            space = dpToPx(20f).toInt(),
            radius = dpToPx(6f),
            dividerHeight = dpToPx(1f).toInt(),
            dividerColorInt = Color.GRAY,
            backgroundColorInt = Color.DKGRAY
        )
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
            return Intent(context, DecorationsActivity::class.java)
        }
    }

    //should be inside ViewModel/UIMapper
    private class DataHolder(
        space: Int,
        radius: Float,
        dividerHeight: Int,
        @ColorInt dividerColorInt: Int,
        @ColorInt backgroundColorInt: Int
    ) {

        private val singleItemDecoration: ItemDecoration<Cell<*>>
        private val topItemDecoration: ItemDecoration<Cell<*>>
        private val middleItemDecoration: ItemDecoration<Cell<*>>
        private val bottomItemDecoration: ItemDecoration<Cell<*>>

        init {
            singleItemDecoration = SampleItemDecoration(
                type = SampleItemDecoration.Type.SINGLE,
                radius = radius,
                dividerHeight = dividerHeight,
                dividerColorInt = dividerColorInt,
                backgroundColorInt = backgroundColorInt,
                top = space,
                bottom = space,
                start = space,
                end = space
            )
            topItemDecoration = SampleItemDecoration(
                type = SampleItemDecoration.Type.TOP,
                radius = radius,
                dividerHeight = dividerHeight,
                dividerColorInt = dividerColorInt,
                backgroundColorInt = backgroundColorInt,
                top = space,
                start = space,
                end = space,
                bottom = dividerHeight
            )
            middleItemDecoration = SampleItemDecoration(
                type = SampleItemDecoration.Type.MIDDLE,
                radius = radius,
                dividerHeight = dividerHeight,
                dividerColorInt = dividerColorInt,
                backgroundColorInt = backgroundColorInt,
                start = space,
                end = space,
                bottom = dividerHeight
            )
            bottomItemDecoration = SampleItemDecoration(
                type = SampleItemDecoration.Type.BOTTOM,
                radius = radius,
                dividerHeight = dividerHeight,
                dividerColorInt = dividerColorInt,
                backgroundColorInt = backgroundColorInt,
                bottom = space,
                start = space,
                end = space
            )
        }

        //should be inside ViewModel/UIMapper
        fun generateData(): List<Cell<*>> {
            val ids = (0..(Random.nextInt(10))).filterIndexed { index, _ ->
                index == 0 || Random.nextBoolean()
            }
            val size = ids.size
            return ids.mapIndexed { index, id ->
                val name: String
                val decoration: ItemDecoration<Cell<*>>
                when {
                    size == 1 -> {
                        name = "Single Cell id=$id"
                        decoration = singleItemDecoration
                    }
                    index == 0 -> {
                        name = "Top Cell id=$id"
                        decoration = topItemDecoration
                    }
                    index == size - 1 -> {
                        name = "Bottom Cell id=$id"
                        decoration = bottomItemDecoration
                    }
                    else -> {
                        name = "Middle Cell id=$id"
                        decoration = middleItemDecoration
                    }
                }
                DecorationsCell(
                    data = DecorationsUI(
                        id = id.toString(),
                        name = name
                    ),
                    decoration = decoration
                )
            }
        }
    }
}