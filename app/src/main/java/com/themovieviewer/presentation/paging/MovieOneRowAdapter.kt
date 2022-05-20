package com.themovieviewer.presentation.paging

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.ItemKeyProvider
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.themovieviewer.R
import com.themovieviewer.core.model.data.Movie
import com.themovieviewer.databinding.WidgetMovieMainHorizontalBinding

class MovieOneRowAdapter : PagingDataAdapter<Movie, MovieOneRowAdapter.ViewHolder>(diffCallback) {
    var tracker: SelectionTracker<Long>? = null
    var useTracker = false

    //region PagingDataAdapter
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(getItem(position), position, tracker?.isSelected(position.toLong()) ?: false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = WidgetMovieMainHorizontalBinding.inflate(inflater, parent, false)
        val viewHolder = ViewHolder(binding)

        with(viewHolder.itemView) {
            if (!useTracker) {
                setOnClickListener {
                    if (viewHolder.layoutPosition != RecyclerView.NO_POSITION) {
                        onClick(viewHolder.layoutPosition)
                    }
                }
            }
        }

        return viewHolder
    }

    lateinit var onItemClick: (Movie) -> Unit

    private fun onClick(position: Int) {
        if (::onItemClick.isInitialized) {
            val movie = getItem(position)
            movie?.let { onItemClick(it) }
        }
    }
    //endregion

    override fun getItemViewType(position: Int): Int {
        return R.layout.widget_movie_main_horizontal
    }

    //region diffCallback
    companion object {

        val diffCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
    //endregion

    //region viewHolder
    inner class ViewHolder(
        private val binding: WidgetMovieMainHorizontalBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        var position: Int? = null
        private fun setSelected(isSelect: Boolean) {
            binding.selection.visibility = if (isSelect) View.VISIBLE else View.GONE
        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
            object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun getPosition(): Int = bindingAdapterPosition
                override fun getSelectionKey(): Long = position.toLong()
                override fun inSelectionHotspot(e: MotionEvent): Boolean {
                    return true
                }
            }

        fun bindTo(item: Movie?, pos: Int, isSelect: Boolean) {
            position = pos
            binding.model = item
            binding.executePendingBindings()

            setSelected(isSelect)

        }
    }
    //endregion

    //region selection
    class SelectionKeyProvider(private val movieOneRowAdapter: MovieOneRowAdapter) : ItemKeyProvider<Long>(
        SCOPE_MAPPED
    ) {

        override fun getKey(position: Int): Long {
            return position.toLong()
        }

        override fun getPosition(key: Long): Int {
            return key.toInt()
        }
    }

    class SelectionDetailsLookup(private val recyclerView: RecyclerView) : ItemDetailsLookup<Long>() {
        @Nullable
        override fun getItemDetails(e: MotionEvent): ItemDetails<Long>? {
            val view: View? = recyclerView.findChildViewUnder(e.x, e.y)
            if (view != null) {
                val holder: RecyclerView.ViewHolder = recyclerView.getChildViewHolder(view)
                if (holder is MovieOneRowAdapter.ViewHolder) {
                    return holder.getItemDetails()
                }
            }
            return null
        }
    }
}
