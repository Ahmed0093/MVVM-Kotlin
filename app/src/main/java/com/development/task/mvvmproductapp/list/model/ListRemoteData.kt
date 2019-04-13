package com.development.task.mvvmproductapp.list.model

import com.development.task.mvvmproductapp.data.remote.ProductService

class ListRemoteData(private val productService: ProductService) : ListDataContract.Remote {
    override fun getProducts() = productService.getProducts()
}