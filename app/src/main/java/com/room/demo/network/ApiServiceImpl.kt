package com.room.demo.network

import com.room.demo.model.Post
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiService: ApiService) {

    suspend fun getPost():List<Post> = apiService.getPost()
}