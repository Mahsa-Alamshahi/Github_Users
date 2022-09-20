package com.github.github_users.presentation


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.github.github_users.R
import com.github.github_users.core.data.User


class UserListAdapter(var navigationHelper: NavigationHelper) :
    RecyclerView.Adapter<BaseViewHolder<Any>>() {


    private var itemList = listOf<User>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Any> {
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false)
        return BaseViewHolder(binding, navigationHelper)
    }


    override fun onBindViewHolder(holder: BaseViewHolder<Any>, position: Int) =
        holder.bind(itemList[position])


    override fun getItemViewType(position: Int): Int = R.layout.list_item


    fun refreshList() {
        itemList = listOf<User>()
    }


    fun setData(userList: List<User>) {
        itemList = userList
    }


    override fun getItemCount(): Int {
        return itemList.size
    }
}
