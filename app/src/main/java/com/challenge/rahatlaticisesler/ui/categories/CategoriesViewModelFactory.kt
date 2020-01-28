package com.challenge.rahatlaticisesler.ui.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.challenge.rahatlaticisesler.data.repo.RelaxingSoundsRepository

class CategoriesViewModelFactory(private val repository: RelaxingSoundsRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        CategoriesViewModel(repository) as T
}