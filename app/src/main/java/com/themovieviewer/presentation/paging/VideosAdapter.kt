package com.themovieviewer.presentation.paging

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.Nullable
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.ItemKeyProvider
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.themovieviewer.R
import com.themovieviewer.domain.model.Trailer
import com.themovieviewer.util.loadImage
import com.themovieviewer.util.loadThumbnail

class VideosAdapter : PagingDataAdapter<Trailer, VideosAdapter.MovieViewHolder>(diffCallback) {
    var tracker: SelectionTracker<Long>? = null
    var useTracker = false

    //region PagingDataAdapter
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindTo(getItem(position), position, tracker?.isSelected(position.toLong()) ?: false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(parent, ::onClick)
    }

    lateinit var onItemClick: (Trailer) -> Unit

    /**
     * Callback implementation to send back the selected GitHub user.
     */
    private fun onClick(position: Int) {
        if (::onItemClick.isInitialized) {
            val trailer = getItem(position)
            trailer?.let { onItemClick(it) }
        }
    }
    //endregion

    override fun getItemViewType(position: Int): Int {
        return R.layout.widget_video
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
        val diffCallback = object : DiffUtil.ItemCallback<Trailer>() {
            override fun areItemsTheSame(oldItem: Trailer, newItem: Trailer): Boolean {
                return oldItem.id == newItem.id
            }

            /**
             * Note that in kotlin, == checking on data classes compares all contents, but in Java,
             * typically you'll implement Object#equals, and use it to compare object contents.
             */
            override fun areContentsTheSame(oldItem: Trailer, newItem: Trailer): Boolean {
                return oldItem == newItem
            }
        }
    }
    //endregion

    //region viewHolder
    inner class MovieViewHolder(parent: ViewGroup, private val onItemClick: (Int) -> Unit) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.widget_video, parent, false)
    ) {
        var trailer: Trailer? = null
        var position: Int? = null

        private val thumbnailView = itemView.findViewById<ImageView>(R.id.thumbnailView)

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
        fun bindTo(item: Trailer?, pos: Int, isSelect: Boolean) {

            trailer = item
            position = pos
            with(itemView) {
                item?.let {

                    thumbnailView.loadThumbnail(it.key)
                    thumbnailView.clipToOutline = true
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
    class SelectionKeyProvider(private val videosAdapter: VideosAdapter) : ItemKeyProvider<Long>(
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
                if (holder is VideosAdapter.MovieViewHolder) {
                    return holder.getItemDetails()
                }
            }
            return null
        }
    }
}
