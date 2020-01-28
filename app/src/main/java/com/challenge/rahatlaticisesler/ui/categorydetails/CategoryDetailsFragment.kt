package com.challenge.rahatlaticisesler.ui.categorydetails

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.challenge.rahatlaticisesler.R
import com.challenge.rahatlaticisesler.base.BaseRecyclerViewFragment
import com.challenge.rahatlaticisesler.data.models.Sound
import com.challenge.rahatlaticisesler.utils.callbacks.IAdapterItemClickListener
import com.challenge.rahatlaticisesler.utils.callbacks.IItemFavoriteStateChangeListener
import com.challenge.rahatlaticisesler.utils.callbacks.IItemVolumeStateChangeListener
import kotlinx.android.synthetic.main.fragment_category_details.*
import org.kodein.di.generic.instance

class CategoryDetailsFragment :
    BaseRecyclerViewFragment<CategoryDetailsAdapter>(),
    IAdapterItemClickListener<Sound>,
    IItemFavoriteStateChangeListener,
    IItemVolumeStateChangeListener {

    private lateinit var viewModel: CategoryDetailsViewModel
    private val factory: CategoryDetailsViewModelFactory by instance()

    private var favList = MutableLiveData<List<Sound>>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val categoryId = arguments?.get(KEY_CATEGORYID) as String

        viewModel = ViewModelProvider(this, factory).get(CategoryDetailsViewModel::class.java)

        viewModel.isLoading.observe(this, Observer {
            if (it) progressDialog.show() else progressDialog.hide()
        })

        viewModel.getCategoryDetails(categoryId.toInt())

        viewModel.categoryDetails.observe(this, Observer { sounds ->
            rvAdapter.updateSoundList(sounds)
        })

    }

    override fun onAdapterItemClick(view: View, item: Sound) {
        when (view.id) {
            R.id.btn_listitem_detail_play -> {
                viewModel.playSound(item)
            }
        }
    }

    override fun onVolumeStateChange(volume: Int, item: Sound) {
        viewModel.volumeStateChanged(volume, item)
    }

    override fun OnFavStateChange(boolean: Boolean, item: Sound) {
    }

    override fun getRecyclerView(): RecyclerView {
        return rv_categorydetails
    }

    override fun getAdapter(): CategoryDetailsAdapter {
        return CategoryDetailsAdapter(this, this, this)
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_category_details
    }

    companion object {
        const val KEY_CATEGORYID = "categoryId"
    }
}