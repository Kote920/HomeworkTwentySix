package com.example.homeworktwentysix.data.repository

import android.util.Log.d
import com.example.homeworktwentysix.data.common.Resource
import com.example.homeworktwentysix.data.remote.mapper.toDomain
import com.example.homeworktwentysix.data.remote.service.CategoriesService
import com.example.homeworktwentysix.domain.model.Category
import com.example.homeworktwentysix.domain.repository.GetCategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCategoryRepositoryImpl @Inject constructor(private val service: CategoriesService) :
    GetCategoryRepository {

    override suspend fun getCategories(title: String): Flow<Resource<List<Category>>> =
        withContext(Dispatchers.IO) {


            flow {
                try {
                    emit(Resource.Loading())
                    val response = service.fetchData(title)
                    if (response.isSuccessful) {
                        val categories = response.body()!!
                        emit(Resource.Success(categories.map { it.toDomain() }))
                        d("SuccessResultList", categories.toString())
                    } else {

                        emit(Resource.Failed("Failed!! with no Exception"))

                    }


                } catch (e: Exception) {
                    emit(Resource.Failed("Failed!"))
                    d("errorRepository", e.toString())
                }

            }


        }

}