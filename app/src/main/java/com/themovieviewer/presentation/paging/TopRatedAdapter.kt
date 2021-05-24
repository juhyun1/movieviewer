package com.themovieviewer.presentation.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.themovieviewer.R
import com.themovieviewer.momain.model.Movie
import com.themovieviewer.util.loadImage

class TopRatedAdapter : PagingDataAdapter<Movie, MovieViewHolder>(diffCallback) {

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(parent)
    }

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
}

class MovieViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.widget_movie_card, parent, false)
) {
    var movie: Movie? = null
    private val poster = itemView.findViewById<ImageView>(R.id.poster)
    private val originalTitle = itemView.findViewById<TextView>(R.id.originalTitle)
    private val releaseDate = itemView.findViewById<TextView>(R.id.releaseDate)

    /**
     * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
     * ViewHolder when Item is loaded.
     */
    fun bindTo(item: Movie?) {

        movie = item
        item?.let{
            if (it.poster_path != null) {
                poster.loadImage(it.poster_path)
                poster.clipToOutline = true;
            }
            originalTitle.text = it.original_title
            releaseDate.text = it.release_date
        }
    }
}
