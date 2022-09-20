package com.github.github_users.presentation

import android.view.View
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.github.github_users.core.data.User
import com.github.github_users.databinding.ListItemBinding



class BaseViewHolder<in T>(
    var viewBinding: ViewBinding,
    var navigationHelper: NavigationHelper,
) :
    RecyclerView.ViewHolder(viewBinding.root) {


    fun bind(item: T) {
      bindUser(item as User)
    }


    private fun bindUser(item: User) {
        (viewBinding as ListItemBinding).user = item
        (viewBinding as ListItemBinding).clickHandler = this
    }


    fun userListItemClickHandler(view: View, userLogin: String) {
        navigationHelper.navigateFromUserListToUserInfoFragment(
            view,
            bundleOf("userLoginArg" to userLogin)
        )
    }

}