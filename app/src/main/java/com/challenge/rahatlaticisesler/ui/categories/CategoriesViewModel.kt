package com.challenge.rahatlaticisesler.ui.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.challenge.rahatlaticisesler.data.models.Category
import com.challenge.rahatlaticisesler.data.repo.RelaxingSoundsRepository
import com.challenge.rahatlaticisesler.utils.CoroutineHelper

class CategoriesViewModel(private val repository: RelaxingSoundsRepository) : ViewModel() {

    //We are using "mutable" live data so only this class should be able to change its value
    private val _categories = MutableLiveData<List<Category>>()

    val categories: LiveData<List<Category>>
        get() = _categories

    fun getCategories() {
        CoroutineHelper.doAsyncWork(
            { repository.getCategories() },
            { categories ->
                _categories.value = categories
            })
    }
}