package com.themovieviewer.presentation.paging

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.ItemKeyProvider
import androidx.recyclerview.selection.Selection
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.themovieviewer.R
import com.themovieviewer.domain.model.Movie
import com.themovieviewer.util.loadImage

class MovieOneRowAdapter: PagingDataAdapter<Movie, MovieOneRowAdapter.MovieViewHolder>(diffCallback) {
    var tracker: SelectionTracker<Long>? = null

    //region PagingDataAdapter
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(parent, ::onClick)
    }

    lateinit var onItemClick: (Movie) -> Unit

    /**
     * Callback implementation to send back the selected GitHub user.
     */
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
        /**
         * This diff callback informs the PagedListAdapter how to compute list differences when new
         * PagedLists arrive.
         *
         * When you add a Cheese with the 'Add' button, the PagedListAdapter uses diffCallback to
         * detect there's only a single item difference from before, so it only needs to animate and
         * rebind a single view.
         *
         * @see DiffUtil
         */
        val diffCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            /**
             * Note that in kotlin, == checking on data classes compares all contents, but in Java,
             * typically you'll implement Object#equals, and use it to compare object contents.
             */
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
    //endregion

    //region viewHolder
    inner class MovieViewHolder(parent: ViewGroup, private val onItemClick: (Int) -> Unit) : RecyclerView.ViewHolder(
//    LayoutInflater.from(parent.context).inflate(R.layout.widget_movie_card, parent, false)
        LayoutInflater.from(parent.context).inflate(R.layout.widget_movie_main_horizontal, parent, false)
    ) {
        var movie: Movie? = null
        private val poster = itemView.findViewById<ImageView>(R.id.poster)
        private val originalTitle = itemView.findViewById<TextView>(R.id.originalTitle)
        private val releaseDate = itemView.findViewById<TextView>(R.id.releaseDate)
        private val overView = itemView.findViewById<TextView>(R.id.overView)


        /**
         * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
         * ViewHolder when Item is loaded.
         */
        fun bindTo(item: Movie?) {

            movie = item

            with(itemView) {
                item?.let{
                    if (it.poster_path != null) {
                        poster.loadImage(it.poster_path)
                        poster.clipToOutline = true;
                    }
                    originalTitle.text = it.original_title
                    releaseDate.text = it.release_date
                    overView.text = it.overview
                }

                tracker?.isSelected(itemId) ?: false
//                setOnClickListener {
//                    if (layoutPosition != RecyclerView.NO_POSITION) {
//                        onItemClick(layoutPosition)
//                    }
//                }
            }
        }
    }
    //endregion

    //region selection
    class SelectionKeyProvider(private val recyclerView: RecyclerView) : ItemKeyProvider<Long>(
        SCOPE_MAPPED
    ) {
        override fun getKey(position: Int): Long {
            val holder = recyclerView.findViewHolderForAdapterPosition(position)
            return holder?.itemId ?: throw IllegalStateException("No Holder")
        }

        override fun getPosition(key: Long): Int {
            val holder = recyclerView.findViewHolderForItemId(key)
            return if (holder is MovieOneRowAdapter.MovieViewHolder) {
                holder.adapterPosition
            } else {
                RecyclerView.NO_POSITION
            }
        }
    }

    class SelectionDetailsLookup(private val recyclerView: RecyclerView) : ItemDetailsLookup<Long>() {
        override fun getItemDetails(e: MotionEvent): ItemDetails<Long>? {
            val view = recyclerView.findChildViewUnder(e.x, e.y) ?: return null

            val holder = recyclerView.getChildViewHolder(view)
            return if (holder is MovieViewHolder) {
                object : ItemDetails<Long>() {
                    override fun getPosition(): Int {
                        return holder.adapterPosition
                    }

                    override fun getSelectionKey(): Long {
                        return holder.itemId
                    }
                }
            } else {
                null
            }
        }
    }

    class SelectionPredicate(private val recyclerView: RecyclerView) : SelectionTracker.SelectionPredicate<Long>() {
        override fun canSetStateForKey(key: Long, nextState: Boolean): Boolean {
            val holder = recyclerView.findViewHolderForItemId(key)
            return if (holder is MovieOneRowAdapter.MovieViewHolder) {
//                holder.element.text == "YES"
                true
            } else {
                false
            }
        }

        override fun canSetStateAtPosition(position: Int, nextState: Boolean): Boolean {
            return true
        }

        override fun canSelectMultiple(): Boolean {
            return true
        }
    }
    //endregion
}
