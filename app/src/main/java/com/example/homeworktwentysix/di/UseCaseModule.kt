package com.example.homeworktwentysix.di

import com.example.homeworktwentysix.domain.repository.GetCategoryRepository
import com.example.homeworktwentysix.domain.useCase.GetCategoriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetCategoriesUseCase(getCategoryRepository: GetCategoryRepository): GetCategoriesUseCase {
        return GetCategoriesUseCase(getCategoryRepository)
    }

}