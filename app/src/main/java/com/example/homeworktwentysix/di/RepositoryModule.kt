package com.example.homeworktwentysix.di

import com.example.homeworktwentysix.data.remote.service.CategoriesService
import com.example.homeworktwentysix.data.repository.GetCategoryRepositoryImpl
import com.example.homeworktwentysix.domain.repository.GetCategoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideGetClothesRepository(
        categoriesService: CategoriesService
    ): GetCategoryRepository {
        return GetCategoryRepositoryImpl(
            categoriesService
        )
    }
}