package com.challenge.rahatlaticisesler.ui.favorites

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.challenge.rahatlaticisesler.R
import com.challenge.rahatlaticisesler.base.BaseRecyclerViewFragment
import com.challenge.rahatlaticisesler.data.models.Sound
import com.challenge.rahatlaticisesler.ui.categorydetails.CategoryDetailsAdapter
import com.challenge.rahatlaticisesler.utils.callbacks.IAdapterItemClickListener
import com.challenge.rahatlaticisesler.utils.callbacks.IItemFavoriteStateChangeListener
import com.challenge.rahatlaticisesler.utils.callbacks.IItemVolumeStateChangeListener
import kotlinx.android.synthetic.main.fragment_favorites.*
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance


class FavoritesFragment : BaseRecyclerViewFragment<CategoryDetailsAdapter>(), KodeinAware,
    IAdapterItemClickListener<Sound>,
    IItemFavoriteStateChangeListener,
    IItemVolumeStateChangeListener {

    private lateinit var viewModel: FavoritesViewModel
    private val factory: FavoritesViewModelFactory by instance()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(FavoritesViewModel::class.java)

        viewModel.getFavorites()

        viewModel.favorites.observe(this, Observer { favorites ->
            rvAdapter.updateSoundList(favorites)
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
        viewModel.favoriteStatusChanged(boolean, item)
        viewModel.getFavorites()
    }


    override fun getRecyclerView(): RecyclerView {
        return rv_favorites
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_favorites
    }

    override fun getAdapter(): CategoryDetailsAdapter {
        return CategoryDetailsAdapter(this, this, this)
    }
}