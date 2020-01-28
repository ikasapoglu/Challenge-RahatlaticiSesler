package com.challenge.rahatlaticisesler.ui.categorydetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.challenge.rahatlaticisesler.data.models.Sound
import com.challenge.rahatlaticisesler.data.repo.RelaxingSoundsRepository
import com.challenge.rahatlaticisesler.ui.SoundsViewModel
import com.challenge.rahatlaticisesler.utils.CoroutineHelper

class CategoryDetailsViewModel(private val repository: RelaxingSoundsRepository) : SoundsViewModel() {

    private val _categoryDetails = MutableLiveData<List<Sound>>()
    val categoryDetails: LiveData<List<Sound>>
        get() = _categoryDetails

    //Categories
    fun getCategoryDetails(categoryId: Int) {
        isLoading.value = true
        CoroutineHelper.doAsyncWork(
            { repository.getCategoryDetails(categoryId) },
            { sounds ->
                _categoryDetails.value = sounds
                isLoading.value = false
            }
        )
    }
}