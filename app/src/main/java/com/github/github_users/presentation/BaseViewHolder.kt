package com.github.github_users.presentation

import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.github.github_users.R
import com.github.github_users.core.data.User
import com.github.github_users.databinding.ListItemBinding
import com.orhanobut.logger.Logger

class BaseViewHolder<in T>(
    var viewBinding: ViewBinding,
    var navigationHelper: NavigationHelper,
    var onItemClicked: (Any) -> Unit
) :
    RecyclerView.ViewHolder(viewBinding.root) {


    fun bind(item: T) {
        when (item) {
            is User -> {
                bindUser(item)
            }
        }
    }


    private fun bindUser(item: User) {
        (viewBinding as ListItemBinding).user = item
        (viewBinding as ListItemBinding).clickHandler = this

    }

    fun userListItemClickHandler(view: View, userLogin: String) {
        Logger.d(userLogin)
        navigationHelper.navigateFromUserListToUserInfoFragment(
            view,
            bundleOf("userLoginArg" to userLogin)
        )
    }
}