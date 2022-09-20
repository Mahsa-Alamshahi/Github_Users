package com.github.github_users.presentation

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.github.github_users.R



@BindingAdapter("imageUrl")
fun loadUserImage(view: View, url: String?) {
    if (!url.isNullOrEmpty()) {
               Glide.with(view)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.ic_baseline_image_24)
            .into(view as ImageView)
    }
}



fun showProgressbarWhileGettingData(retry: View, loading: View, noData: View, data: View) {
    retry.visibility = View.GONE
    loading.visibility = View.VISIBLE
    noData.visibility = View.GONE
    data.visibility = View.GONE
}


fun showRetryWhenFacedProblems(retry: View, loading: View, noData: View, data: View) {
    retry.visibility = View.VISIBLE
    loading.visibility = View.GONE
    noData.visibility = View.GONE
    data.visibility = View.GONE
}


fun showNoData(retry: View, loading: View, noData: View, data: View) {
    retry.visibility = View.GONE
    loading.visibility = View.GONE
    noData.visibility = View.VISIBLE
    data.visibility = View.GONE
}


fun showData(retry: View, loading: View, noData: View, data: View) {
    retry.visibility = View.GONE
    loading.visibility = View.GONE
    noData.visibility = View.GONE
    data.visibility = View.VISIBLE
}