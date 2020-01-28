package com.challenge.rahatlaticisesler.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein

/**
 * Inherit from this class when creating Fragment. Kodein injection by default.
 */
abstract class BaseFragment : Fragment(), KodeinAware {

    override val kodein by kodein()

    /**
     * Get layout of the fragment.
     */
    @LayoutRes
    abstract fun getLayoutRes(): Int

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutRes(), container, false)
    }
}