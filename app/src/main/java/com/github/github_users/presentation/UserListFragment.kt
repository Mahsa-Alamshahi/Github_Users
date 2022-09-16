package com.github.github_users.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.github_users.R
import com.github.github_users.databinding.FragmentUserListBinding
import com.github.github_users.framework.viewmodel.ListViewModel
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class UserListFragment : Fragment() {


    private lateinit var binding: FragmentUserListBinding
    val viewModel by viewModels<ListViewModel>()

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

        binding.userListView.layoutManager = LinearLayoutManager(context).apply {
            isAutoMeasureEnabled = false
        }
        listAdapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT
        binding.userListView.adapter = listAdapter


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getUserList().collect { userList ->
                    userList.let {
                        if (userList.isEmpty()) {
                            binding.userListView.visibility = View.GONE
                            binding.loadingView.visibility = View.GONE
                            binding.noData.visibility = View.VISIBLE
                        } else {
                            listAdapter.setList(userList)
                            binding.userListView.visibility = View.VISIBLE
                            binding.loadingView.visibility = View.GONE
                            binding.noData.visibility = View.GONE
                        }
                    }
                }
            }
        }

    }



}