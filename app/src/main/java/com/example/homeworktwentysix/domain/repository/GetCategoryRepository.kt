package com.example.homeworktwentysix.domain.repository

import com.example.homeworktwentysix.data.common.Resource
import com.example.homeworktwentysix.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface GetCategoryRepository {

    suspend fun getCategories(title: String): Flow<Resource<List<Category>>>

}