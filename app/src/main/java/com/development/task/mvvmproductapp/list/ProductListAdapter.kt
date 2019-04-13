package com.development.task.mvvmproductapp.list

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.development.task.mvvmproductapp.R
import com.development.task.mvvmproductapp.data.local.ProductModel
import kotlinx.android.synthetic.main.product_item.view.*

class ProductListAdapter (private val imageLoader: RequestManager) :
    ListAdapter<ProductModel, ProductListAdapter.ProductModelViewHolder>(ProductModelDC()) {

    var interaction: ProductListAdapter.Interaction? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductModelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.product_item,
            parent,
            false)
        return ProductModelViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductModelViewHolder, position: Int) {
        holder.bind(getItem(position), imageLoader, interaction)
    }

    private val movies: MutableList<ProductModel> = mutableListOf()

    interface Interaction {
        fun postClicked(
            post: ProductModel,
            tvTitle: TextView,
            tvBody: TextView,
            ivAvatar: ImageView
        )
    }
    fun getProductModelAt(position :Int) : ProductModel {
        return getItem(position)
    }


    class ProductModelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(productModel: ProductModel, imageLoader: RequestManager, listener: Interaction?) = with(itemView) {
            tvTitle.text = productModel.title
            tvPrice.text = productModel.price.toString()
                      imageLoader
                .load(productModel.image.link)
                           .placeholder(R.drawable.ic_mtrl_chip_checked_circle)
                           .apply(RequestOptions().override(productModel.image.width.toInt(), productModel.image.height.toInt()))
                           .into(ivAvatar)
            itemView.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    listener?.postClicked(productModel, itemView.tvTitle, itemView.tvPrice, itemView.ivAvatar)
                }
            })
        }

    }
    private class ProductModelDC : DiffUtil.ItemCallback<ProductModel>() {
        override fun areItemsTheSame(oldItem: ProductModel, newItem: ProductModel) = oldItem.productId == newItem.productId

        override fun areContentsTheSame(oldItem: ProductModel, newItem: ProductModel) = oldItem == newItem
    }
}