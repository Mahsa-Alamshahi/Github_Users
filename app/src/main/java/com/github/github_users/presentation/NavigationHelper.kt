package com.github.github_users.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import com.github.github_users.R



class NavigationHelper {

    fun navigateFromUserListToUserInfoFragment(view: View, bundle: Bundle) {
        Navigation.findNavController(view).navigate(
            R.id.action_userListFragment_to_userInfoFragment,
            bundle
        )
    }
}