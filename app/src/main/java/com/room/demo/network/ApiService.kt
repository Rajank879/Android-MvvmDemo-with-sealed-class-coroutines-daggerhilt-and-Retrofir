package com.room.demo.network

import com.room.demo.model.Post
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPost():List<Post>
}