package com.github.github_users.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.github_users.databinding.LoadingPrgressbarBinding



class ProgressHolder(var binding: LoadingPrgressbarBinding): RecyclerView.ViewHolder(binding.root) {

    constructor(parent: ViewGroup) : this(
        LoadingPrgressbarBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    fun bind() {}
}