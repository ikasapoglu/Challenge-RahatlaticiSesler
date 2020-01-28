package com.challenge.rahatlaticisesler.ui.categories

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.challenge.rahatlaticisesler.R
import com.challenge.rahatlaticisesler.base.BaseRecyclerViewFragment
import com.challenge.rahatlaticisesler.data.models.Category
import com.challenge.rahatlaticisesler.ui.categorydetails.CategoryDetailsFragment
import com.challenge.rahatlaticisesler.utils.callbacks.IAdapterItemClickListener
import kotlinx.android.synthetic.main.fragment_categories.*
import org.kodein.di.generic.instance


class CategoriesFragment : BaseRecyclerViewFragment<CategoriesAdapter>(),
    IAdapterItemClickListener<Category> {

    private lateinit var viewModel: CategoriesViewModel
    private val factory: CategoriesViewModelFactory by instance()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this, factory).get(CategoriesViewModel::class.java)

        viewModel.isLoading.observe(this, Observer {
            if (it) progressDialog.show() else progressDialog.hide()
        })

        viewModel.getCategories()
        viewModel.categories.observe(this, Observer { categories ->
            rvAdapter.updateCategoryList(categories)
        })
    }


    override fun onAdapterItemClick(view: View, category: Category) {
        navigateToDetails(category.id.toString())
    }

    private fun navigateToDetails(categoryId: String) {
        val bundle = bundleOf(CategoryDetailsFragment.KEY_CATEGORYID to categoryId)
        findNavController().navigate(
            R.id.action_categoriesFragment_to_categoryDetailsFragment,
            bundle
        )
    }

    override fun getRecyclerView(): RecyclerView {
        return rv_categories
    }

    override fun getAdapter(): CategoriesAdapter {
        return CategoriesAdapter(this)
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_categories
    }
}
