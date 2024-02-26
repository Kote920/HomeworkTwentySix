package com.example.homeworktwentysix.domain.useCase

import com.example.homeworktwentysix.domain.repository.GetCategoryRepository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(private val repository: GetCategoryRepository) {

    suspend operator fun invoke() = repository.getCategories("getAll")
}