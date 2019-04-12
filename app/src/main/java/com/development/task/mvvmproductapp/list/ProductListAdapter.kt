package com.development.task.mvvmproductapp.list

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
import com.development.task.mvvmproductapp.data.local.ProductModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_item.view.*


class ProductListAdapter(private val picasso: Picasso)
    : ListAdapter<ProductModel, ProductListAdapter.ListViewHolder>(PostWithUserDC()) {

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

    fun swapData(data: List<ProductModel>) {
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

        fun bind(item: ProductModel, picasso: Picasso) = with(itemView) {
            tvTitle.text = item.title
            tvPrice.text = item.price.toString()
            picasso.load(item.image.link)
                .resize(item.image.height.toInt(),item.image.width.toInt())
                    .into(itemView.ivAvatar)
        }
    }

    interface Interaction {
        fun postClicked(
            post: ProductModel,
            tvTitle: TextView,
            tvBody: TextView,
            ivAvatar: ImageView)
    }

    private class PostWithUserDC : DiffUtil.ItemCallback<ProductModel>() {
        override fun areItemsTheSame(oldItem: ProductModel, newItem: ProductModel) = oldItem.productId == newItem.productId

        override fun areContentsTheSame(oldItem: ProductModel, newItem: ProductModel) = oldItem == newItem
    }
}