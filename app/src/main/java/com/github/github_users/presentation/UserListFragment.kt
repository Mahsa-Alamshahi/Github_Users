package com.github.github_users.presentation

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
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
import com.github.github_users.framework.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class UserListFragment : Fragment() {


    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var binding: FragmentUserListBinding
    val viewModel by viewModels<ListViewModel>()
    val searchViewModel by viewModels<SearchViewModel>()

    var userList = arrayListOf<User>()
    private var loading: Boolean = false

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


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }


    @RequiresApi(Build.VERSION_CODES.M)
    fun initView() {
        if (viewModel.isOnline()) {
            initRecyclerView()
            showProgressbarWhileGettingData(
                binding.txtRetry,
                binding.loading,
                binding.noData,
                binding.userListView
            )
            loadUserList()
            initScrollListener()
        } else {
            showRetryWhenFacedProblems(
                binding.txtRetry,
                binding.loading,
                binding.noData,
                binding.userListView
            )
        }
    }

    private fun initScrollListener() {
        var visibleItemCount = 0
        var totalItemCount = 0
        var pastVisiblesItems = 0
        binding.userListView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            @RequiresApi(Build.VERSION_CODES.M)
            override fun onScrolled(@NonNull recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    visibleItemCount =
                        (binding.userListView.layoutManager as LinearLayoutManager).childCount
                    totalItemCount =
                        (binding.userListView.layoutManager as LinearLayoutManager).itemCount
                    pastVisiblesItems =
                        (binding.userListView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    if (!loading) {
                        if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                            if (viewModel.isOnline()) {
                                loading = true
                                loadUserList()
                            } else {
                                Toast.makeText(context, "No internet connection. Pleace check it and try again.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        })
    }


    private fun initRecyclerView() {
        binding.userListView.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false).apply {
            isAutoMeasureEnabled = false
        }
        listAdapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT
        binding.userListView.layoutManager = layoutManager
        binding.userListView.adapter = listAdapter
    }


    @RequiresApi(Build.VERSION_CODES.M)
    fun checkEnteredNameForSearch() {
        if (viewModel.isOnline()) {
            showProgressbarWhileGettingData(
                binding.txtRetry,
                binding.loading,
                binding.noData,
                binding.userListView
            )
            if (binding.edtSearch.text.toString() != "") {
                userList.clear()
                listAdapter.refreshList()
                initScrollListener()
                loadSearchResult()
            } else {
                userList.clear()
                listAdapter.refreshList()
                initScrollListener()
                loadUserList()
            }
        }else {
            showRetryWhenFacedProblems(
                binding.txtRetry,
                binding.loading,
                binding.noData,
                binding.userListView
            )
        }
    }


    private fun loadSearchResult() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loading = true
                searchViewModel.searchUser(userList.size, binding.edtSearch.text.toString())
                    .collect { list ->
                        setDataIntoRecyclerView(list)
                    }
            }
        }
    }

    private fun loadUserList() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loading = true
                viewModel.getUserList(userList.size).collect { list ->
                    setDataIntoRecyclerView(list)
                }
            }
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun setDataIntoRecyclerView(list: List<User>?) {
        if (list != null) {
            if (list.isNotEmpty()) {
                showData(binding.txtRetry, binding.loading, binding.noData, binding.userListView)
                userList.addAll(list)
                listAdapter.setData(userList)
                listAdapter.notifyDataSetChanged()
                loading = false
            } else {
                showNoData(binding.txtRetry, binding.loading, binding.noData, binding.userListView)
            }
        } else {
            showRetryWhenFacedProblems(
                binding.txtRetry,
                binding.loading,
                binding.noData,
                binding.userListView
            )
        }
    }
}




