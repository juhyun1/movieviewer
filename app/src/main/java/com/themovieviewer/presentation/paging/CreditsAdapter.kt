package com.themovieviewer.presentation.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.themovieviewer.databinding.WidgetCreditsVerticalBinding
import com.themovieviewer.core.data.network.model.CreditsCastCrewDto

class CreditsAdapter : PagingDataAdapter<CreditsCastCrewDto, CreditsAdapter.ViewHolder>(diffCallback) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = WidgetCreditsVerticalBinding.inflate(inflater, parent, false)
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

    lateinit var onItemClick: (CreditsCastCrewDto) -> Unit

    private fun onClick(position: Int) {
        if (::onItemClick.isInitialized) {
            val credits = getItem(position)
            credits?.let { onItemClick(it) }
        }
    }

    companion object {

        val diffCallback = object : DiffUtil.ItemCallback<CreditsCastCrewDto>() {
            override fun areItemsTheSame(oldItem: CreditsCastCrewDto, newItem: CreditsCastCrewDto): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CreditsCastCrewDto, newItem: CreditsCastCrewDto): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ViewHolder(
        private val binding: WidgetCreditsVerticalBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(model: CreditsCastCrewDto?) {
            binding.model = model
            binding.executePendingBindings()
        }
    }
}
