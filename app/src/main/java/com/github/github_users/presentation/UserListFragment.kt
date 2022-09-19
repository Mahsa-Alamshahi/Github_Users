package com.github.github_users.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.github_users.R
import com.github.github_users.core.data.User
import com.github.github_users.databinding.FragmentUserListBinding
import com.github.github_users.framework.viewmodel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class UserListFragment : Fragment() {


    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var binding: FragmentUserListBinding
    val viewModel by viewModels<ListViewModel>()

    var notLoading = true
    var userList: ArrayList<User?> = ArrayList()

    @Inject
    lateinit var listAdapter: UserListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_user_list, container, false)
        binding.fragment = this
        return binding.root
    }


    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.noData.visibility = View.GONE

        initRecyclerView()
        loadingView()
        initScrollListener()
    }


    private fun loadPageList(currentPage: Int) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getUserList(currentPage).collect { list ->
                    list?.let {
                        if (list.isEmpty()) {
                            showNoData()
                        } else {
                            with(userList) {
                                clear()
                                addAll(list)
                            }
                            listAdapter.setList(list)
                        }
                    } ?: run {
                        showErrorOnView()
                        retry()
                    }
                }
            }
        }
    }


    private fun retry() {
        binding.retry.visibility = View.VISIBLE
    }


    private fun showNoData() {
        binding.noData.visibility = View.VISIBLE
        binding.retry.visibility = View.GONE
    }


    private fun loadMore() {
        userList.add(User().apply {
            this.type = "Progress"
        })
        listAdapter.addProgress(userList)
        listAdapter.notifyItemInserted(userList.size - 1)
        notLoading = false

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getUserList(userList.size - 1).collect { list ->

//                    userList.removeAt(userList.size - 1)
//                    listAdapter.notifyItemRemoved(userList.size)
                    listAdapter.removeItem()
                    list?.let {
                        if (list.isEmpty()) {
                            showNoData()
                        } else {
                            with(userList) {
                                clear()
                                addAll(list)
                            }
                            listAdapter.setList(list)
                            notLoading = true
                        }
                    } ?: run {
                        showErrorOnView()
                        retry()
                    }
                }
            }
        }
    }


    private fun initScrollListener() {
        binding.userListView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(@NonNull recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) { //check for scroll down
                    if (notLoading && layoutManager.findLastCompletelyVisibleItemPosition() == userList.size - 1) {
                              loadMore()
                    }
                }
           //        if (notLoading && layoutManager.findLastCompletelyVisibleItemPosition() == userList.size - 1) {
//                    // bottom of list!
//                    loadMore()
//                }
            }
        })
    }


    fun retryClickHandler(){
        binding.retry.visibility = View.GONE
        loadPageList(1)
    }

    fun initRecyclerView() {
        binding.userListView.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false).apply {
            isAutoMeasureEnabled = false
        }
        binding.userListView.layoutManager = layoutManager
        listAdapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT
        binding.userListView.adapter = listAdapter

    }



    fun loadingView() {
        loadPageList(1)
        binding.errorGettingDataParent.visibility = View.VISIBLE
        binding.imgLoadingRetry.visibility = View.GONE
        binding.txtLoadingRetry.visibility = View.GONE
        binding.loading.visibility = View.VISIBLE
    }


    private fun showErrorOnView() {
        binding.errorGettingDataParent.visibility = View.VISIBLE
        binding.imgLoadingRetry.visibility = View.VISIBLE
        binding.txtLoadingRetry.visibility = View.VISIBLE
        binding.loading.visibility = View.GONE
    }
}




