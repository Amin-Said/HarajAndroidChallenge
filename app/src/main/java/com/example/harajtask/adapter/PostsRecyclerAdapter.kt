package com.example.harajtask.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.harajtask.R
import com.example.harajtask.databinding.PostItemBinding
import com.example.harajtask.model.Post
import com.example.harajtask.utils.Constants.DayMeasure
import com.example.harajtask.utils.Constants.HourMeasure
import com.example.harajtask.utils.Constants.MinuteMeasure
import com.example.harajtask.utils.getPostWhenTime
import java.util.*

class PostsRecyclerAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Post>() {

        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.username == newItem.username
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return PostsViewHolder(
            PostItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PostsViewHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<Post>) {
        differ.submitList(list)
    }

    class PostsViewHolder
    constructor(
        private val binding: PostItemBinding,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Post) = with(binding.root) {
            binding.root.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }

            // to get since from time data
            var sinceFrom = ""
            val map = item.date.getPostWhenTime(Calendar.getInstance().time)
            for ((k, v) in map) {
                when (k) {
                    DayMeasure -> {
                        sinceFrom =
                            binding.root.resources.getQuantityString(R.plurals.days_count, v,v)
                    }
                    HourMeasure -> {
                        sinceFrom =
                            binding.root.resources.getQuantityString(R.plurals.hours_count, v,v)
                    }
                    MinuteMeasure -> {
                        sinceFrom =
                            binding.root.resources.getQuantityString(R.plurals.minutes_count, v,v)
                    }
                }
            }

            // pass data to item views
            binding.tvTitle.text = item.title
            binding.tvTime.text = sinceFrom
            binding.tvUser.text = item.username
            binding.tvCity.text = item.city
            if (item.commentCount > 0) {
                binding.tvComments.visibility = View.VISIBLE
                binding.commentsIv.visibility = View.VISIBLE
                binding.tvComments.text = "(${item.commentCount})"
            }

            // to make image with rounded corners
            val radius =
                binding.root.context.resources.getDimensionPixelSize(R.dimen.post_image_corner)

            Glide.with(binding.root.context)
                .load(item.thumbURL)
                .transform(CenterCrop(), RoundedCorners(radius))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.postImage)


        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: Post)
    }
}

