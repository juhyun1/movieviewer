package com.themovieviewer.presentation.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.themovieviewer.R
import com.themovieviewer.network.model.CreditsCastCrewDto
import com.themovieviewer.util.loadImage

class CreditsAdapter: PagingDataAdapter<CreditsCastCrewDto, CreditsAdapter.ViewHolder>(CreditsAdapter.diffCallback) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent, ::onClick)
    }

    lateinit var onItemClick: (CreditsCastCrewDto) -> Unit

    /**
     * Callback implementation to send back the selected GitHub user.
     */
    private fun onClick(position: Int) {
        if (::onItemClick.isInitialized) {
            val credits = getItem(position)
            credits?.let { onItemClick(it) }
        }
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
        val diffCallback = object : DiffUtil.ItemCallback<CreditsCastCrewDto>() {
            override fun areItemsTheSame(oldItem: CreditsCastCrewDto, newItem: CreditsCastCrewDto): Boolean {
                return oldItem.id == newItem.id
            }

            /**
             * Note that in kotlin, == checking on data classes compares all contents, but in Java,
             * typically you'll implement Object#equals, and use it to compare object contents.
             */
            override fun areContentsTheSame(oldItem: CreditsCastCrewDto, newItem: CreditsCastCrewDto): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ViewHolder(parent: ViewGroup, private val onItemClick: (Int) -> Unit) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.widget_credits_vertical, parent, false)
    ) {
        private val poster = itemView.findViewById<ImageView>(R.id.profile)
//        private val originalTitle = itemView.findViewById<TextView>(R.id.originalTitle)
//        private val releaseDate = itemView.findViewById<TextView>(R.id.releaseDate)
//        private val overView = itemView.findViewById<TextView>(R.id.overView)


        /**
         * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
         * ViewHolder when Item is loaded.
         */
        fun bindTo(item: CreditsCastCrewDto?) {
            with(itemView) {
                item?.let{
                    if (it.profile_path != null) {
                        poster.loadImage(it.profile_path)
                        poster.clipToOutline = true;
                    }
//                    originalTitle.text = it.original_title
//                    releaseDate.text = it.release_date
//                    overView.text = it.overview
                }

                setOnClickListener {
                    if (layoutPosition != RecyclerView.NO_POSITION) {
                        onItemClick(layoutPosition)
                    }
                }
            }
        }
    }
}

