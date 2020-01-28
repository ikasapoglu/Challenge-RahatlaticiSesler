package com.challenge.rahatlaticisesler.ui.categorydetails

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.challenge.rahatlaticisesler.R
import com.challenge.rahatlaticisesler.data.models.Sound
import com.challenge.rahatlaticisesler.databinding.ListitemCategoryDetailsBinding
import com.challenge.rahatlaticisesler.utils.callbacks.IAdapterItemClickListener
import com.challenge.rahatlaticisesler.utils.callbacks.IItemFavoriteStateChangeListener
import com.challenge.rahatlaticisesler.utils.callbacks.IItemVolumeStateChangeListener
import kotlinx.android.synthetic.main.listitem_category_details.view.*

class  CategoryDetailsAdapter(
    private val itemClickListener: IAdapterItemClickListener<Sound>,
    private val itemFavStateListener: IItemFavoriteStateChangeListener,
    private val itemVolumeStateListener: IItemVolumeStateChangeListener
) : RecyclerView.Adapter<CategoryDetailsAdapter.CategoryDetailsViewHolder>() {

    private var soundList = ArrayList<Sound>()
    private var favoritedList = ArrayList<Sound>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CategoryDetailsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                R.layout.listitem_category_details,
                parent,
                false
            )
        )

    override fun getItemCount(): Int {
        return soundList.size
    }

    override fun onBindViewHolder(holder: CategoryDetailsViewHolder, position: Int) {
        val sound = soundList[position]
        holder.binding.sound = sound

        holder.itemView.cb_listitem_detail_favorite.setOnCheckedChangeListener { btn, isChecked ->
            itemFavStateListener.OnFavStateChange(isChecked, sound)
        }

        holder.binding.sbListitemDetailVolume.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, p2: Boolean) {
                itemVolumeStateListener.onVolumeStateChange(progress, sound)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        holder.binding.btnListitemDetailPlay.setOnClickListener {
            itemClickListener.onAdapterItemClick(it, sound)
        }
    }

    /**
     * Updates the sound list of the adapter.
     * @param soundList Updated soundlist.
     * @param favoritedList User's favorited list.
     */
    fun updateSoundList(_soundList: List<Sound>) {
        this.soundList.clear()
        this.soundList.addAll(_soundList)
        notifyDataSetChanged()
    }

    /**
     * Updates the favorited list of the adapter also checks is sound favorited.
     * @param soundList Updated soundlist.
     */
    fun updateFavorites(_favList: List<Sound>) {
        this.favoritedList.clear()
        this.favoritedList.addAll(_favList)
        soundList.forEach {
            it.isFavorited = favoritedList.any { f -> f.id == it.id }
        }
        notifyDataSetChanged()
    }

    inner class CategoryDetailsViewHolder(val binding: ListitemCategoryDetailsBinding) :
        RecyclerView.ViewHolder(binding.root)
}