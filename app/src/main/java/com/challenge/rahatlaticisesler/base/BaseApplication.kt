package com.challenge.rahatlaticisesler.base

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule

class BaseApplication : Application(), KodeinAware {

    //Configure kodein
    override val kodein = Kodein.lazy {
        import(androidXModule(this@BaseApplication))
    }
}