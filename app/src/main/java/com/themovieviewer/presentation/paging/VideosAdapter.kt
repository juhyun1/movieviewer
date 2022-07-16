package com.themovieviewer.presentation.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.themovieviewer.R
import com.themovieviewer.core.model.data.Trailer
import com.themovieviewer.databinding.WidgetVideoBinding

class VideosAdapter : PagingDataAdapter<Trailer, VideosAdapter.ViewHolder>(diffCallback) {

    //region PagingDataAdapter
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = WidgetVideoBinding.inflate(inflater, parent, false)
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

    lateinit var onItemClick: (Trailer) -> Unit

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

        val diffCallback = object : DiffUtil.ItemCallback<Trailer>() {
            override fun areItemsTheSame(oldItem: Trailer, newItem: Trailer): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Trailer, newItem: Trailer): Boolean {
                return oldItem == newItem
            }
        }
    }
    //endregion

    inner class ViewHolder(
        private val binding: WidgetVideoBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(model: Trailer?) {
            binding.model = model
            binding.executePendingBindings()
        }
    }
}
