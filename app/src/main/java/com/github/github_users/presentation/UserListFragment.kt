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
import com.github.github_users.framework.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject


@AndroidEntryPoint
class UserListFragment : Fragment() {


    private var loading: Boolean = false

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var binding: FragmentUserListBinding
    val viewModel by viewModels<ListViewModel>()
    val searchViewModel by viewModels<SearchViewModel>()

    var userList = arrayListOf<User>()

    @Inject
    lateinit var listAdapter: UserListAdapter

    @Inject
    lateinit var logging: HttpLoggingInterceptor



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_user_list, container, false)
        binding.fragment = this
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        showProgressbarWhileGettingData()
        loadUserList()
        initScrollListener()
    }


//    private fun logRetrofit() {
//        logging.level = HttpLoggingInterceptor.Level.BODY
//        logging.redactHeader("body")
//    }


    private fun initScrollListener() {
        var visibleItemCount = 0
        var totalItemCount = 0
        var pastVisiblesItems = 0
        binding.userListView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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
                            loading = true
                            loadUserList()
                        }
                    }
                }
//                    if (notLoading && layoutManager.findLastCompletelyVisibleItemPosition() == userList.size - 1) {
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


    fun checkEnteredNameForSearch() {
        showProgressbarWhileGettingData()
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
    private fun setDataIntoRecyclerView(list: List<User>?){
        if (list != null) {
            if (list.isNotEmpty()) {
                showData()
                userList.addAll(list)
                listAdapter.addProgress(userList)
                listAdapter.notifyDataSetChanged()
                loading = false
            } else {
                showNoData()
            }
        } else {
            showRetryWhenIncounteredProblems()
        }
    }


    private fun showProgressbarWhileGettingData() {
        binding.txtRetry.visibility = View.GONE
        binding.loading.visibility = View.VISIBLE
        binding.noData.visibility = View.GONE
        binding.userListView.visibility = View.GONE
    }


    private fun showRetryWhenIncounteredProblems() {
        binding.txtRetry.visibility = View.VISIBLE
        binding.loading.visibility = View.GONE
        binding.noData.visibility = View.GONE
        binding.userListView.visibility = View.GONE
    }


    private fun showNoData() {
        binding.txtRetry.visibility = View.GONE
        binding.loading.visibility = View.GONE
        binding.noData.visibility = View.VISIBLE
        binding.userListView.visibility = View.GONE
    }


    private fun showData() {
        binding.txtRetry.visibility = View.GONE
        binding.loading.visibility = View.GONE
        binding.noData.visibility = View.GONE
        binding.userListView.visibility = View.VISIBLE
    }

}




