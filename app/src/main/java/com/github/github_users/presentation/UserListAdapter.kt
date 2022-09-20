package com.github.github_users.presentation


import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.github_users.core.data.User


class UserListAdapter(var navigationHelper: NavigationHelper) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var itemList = listOf<User>()


    companion object {
        var TYPE_DATA = 0
        var TYPE_PROGRESS = 1
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_DATA -> BaseViewHolder(parent, navigationHelper)
//            TYPE_PROGRESS -> ProgressHolder(parent)
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BaseViewHolder -> holder.bind(itemList[position])
            is ProgressHolder -> holder.bind()
        }

    }


    override fun getItemViewType(position: Int): Int {
        return TYPE_DATA
    }

    fun refreshList() {
        itemList = listOf<User>()
    }

    fun addProgress(userList: List<User>) {
        itemList = userList
    }


}