# CompositeAdapter_Android

# Dependencies

```
    object CompositeAdapter {
        const val compositeAdapterVersion = "1.0.0"

        const val compositeAdapter = "com.netcosports.composite-adapter:${compositeAdapterVersion}"
    }
```

#  Usage

##  ViewHolder

  - Forget about `ViewHolder`s for each `viewType`, you don't need it anymore. Implement a simple and single `ViewHolder`
  with `ViewBinding`/`Custom View` or `DataBinding` instead, for example:
```
    //for <b>ViewBinding</b> or <b>Custom View</b>
    class SampleCellViewHolder(view: View) : RecyclerView.ViewHolder(view)

    //or <b>DataBinding</b>
    class SampleDataBindingViewHolder(val bindings: ViewDataBinding) : RecyclerView.ViewHolder(bindings.root) {

        companion object {
            fun create(inflater: LayoutInflater, layoutResId: Int, parent: ViewGroup): DataBindingViewHolder {
                return DataBindingViewHolder(DataBindingUtil.inflate(inflater, layoutResId, parent, false))
            }
        }
    }
```
  and use this `ViewHolder` in all `Cell<T>`s.

##  Cell&lt;T&gt;

  - If you use `ViewBinding` or `DataBinding`, its good idea to have a base `Cell<T>` with default implementation of
  `onCreateViewHolder`.
  `ViewBinding`:
<pre>
    interface SampleViewBindingCell<T> : Cell<T> {

        override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            //we already have <b>layoutId</b> in the <b>Cell&lt;T&gt;</b>, so we can create a `SampleCellViewHolder` with `ViewBinding`
            return SampleCellViewHolder(inflater.inflate(layoutId, parent, false))
        }
    }
</pre>
  `DataBinding`:
<pre>
    interface SampleDataBindingCell<T> : Cell<T> {

        override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            //we already have a <b>layoutId</b> in the <b>Cell&lt;T&gt;</b>, so we can create a `DataBindingViewHolder` with `ViewDataBinding`
            return DataBindingViewHolder.create(inflater, layoutId, parent)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            (holder as DataBindingViewHolder).apply {
                //and we already have a <b>data</b> in the <b>Cell&lt;T&gt;</b>, just set it via `setVariable` and use it in the `layout`
                bindings.setVariable(BR.item, data)
                bindings.executePendingBindings()
            }
        }
    }
</pre>
  Now you don't need to copy and paste this code into every `Cell<T>`.

  - Then you can implement a simple `Cell<T>`, but forget about the `var` fields in the class for `data` field of the `Cell<T>`.
  You `must` use the `kotlin data class` (or with correct `equals`/`hashCode`) for the `data` field of the `Cell<T>`.
<pre>
    data class SampleCell(
        override val data: T, //<b>Must be kotlin data class</b> or with correct <b>equals</b>/<b>hashCode</b>
    ) : SampleViewBindingCell&lt;T&gt; {

        override val uniqueId: String = data.id //<b>Must be unique</b> for this <b>viewType</b> (by default <b>viewType</b> == <b>layoutId</b>)
        override val layoutId: Int = R.layout.layout_id //Can be generated via <b>layout_ids.xml</b> (by default <b>viewType</b> == <b>layoutId</b>)

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            //If you use <b>ViewBinding</b>, you can get stored <b>bindings</b> from the <b>ViewHolder</b> like this
            (holder.getViewBinding(SampleBinding::bind)).apply {
                //TODO this.root
            }
            //Or cast <b>holder.itemView</b> to your <b>Custom View</b>
            //Or cast <b>holder</b> to your <b>DataBindingViewHolder</b> with <b>ViewDataBinding</b>
            //Or cast <b>holder</b> to your custom <b>ViewHolder</b>
        }
    }
</pre>
  - But `Cell<T>` has all methods related to `ViewHolder` and `DiffUtil.ItemCallback`, the full list is here:
<pre>
    interface Cell&lt;T&gt; {

        val uniqueId: String //Must be unique for this viewType (by default viewType == layoutId)
        val data: T //Must be <b>kotlin data class</b> or with correct equals/hashCode

        @get:LayoutRes
        val layoutId: Int //Can be generated via layout_ids.xml if you use <b>Custom View</b> (by default viewType == layoutId)
        val viewType: Int get() = layoutId //Override if you have two <b>viewType</b>s for the same <b>layoutId</b>

        val decoration: ItemDecoration&lt;out Cell&lt;*&gt;&gt;? get() = null //<b>ItemDecoration</b> only for this <b>Cell&lt;T&gt;</b>

        val onClickListener: ((ClickItem&lt;T&gt;) -&gt; Unit)? get() = null

        fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

        fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List&lt;Any&gt;): Boolean = false
        fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)

        fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) = Unit
        fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) = Unit

        fun onViewRecycled(holder: RecyclerView.ViewHolder) = Unit

        fun areItemsTheSame(newItem: Cell&lt;*&gt;): Boolean = viewType == newItem.viewType && uniqueId == newItem.uniqueId
        fun areContentsTheSame(newItem: Cell&lt;*&gt;): Boolean = data == newItem.data
        fun getChangePayload(newItem: Cell&lt;*&gt;): Any? = null
        fun areDecorationsTheSame(newItem: Cell&lt;*&gt;): Boolean = decoration == newItem.decoration

        fun onClicked(context: Context, holder: RecyclerView.ViewHolder, position: Int) {
            onClickListener?.invoke(ClickItem(context, holder, position, data))
        }
    }
</pre>

##  CompositeAdapter & CompositeItemDecoration

  - Setup `RecyclerView` and submit data to adapter
<pre>
    val adapter = CompositeAdapter()
    recyclerView.adapter = adapter
    recyclerView.layoutManager = TODO("layoutManager")
    //Don't forget to register <b>CompositeItemDecoration</b> to use <b>ItemDecoration</b> for each <b>Cell&lt;T&gt;</b>
    recyclerView.addItemDecoration(CompositeItemDecoration())
    ...
    val items: List&lt;Cell&lt;*&gt;&gt; = TODO("data")
    adapter.submitList(items)
</pre>

  - Forget about `androidx.recyclerview.widget.ItemDecoration` and use
  `com.netcosports.composite.adapter.decoration.ItemDecoration<Cell<T>>` with the additional parameter `Cell<T>` instead.
```
    interface ItemDecoration<Item> {

        fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State,
            item: Item
        ) = Unit

        fun onDraw(canvas: Canvas,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State,
            item: Item
        ) = Unit

        fun onDrawOver(canvas: Canvas,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State,
            item: Item
        ) = Unit
    }
```
  For example colored background:
```
    class SampleDecoration(@ColorInt colorInt: Int, space: Int) : SpaceItemDecoration<Cell<*>>(
        top = space, bottom = space, start = space, end = space
    ) {

        private val itemBounds = Rect()
        private val dividerPaint: Paint = Paint().apply {
            this.color = colorInt
            this.style = Paint.Style.FILL
            this.isAntiAlias = true
        }

        override fun onDraw(
            canvas: Canvas,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State,
            item: Cell<*>
        ) {
            super.onDraw(canvas, view, parent, state, item)
            parent.layoutManager?.getDecoratedBoundsWithMargins(view, itemBounds)
            canvas.drawRect(
                itemBounds.left.toFloat(),
                itemBounds.top.toFloat(),
                itemBounds.right.toFloat(),
                itemBounds.bottom.toFloat(),
                dividerPaint
            )
        }
    }
```
  Or just use the existing `SpaceItemDecoration` if you don't need the `onDraw` methods.