package com.development.task.mvvmproductapp.list

import android.support.v4.view.ViewCompat
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.development.task.mvvmproductapp.R
import com.development.task.mvvmproductapp.data.PostWithUser
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_item.view.*


class PostListAdapter(private val picasso: Picasso)
    : ListAdapter<PostWithUser, PostListAdapter.ListViewHolder>(PostWithUserDC()) {

    var interaction: Interaction? = null

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ) = ListViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item, parent, false), interaction)

    override fun onBindViewHolder(
            holder: ListViewHolder,
            position: Int
    ) = holder.bind(getItem(position), picasso)

    fun swapData(data: List<PostWithUser>) {
        submitList(data.toMutableList())
    }

    inner class ListViewHolder(
            itemView: View,
            private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView), OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val clicked = getItem(adapterPosition)
            interaction?.postClicked(clicked, itemView.tvTitle, itemView.tvPrice, itemView.ivAvatar)
        }

        fun bind(item: PostWithUser, picasso: Picasso) = with(itemView) {
            tvTitle.text = item.postTitle
            tvPrice.text = item.getFormattedPostBody()
            picasso.load(item.getAvatarPhoto())
                    .into(itemView.ivAvatar)

            //SharedItem transition
            ViewCompat.setTransitionName(tvTitle, item.postTitle)
            ViewCompat.setTransitionName(tvPrice, item.postBody)
            ViewCompat.setTransitionName(ivAvatar, item.getAvatarPhoto())
        }
    }

    interface Interaction {
        fun postClicked(
                post: PostWithUser,
                tvTitle: TextView,
                tvBody: TextView,
                ivAvatar: ImageView)
    }

    private class PostWithUserDC : DiffUtil.ItemCallback<PostWithUser>() {
        override fun areItemsTheSame(oldItem: PostWithUser, newItem: PostWithUser) = oldItem.postId == newItem.postId

        override fun areContentsTheSame(oldItem: PostWithUser, newItem: PostWithUser) = oldItem == newItem
    }
}