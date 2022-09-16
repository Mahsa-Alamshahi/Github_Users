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
            .thumbnail(0.5f)
            .override(300, 300)
            .centerCrop()
            .placeholder(R.drawable.ic_baseline_image_24)
            .into(view as ImageView)
    }
}