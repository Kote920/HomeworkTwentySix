package com.example.homeworktwentysix.data.remote.service

import com.example.homeworktwentysix.data.common.Resource

import com.example.homeworktwentysix.data.remote.model.CategoryDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoriesService {
    @GET("6f722f19-3297-4edd-a249-fe765e8104db?")
    suspend fun fetchData(@Query("search") searchQuery: String): Response<List<CategoryDto>>



}