package com.challenge.rahatlaticisesler.ui.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.challenge.rahatlaticisesler.R
import com.challenge.rahatlaticisesler.data.models.Category
import com.challenge.rahatlaticisesler.databinding.ListitemCategoriesBinding
import com.challenge.rahatlaticisesler.utils.callbacks.IAdapterItemClickListener

class CategoriesAdapter(
    private val itemClickListener: IAdapterItemClickListener<Category>
) :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    private var categoryList = ArrayList<Category>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CategoriesViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                R.layout.listitem_categories,
                parent,
                false
            )
        )

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.binding.categorie = categoryList[position]
        holder.binding.root.setOnClickListener {
            itemClickListener.onAdapterItemClick(holder.itemView, categoryList[position])
        }
    }


    /**
     * Use this method for update adapter
     */
    fun updateCategoryList(categoryList: List<Category>) {
        this.categoryList.clear()
        this.categoryList.addAll(categoryList)
        notifyDataSetChanged()
    }

    inner class CategoriesViewHolder(val binding: ListitemCategoriesBinding) :
        RecyclerView.ViewHolder(binding.root)
}