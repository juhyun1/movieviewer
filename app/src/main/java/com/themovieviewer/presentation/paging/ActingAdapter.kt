package com.themovieviewer.presentation.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.themovieviewer.R
import com.themovieviewer.domain.model.CastCrew
import java.lang.Exception
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class ActingAdapter : PagingDataAdapter<CastCrew, ActingAdapter.ViewHolder>(diffCallback) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent, ::onClick)
    }

    lateinit var onItemClick: (CastCrew) -> Unit

    /**
     * Callback implementation to send back the selected GitHub user.
     */
    private fun onClick(position: Int) {
        if (::onItemClick.isInitialized) {
            val castCrew = getItem(position)
            castCrew?.let { onItemClick(it) }
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
        val diffCallback = object : DiffUtil.ItemCallback<CastCrew>() {
            override fun areItemsTheSame(oldItem: CastCrew, newItem: CastCrew): Boolean {
                return oldItem.id == newItem.id
            }

            /**
             * Note that in kotlin, == checking on data classes compares all contents, but in Java,
             * typically you'll implement Object#equals, and use it to compare object contents.
             */
            override fun areContentsTheSame(oldItem: CastCrew, newItem: CastCrew): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ViewHolder(parent: ViewGroup, private val onItemClick: (Int) -> Unit) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.widget_people_acting, parent, false)

    ) {
        private val releaseDate = itemView.findViewById<TextView>(R.id.tv_releaseDate)
        private val title = itemView.findViewById<TextView>(R.id.tv_title)
        private val character = itemView.findViewById<TextView>(R.id.tv_character)

        fun bindTo(item: CastCrew?) {
            with(itemView) {
                item?.let {
                    try {
                        if (it.release_date == null || it.release_date.isEmpty()) {
                            releaseDate.text = "-"
                        } else {
                            releaseDate.text = it.release_date.substring(0, it.release_date.indexOf("-"))
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                    title.text = it.title
                    character.text = it.character
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
