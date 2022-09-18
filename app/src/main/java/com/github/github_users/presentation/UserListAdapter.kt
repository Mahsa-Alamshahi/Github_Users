package com.github.github_users.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.github.github_users.R
import com.github.github_users.core.data.User


class UserListAdapter(var navigationHelper: NavigationHelper): RecyclerView.Adapter<BaseViewHolder<Any>>() {


    var onItemClicked: ((Any) -> Unit)? = null
    private var itemList: ArrayList<Any> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Any> {
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false)
        return BaseViewHolder(binding, navigationHelper) {
            onItemClicked?.let { item ->
                item(it)
            }
        }


    }

    override fun onBindViewHolder(holder: BaseViewHolder<Any>, position: Int) {
        holder.bind(itemList[position])
    }



    override fun getItemCount(): Int {
        return if (itemList == null) 0 else itemList.size
    }


    override fun getItemViewType(position: Int): Int {
        return R.layout.list_item
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<User>?) {
        with(this.itemList) {
            clear()
            list?.let { addAll(it) }
        }
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(tvShowList: List<User>, oldCount: Int, listSize: Int) {
        this.itemList.addAll(tvShowList)
        notifyDataSetChanged()
        notifyItemRangeInserted(oldCount, listSize)
    }
}