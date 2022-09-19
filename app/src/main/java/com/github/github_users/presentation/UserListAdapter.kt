package com.github.github_users.presentation


import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.github_users.core.data.User



class UserListAdapter(var navigationHelper: NavigationHelper) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var itemList: ArrayList<User> = ArrayList()


    companion object {
        var TYPE_DATA = 0
        var TYPE_PROGRESS = 1
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_DATA -> BaseViewHolder(parent, navigationHelper)
            TYPE_PROGRESS -> ProgressHolder(parent)
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
        var viewType = itemList.get(position).type
        return when (viewType) {
            "User" -> TYPE_DATA
            else -> TYPE_PROGRESS
        }

    }


    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<User>?) {
        with(this.itemList) {
            list?.let { addAll(it) }
        }
        notifyDataSetChanged()
    }


    fun addProgress(userList: List<User?>) {
        with(this.itemList) {
            userList.let { addAll(this) }
        }
        notifyItemInserted(itemList.size - 1)
    }


    fun removeItem() {
        itemList.removeAt(itemList.size - 1)
        notifyItemRemoved(itemList.size)
    }
}