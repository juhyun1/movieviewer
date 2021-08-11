package com.themovieviewer.presentation.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.themovieviewer.R
import com.themovieviewer.databinding.WidgetMovieRecommendationsBinding
import com.themovieviewer.domain.model.Movie

class MovieRecommendationsAdapter : PagingDataAdapter<Movie, MovieRecommendationsAdapter.ViewHolder>(diffCallback) {
    //region PagingDataAdapter
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =  WidgetMovieRecommendationsBinding.inflate(inflater, parent, false)
        val viewHolder = ViewHolder(binding)

        with(viewHolder.itemView) {
            setOnClickListener {
                if (viewHolder.layoutPosition != RecyclerView.NO_POSITION) {
                    onClick(viewHolder.layoutPosition)
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
        return R.layout.widget_movie_recommendations
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
        private val binding: WidgetMovieRecommendationsBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(item: Movie?) {
            binding.model = item
            binding.executePendingBindings()
        }
    }
    //endregion
}
