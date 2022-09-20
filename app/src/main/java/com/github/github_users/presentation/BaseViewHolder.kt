package com.github.github_users.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.github.github_users.core.data.User
import com.github.github_users.databinding.ListItemBinding
import com.orhanobut.logger.Logger

class BaseViewHolder(
    var itemBinding: ListItemBinding,
) : RecyclerView.ViewHolder(itemBinding.root) {


    lateinit var navigationHelper: NavigationHelper

    constructor(
        parent: ViewGroup,
        navigationHelper: NavigationHelper,
    ) : this(
        ListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    ) {
        this.navigationHelper = navigationHelper
    }


    fun bind(item: User) {
        itemBinding.user = item
        itemBinding.clickHandler = this
        Logger.d("onsore " + item.id + " "+ item.avatarUrl )
    }


    fun userListItemClickHandler(view: View, userLogin: String) {
        navigationHelper.navigateFromUserListToUserInfoFragment(
            view,
            bundleOf("userLoginArg" to userLogin)
        )
    }

}