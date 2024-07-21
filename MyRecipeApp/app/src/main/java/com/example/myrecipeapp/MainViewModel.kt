package com.example.myrecipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _categoriesState = mutableStateOf(RecipeState())
    val categoriesState : State<RecipeState> = _categoriesState

    init {
        fetchCategories()
    }

    data class RecipeState(
        val loading: Boolean = false,
        val list: List<Category> = emptyList(),
        val error: String? = null
    )

    fun fetchCategories() {
        viewModelScope.launch {
            try {
                val response = recipeService.getCategories()
                _categoriesState.value = _categoriesState.value.copy(
                    loading = false,
                    error = null,
                    list = response.categories
                )

            } catch (e: Exception) {
                _categoriesState.value = _categoriesState.value.copy(
                    loading = false,
                    list = emptyList(),
                    error = "Error fetching Categories ${e.message}"
                )
            }
        }
    }

}