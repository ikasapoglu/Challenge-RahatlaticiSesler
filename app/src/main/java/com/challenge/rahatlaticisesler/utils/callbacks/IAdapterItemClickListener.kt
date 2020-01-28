package com.challenge.rahatlaticisesler.utils.callbacks

import android.view.View


interface IAdapterItemClickListener<T> {
    fun onAdapterItemClick(view: View, item: T)
}