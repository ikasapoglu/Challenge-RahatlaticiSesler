package com.challenge.rahatlaticisesler.base

import android.app.Dialog
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.challenge.rahatlaticisesler.R
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein

/**
 * Inherit from this class when creating Fragment. Kodein injection by default.
 */
abstract class BaseFragment : Fragment(), KodeinAware {

    override val kodein by kodein()

    @LayoutRes
    abstract fun getLayoutRes(): Int

    lateinit var progressDialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        buildProgressDialog()
        return inflater.inflate(getLayoutRes(), container, false)
    }

    private fun buildProgressDialog() {
        activity?.let {
            progressDialog = Dialog(activity!!)
            progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            progressDialog.window!!.setBackgroundDrawable(ColorDrawable(0))
            progressDialog.setContentView(R.layout.progress_dialog)
            val progressBar = progressDialog.findViewById<ProgressBar>(R.id.progressbar_base)
            progressBar.indeterminateDrawable.setColorFilter(
                it.resources.getColor(R.color.colorPrimary),
                PorterDuff.Mode.MULTIPLY
            )
            progressDialog.setCancelable(false)
        }
    }
}