package com.challenge.rahatlaticisesler.ui.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.challenge.rahatlaticisesler.base.BaseViewModel
import com.challenge.rahatlaticisesler.data.models.Category
import com.challenge.rahatlaticisesler.data.repo.RelaxingSoundsRepository
import com.challenge.rahatlaticisesler.utils.CoroutineHelper

class CategoriesViewModel(private val repository: RelaxingSoundsRepository) : BaseViewModel() {

    //We are using "mutable" live data so only this class should be able to change its value
    private val _categories = MutableLiveData<List<Category>>()

    val categories: LiveData<List<Category>>
        get() = _categories

    fun getCategories() {
        isLoading.value = true
        CoroutineHelper.doAsyncWork(
            { repository.getCategories() },
            { categories ->
                _categories.value = categories
                isLoading.value = false
            })
    }
}