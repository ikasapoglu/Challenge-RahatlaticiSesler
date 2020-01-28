package com.challenge.rahatlaticisesler.ui.categorydetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.challenge.rahatlaticisesler.data.repo.RelaxingSoundsRepository

class CategoryDetailsViewModelFactory(private val repository: RelaxingSoundsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        CategoryDetailsViewModel(repository) as T
}