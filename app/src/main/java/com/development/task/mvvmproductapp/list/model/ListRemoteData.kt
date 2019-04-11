package com.development.task.mvvmproductapp.list.model

import com.development.task.mvvmproductapp.data.remote.PostService

class ListRemoteData(private val postService: PostService) : ListDataContract.Remote {

//    override fun getUsers() = postService.getUsers()

    override fun getPosts() = postService.getPosts()
}