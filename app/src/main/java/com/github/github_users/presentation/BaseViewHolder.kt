package com.github.github_users.presentation

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class BaseViewHolder<in T>(
    var viewBinding: ViewBinding,
    var navigationHelper: NavigationHelper,
    var onItemClicked: (Any) -> Unit
) :
    RecyclerView.ViewHolder(viewBinding.root) {

}