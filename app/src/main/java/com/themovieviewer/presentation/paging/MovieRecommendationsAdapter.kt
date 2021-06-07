package com.themovieviewer.presentation.paging

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.ItemKeyProvider
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.themovieviewer.R
import com.themovieviewer.domain.model.Movie
import com.themovieviewer.util.loadImage

class MovieRecommendationsAdapter : PagingDataAdapter<Movie, MovieRecommendationsAdapter.MovieViewHolder>(diffCallback) {
    var tracker: SelectionTracker<Long>? = null
    var useTracker = false

    //region PagingDataAdapter
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindTo(getItem(position), position, tracker?.isSelected(position.toLong()) ?: false)
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
        LayoutInflater.from(parent.context).inflate(R.layout.widget_movie_recommendations, parent, false)
    ) {
        var movie: Movie? = null
        var position: Int? = null
        private val backdropImage = itemView.findViewById<ImageView>(R.id.backdropImage)
        private val originalTitle = itemView.findViewById<TextView>(R.id.originalTitle)
        private val voteAverage = itemView.findViewById<TextView>(R.id.voteAverage)
        private val selectionView = itemView.findViewById<View>(R.id.selection)

        private fun setSelected(isSelect: Boolean) {
            selectionView.visibility = if (isSelect) View.VISIBLE else View.GONE
        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
            object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun getPosition(): Int = bindingAdapterPosition
                override fun getSelectionKey(): Long = position.toLong()
                override fun inSelectionHotspot(e: MotionEvent): Boolean {
                    return true
                }
            }

        /**
         * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
         * ViewHolder when Item is loaded.
         */
        fun bindTo(item: Movie?, pos: Int, isSelect: Boolean) {

            movie = item
            position = pos
            setSelected(isSelect)
            with(itemView) {
                item?.let {
                    if (it.backdrop_path != null) {
                        backdropImage.loadImage(it.backdrop_path)
                        backdropImage.clipToOutline = true
                    }

                    originalTitle.text = it.title
                    val text = (it.vote_average * 10).toInt().toString()
                    voteAverage.text = "$text %"
                }

                if (!useTracker) {
                    setOnClickListener {
                        if (layoutPosition != RecyclerView.NO_POSITION) {
                            onItemClick(layoutPosition)
                        }
                    }
                }
            }
        }
    }
    //endregion

    //region selection
    class SelectionKeyProvider(private val recommendationsAdapter: MovieRecommendationsAdapter) : ItemKeyProvider<Long>(
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
                if (holder is MovieRecommendationsAdapter.MovieViewHolder) {
                    return holder.getItemDetails()
                }
            }
            return null
        }
    }
}
