package com.example.homeworktwentysix.domain.useCase

import com.example.homeworktwentysix.data.common.Resource
import com.example.homeworktwentysix.domain.model.Category
import com.example.homeworktwentysix.domain.repository.GetCategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val repository: GetCategoryRepository) {

    suspend operator fun invoke(title: String):Flow<Resource<List<Category>>> = flow {


        emit(Resource.Loading())
        repository.getCategories(title).collect{
            when(it){
                is Resource.Success -> {

                    val categoryList = it.responseData
                    val found = searchCategory(categoryList, title)
                    if (!found.isNullOrEmpty()){
                        emit(Resource.Success(found))
                    }else{
                        emit(Resource.Failed("Not found "))
                    }


                }
                is Resource.Failed  -> emit(Resource.Failed("Failed"))

                else -> {}
            }
        }



    }

    private fun searchCategory(categories: List<Category>?, searchName: String): List<Category> {
        val matchingCategories = mutableListOf<Category>()

        fun search(categories: List<Category>?) {
            categories?.forEach { category ->
                if (category.name?.contains(searchName, ignoreCase = true) == true) {
                    matchingCategories.add(category)
                }
                search(category.children)
            }
        }

        search(categories)
        return matchingCategories
    }

}