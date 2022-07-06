package com.themovieviewer.presentation.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.themovieviewer.core.model.data.CastCrew
import com.themovieviewer.databinding.WidgetPeopleActingBinding

class ActingAdapter : PagingDataAdapter<CastCrew, ActingAdapter.ViewHolder>(diffCallback) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = WidgetPeopleActingBinding.inflate(inflater, parent, false)
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

    lateinit var onItemClick: (CastCrew) -> Unit

    private fun onClick(position: Int) {
        if (::onItemClick.isInitialized) {
            val castCrew = getItem(position)
            castCrew?.let { onItemClick(it) }
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<CastCrew>() {
            override fun areItemsTheSame(oldItem: CastCrew, newItem: CastCrew): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CastCrew, newItem: CastCrew): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ViewHolder(
        private val binding: WidgetPeopleActingBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(item: CastCrew?) {
            binding.model = item
            binding.executePendingBindings()
        }
    }
}
