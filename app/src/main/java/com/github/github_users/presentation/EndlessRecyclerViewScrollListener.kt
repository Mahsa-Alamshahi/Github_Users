package com.github.github_users.presentation

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


abstract class EndlessRecyclerViewScrollListener constructor(): RecyclerView.OnScrollListener() {

    private var loading = true
    private var previousItemCount = 0

    var currentItem = 1
    var itemsPerPage: Int = 20


    fun refresh() {
        currentItem = 0
        itemsPerPage = 20
        previousItemCount = 0
        loading = true
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val layoutManager = when {
            (recyclerView.layoutManager is LinearLayoutManager) -> recyclerView.layoutManager as LinearLayoutManager
            (recyclerView.layoutManager is GridLayoutManager) -> recyclerView.layoutManager as GridLayoutManager
            else -> throw Exception("unknown layout manager")
        }
        val lastPosition = layoutManager.findLastVisibleItemPosition()
        if (lastPosition == recyclerView.adapter!!.itemCount - 1 && !loading) {
            if ((currentItem) <= currentItem+itemsPerPage){
                currentItem += itemsPerPage
                onLoadMore(currentItem)
                loading = true
            }
        }
        if (recyclerView.adapter!!.itemCount > previousItemCount){
            previousItemCount = recyclerView.adapter!!.itemCount
            loading = false

        }
    }

    abstract fun onLoadMore(currentPage: Int)
}