package com.challenge.rahatlaticisesler.base

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Base class for an Recyclerview based fragment
 *
 * Use it for Linear RV based fragments
 * @param A should be Adapter that using from RV.
 * Use [rvAdapter] for actual adapter process. Override the [getAdapter] function first.
 */
abstract class BaseRecyclerViewFragment<A : RecyclerView.Adapter<*>> :
    BaseFragment() {

    /**
     * override this method and return the RV that attached to fragment.
     */
    abstract fun getRecyclerView(): RecyclerView

    /**
     * Override this method and return the Adapter that want to attach to RV.
     *
     * IMPORTANT: Don't use this method for adapter process. See [rvAdapter].
     */
    abstract fun getAdapter(): A

    /**
     * [rvAdapter] is the adapter that using from attached RecyclerView, use it for actual adapter process like show-delete-update.
     */
    protected lateinit var rvAdapter: A

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvAdapter = getAdapter()
        initRecyclerView(rvAdapter)
    }

    private fun initRecyclerView(_adapter: A) {
        getRecyclerView().apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = _adapter
            setHasFixedSize(true)
        }
    }
}