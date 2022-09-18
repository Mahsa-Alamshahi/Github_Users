package com.github.github_users.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.github_users.R
import com.github.github_users.databinding.FragmentUserListBinding
import com.github.github_users.framework.viewmodel.ListViewModel
import com.google.android.material.snackbar.Snackbar
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class UserListFragment : Fragment() {


    private lateinit var binding: FragmentUserListBinding
    val viewModel by viewModels<ListViewModel>()
    var searchQuery: String = ""

    private var currentPage = 1
    private var totalAvailablePages = 1

    @Inject
    lateinit var listAdapter: UserListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_user_list, container, false)
        return binding.root
    }


    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.noData.visibility = View.GONE

        binding.userListView.setHasFixedSize(true)
        binding.userListView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false).apply {
                isAutoMeasureEnabled = false
            }
        listAdapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT
        binding.userListView.adapter = listAdapter



        binding.userListView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!binding.userListView.canScrollVertically(1)) {
//                    if (currentPage <= totalAvailablePages) {
                        currentPage += 1
                        loadPageList()
//                    }
                }
            }
        })
        loadPageList()
    }


    private fun togleLoading() {
        if (currentPage == 1) {
            if (binding.defaultProgress.isShown) {
                binding.defaultProgress.visibility = View.GONE
            } else {
                binding.defaultProgress.visibility = View.VISIBLE
            }
        } else {
            if (binding.loadMoreProgress.isShown) {
                binding.loadMoreProgress.visibility = View.GONE
            } else {
                binding.loadMoreProgress.visibility = View.VISIBLE
            }
        }
    }


    private fun loadPageList() {
        togleLoading()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getUserList(currentPage).collect { userList ->
                    userList.let {
                        if (userList.isEmpty()) {
                        } else {
                            val oldCount = userList.size
//                            totalAvailablePages = showModel.pages
//                            tvShowList.addAll(showModel.tvShows)
                            listAdapter.updateList(userList, oldCount, userList.size)
                            Logger.e(
                                "oldCount $oldCount totalAvailablePages $totalAvailablePages tvShowList ${userList.size}"
                            )
//                            listAdapter.setList(userList)
                        }
                    }
                }
            }
        }
        togleLoading()
    }


}




