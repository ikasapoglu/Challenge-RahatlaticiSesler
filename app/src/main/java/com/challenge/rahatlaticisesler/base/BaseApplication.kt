package com.challenge.rahatlaticisesler.base

import android.app.Application
import com.challenge.rahatlaticisesler.data.remote.IApiService
import com.challenge.rahatlaticisesler.data.remote.NetworkConnectionInterceptor
import com.challenge.rahatlaticisesler.data.repo.RelaxingSoundsRepository
import com.challenge.rahatlaticisesler.ui.categories.CategoriesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class BaseApplication : Application(), KodeinAware {

    //Configure kodein
    override val kodein = Kodein.lazy {
        import(androidXModule(this@BaseApplication))

        bind() from singleton { NetworkConnectionInterceptor((instance())) }
        bind() from singleton { IApiService(instance()) }
        bind() from singleton { RelaxingSoundsRepository(instance()) }
        bind() from provider { CategoriesViewModelFactory(instance()) }
    }
}