[![Maven Central](https://img.shields.io/maven-central/v/io.github.netcosports.compositeadapter/composite-adapter?color=orange)](https://repo1.maven.org/maven2/io/github/netcosports/compositeadapter/composite-adapter/)
[![License](https://img.shields.io/badge/License-Apache%202.0-orange.svg)](http://www.apache.org/licenses/LICENSE-2.0)

# CompositeAdapter
The `CompositeAdapter` extends `ListAdapter` and delegates all UI logic to a `Cell<DATA, VIEW_HOLDER>` created by the `ViewModel`.
`Cell`s handles all work with `ViewHolder`s, `DiffUtil`s and `ItemDecoration`s.

# Dependencies
```
repositories {
    mavenCentral()
}

implementation("io.github.netcosports.compositeadapter:composite-adapter:${compositeAdapterVersion}")
```

# Samples
| Sample | Description |
| ------ | ----------- |
| [Basic](https://github.com/netcosports/CompositeAdapter_Android/tree/main/samples/basic/src/main/java/com/originsdigital/compositeadapter/sample/basic/ui) | A simple setup how to create and show `Cell` inside `CompositeAdapter` |
| [Decorations](https://github.com/netcosports/CompositeAdapter_Android/tree/main/samples/decorations/src/main/java/com/originsdigital/compositeadapter/sample/decorations/ui) | Using a different `ItemDecoration` for each `Cell` |
| [Different bindings](https://github.com/netcosports/CompositeAdapter_Android/tree/main/samples/different-bindings/src/main/java/com/originsdigital/compositeadapter/sample/differentbindings/ui) | Using `ViewBinding`/`DataBinding`/`CustomViews` |
| [Inner RecyclerView/Webview/VideoPlayer<br/>or other complex view](https://github.com/netcosports/CompositeAdapter_Android/tree/main/samples/inner-recyclerview/src/main/java/com/originsdigital/compositeadapter/sample/innerrecyclerview/ui) | Handle binding for complex `views` via `payload` and save the scroll state of inner `RecyclerViews` |
| [State as Cells](https://github.com/netcosports/CompositeAdapter_Android/tree/main/samples/state-as-cells/features/home/ui/src/main/java/com/originsdigital/compositeadapter/stateascells/home/ui) | Show all requests (including Loading/Error/Empty/Data states and SwipeRefresh/Reload/Error actions) in a single `RecyclerView` without any additional `Views` |

# Usage
## 1. Implement [SampleCell](https://github.com/netcosports/CompositeAdapter_Android/blob/main/samples/basic/src/main/java/com/originsdigital/compositeadapter/sample/basic/ui/cell/SampleCell.kt):
<pre>
data class SampleCell(
    override val data: SampleUI, // <b>Must be kotlin data class</b> or with correct <b>equals</b>
    override val decoration: ItemDecoration? = null, // <b>ItemDecoration</b> for this <b>SampleCell</b> instance only
    override val onClickListener: ((GenericClickItem&lt;SampleUI&gt;) -&gt; Unit)? = null // Root <b>View.OnClickListener</b>
) : Cell&lt;SampleUI, SampleCell.SampleViewHolder&gt; {

    override val uniqueId: String = data.id // <b>Must be unique</b> for this <b>viewType</b>
    override val viewType: Int = R.layout.sample_cell // Can be generated via <b>ids.xml</b>

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): SampleViewHolder {
        return SampleViewHolder(SampleCellBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.binding.text.text = data.name
    }

    class SampleViewHolder(
        val binding: SampleCellBinding
    ) : RecyclerView.ViewHolder(binding.root)
}
</pre>
## 2. Setup RecyclerView:
<pre>
// You don't need to list the supported <b>ViewTypes</b> and <b>ViewHolders</b> for the <b>CompositeAdapter</b>.
val adapter = CompositeAdapter()
recyclerView.adapter = adapter
recyclerView.layoutManager = TODO("layoutManager")
// Each <b>Cell</b> can have their own <b>ItemDecoration</b>.
// To activate this functionality, don't forget to register <b>CompositeItemDecoration</b>.
recyclerView.addItemDecoration(CompositeItemDecoration())
</pre>
## 3. Submit Data:
<pre>
val items: List&lt;GenericCell&gt; = listOf(SampleCell(...), HorizontallStoriesCell(...), TitleCell(...), NewsCell(...), ...)
adapter.submitList(items)
</pre>

That's all.

# ItemDecorations
Forget about `androidx.recyclerview.widget.ItemDecoration` and use [`com.originsdigital.compositeadapter.decoration.ItemDecoration`](https://github.com/netcosports/CompositeAdapter_Android/blob/main/composite-adapter/src/main/java/com/originsdigital/compositeadapter/decoration/ItemDecoration.kt) with the additional parameter `Cell` instead.
Each `Cell` can have their own `ItemDecoration` that only affects them.
- [SpaceItemDecoration (from the library)](https://github.com/netcosports/CompositeAdapter_Android/blob/main/composite-adapter/src/main/java/com/originsdigital/compositeadapter/decoration/SpaceItemDecoration.kt)
- [BackgroundItemDecoration (from the sample)](https://github.com/netcosports/CompositeAdapter_Android/blob/main/samples/decorations/src/main/java/com/originsdigital/compositeadapter/sample/decorations/ui/decorations/SampleItemDecoration.kt)

# Advanced Usage
In most cases we don't need a separate `ViewHolder` for each `Cell`, so we can remove this copy-paste, for example with the base implementation of `Cell`.
## 1. Create a base ViewHolder:
Forget about `ViewHolder`s for each `viewType`, you don't need it anymore. Instead, implement a simple and consistent `ViewHolder`, for example:

### [ViewBinding](https://github.com/netcosports/CompositeAdapter_Android/blob/main/samples/different-bindings/src/main/java/com/originsdigital/compositeadapter/sample/differentbindings/ui/cell/viewbinding/base/ViewBindingViewHolder.kt):
<pre>
class ViewBindingViewHolder&lt;VIEW_BINDING : ViewBinding&gt;(
    val binding: VIEW_BINDING
) : ViewHolder(binding.root)
</pre>

### [DataBinding](https://github.com/netcosports/CompositeAdapter_Android/blob/main/samples/different-bindings/src/main/java/com/originsdigital/compositeadapter/sample/differentbindings/ui/cell/databinding/base/DataBindingViewHolder.kt):
<pre>
class DataBindingViewHolder&lt;DATA_BINDING : ViewDataBinding&gt;(
    val binding: DATA_BINDING
) : ViewHolder(binding.root) {

    companion object {
        fun &lt;DATA_BINDING : ViewDataBinding&gt; create(
            inflater: LayoutInflater,
            layoutResId: Int,
            parent: ViewGroup
        ): DataBindingViewHolder&lt;DATA_BINDING&gt; {
            return DataBindingViewHolder(
                DataBindingUtil.inflate(
                    inflater,
                    layoutResId,
                    parent,
                    false
                ) as DATA_BINDING
            )
        }
    }
}
</pre>
and use this `ViewHolder` in all `Cell`s.

## 2. Create a base Cell:
If you are using `ViewBinding` or `DataBinding`, its good idea to have a base `Cell` with default implementation of `onCreateViewHolder` and `onBindViewHolder`(the last one is for `DataBinding` only).

### [ViewBinding](https://github.com/netcosports/CompositeAdapter_Android/blob/main/samples/different-bindings/src/main/java/com/originsdigital/compositeadapter/sample/differentbindings/ui/cell/viewbinding/base/ViewBindingCell.kt):
<pre>
abstract class ViewBindingCell&lt;DATA, VIEW_BINDING : ViewBinding&gt;
    : Cell&lt;DATA, ViewBindingViewHolder&lt;VIEW_BINDING&gt;> {

    abstract fun createViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): VIEW_BINDING

    final override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewBindingViewHolder&lt;VIEW_BINDING&gt; {
        return ViewBindingViewHolder(createViewBinding(inflater, parent, viewType))
    }
}
</pre>

### [DataBinding](https://github.com/netcosports/CompositeAdapter_Android/blob/main/samples/different-bindings/src/main/java/com/originsdigital/compositeadapter/sample/differentbindings/ui/cell/databinding/base/DataBindingCell.kt):
<pre>
abstract class DataBindingCell&lt;DATA, DATA_BINDING : ViewDataBinding&gt;
    : Cell&lt;DATA, DataBindingViewHolder&lt;DATA_BINDING&gt;&gt; {

    @get:LayoutRes
    abstract val layoutId: Int

    override val viewType: Int get() = layoutId

    final override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): DataBindingViewHolder&lt;DATA_BINDING&gt; {
        return DataBindingViewHolder.create(inflater, layoutId, parent)
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder&lt;DATA_BINDING&gt;, position: Int) {
        (holder.binding).apply {
            setVariable(BR.item, data)
            executePendingBindings()
        }
    }
}
</pre>

Now you don't need to copy and paste this code into every `Cell`.

## 3. Create a real Cell using basic implementations
### ViewBinding:
<pre>
data class SampleCell(
    override val data: SampleEntity, // <b>Must be kotlin data class</b> or with correct <b>equals</b>
    override val decoration: ItemDecoration? = null, // <b>ItemDecoration</b> for this <b>SampleCell</b> instance only
    override val onClickListener: ((GenericClickItem&lt;SampleEntity&gt;) -> Unit)? = null // Root View.OnClickListener
) : ViewBindingCell&lt;SampleEntity, SampleCellBinding&gt;() {

    override val uniqueId: String = data.id // <b>Must be unique</b> for this <b>viewType</b>
    override val viewType: Int = R.layout.sample_cell // Can be generated via <b>ids.xml</b>

    override fun createViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): SampleCellBinding {
        return SampleCellBinding.inflate(inflater, parent, false)
    }

    override fun onBindViewHolder(
        holder: ViewBindingViewHolder&lt;SampleCellBinding&gt;,
        position: Int
    ) {
        holder.binding.text.text = data.text
    }
}
</pre>

### DataBinding:
<pre>
data class SampleCell(
    override val data: SampleEntity, // <b>Must be kotlin data class</b> or with correct <b>equals</b>
    override val decoration: ItemDecoration? = null, // <b>ItemDecoration</b> for this <b>SampleCell</b> instance only
    override val onClickListener: ((GenericClickItem&lt;SampleEntity&gt;) -> Unit)? = null // Root View.OnClickListener
) : DataBindingCell&lt;SampleEntity, SampleCellBinding&gt;() {

    override val uniqueId: String = data.id // <b>Must be unique</b> for this <b>viewType</b> (by default <b>viewType</b> == <b>layoutId</b>)
    override val layoutId: Int = R.layout.sample_cell // Can be generated via <b>ids.xml</b> (by default <b>viewType</b> == <b>layoutId</b>)

    // If you have all bindings inside the <b>R.layout.sample_cell</b>,
    // you don't need to override <b>onBindViewHolder</b> because everything is done inside the <b>DataBindingCell</b>
    // But you can move all bindings from <b>layout</b> to <b>onBindViewHolder</b>
//    override fun onBindViewHolder(
//        holder: DataBindingViewHolder&lt;SampleCellBinding&gt;,
//        position: Int
//    ) {
//        super.onBindViewHolder(holder, position)
//    }
}
</pre>

`Cell` has all methods related to `ViewHolder`, `DiffUtil.ItemCallback` and `ItemDecoration`, [the full list is here](https://github.com/netcosports/CompositeAdapter_Android/blob/main/composite-adapter/src/main/java/com/originsdigital/compositeadapter/cell/Cell.kt).