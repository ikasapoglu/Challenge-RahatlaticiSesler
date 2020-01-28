package com.challenge.rahatlaticisesler.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.challenge.rahatlaticisesler.data.repo.RelaxingSoundsRepository

class FavoritesViewModelFactory(private val repository: RelaxingSoundsRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        FavoritesViewModel(repository) as T
}