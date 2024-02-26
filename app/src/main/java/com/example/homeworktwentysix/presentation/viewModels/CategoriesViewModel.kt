package com.example.homeworktwentysix.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworktwentysix.data.common.Resource
import com.example.homeworktwentysix.domain.useCase.GetCategoriesUseCase
import com.example.homeworktwentysix.domain.useCase.SearchUseCase
import com.example.homeworktwentysix.presentation.mapper.toPresentation
import com.example.homeworktwentysix.presentation.model.CategoryUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val searchUseCase: SearchUseCase
) :
    ViewModel() {


    private val _categoriesFlow = MutableSharedFlow<Resource<List<CategoryUI>>>()
    val categoriesFlow: SharedFlow<Resource<List<CategoryUI>>> = _categoriesFlow.asSharedFlow()


    private val _foundFlow = MutableSharedFlow<Resource<List<CategoryUI>>>()
    val foundFlow: SharedFlow<Resource<List<CategoryUI>>> = _foundFlow.asSharedFlow()

    fun getCategoriesList() {
        viewModelScope.launch {
            getCategoriesUseCase.invoke().collect() {

                when (it) {
                    is Resource.Loading -> _categoriesFlow.emit(Resource.Loading())
                    is Resource.Success -> {
                        _categoriesFlow.emit(Resource.Success(it.responseData.map { category -> category.toPresentation() }))


                    }

                    is Resource.Failed -> _categoriesFlow.emit(Resource.Failed(it.message))
                }
            }
        }
    }

    fun search(title: String){

        viewModelScope.launch {
            searchUseCase.invoke(title).collect{
                when (it) {
                    is Resource.Loading -> _foundFlow.emit(Resource.Loading())
                    is Resource.Success -> {
                        _foundFlow.emit(Resource.Success(it.responseData.map { category -> category.toPresentation() }))


                    }

                    is Resource.Failed -> _foundFlow.emit(Resource.Failed(it.message))
                }
            }
        }

    }

}