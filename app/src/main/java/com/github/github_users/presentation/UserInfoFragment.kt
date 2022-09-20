package com.github.github_users.presentation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.github.github_users.R
import com.github.github_users.databinding.FragmentUserInfoBinding
import com.github.github_users.framework.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class UserInfoFragment : Fragment() {


    val viewModel: UserViewModel by viewModels()
    lateinit var binding: FragmentUserInfoBinding
    private var userLogin: String = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val args: UserInfoFragmentArgs by navArgs()
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_user_info, container, false)
        userLogin = args.userLoginArg!!
        binding.clickHandler = this
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
    }


    private fun getUserData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getUser(userLogin).collect { user ->
                    user?.let {
                        showData(binding.txtRetry, binding.loading, binding.noData, binding.data)
                        binding.user = it
                    } ?: run {
                        showRetryWhenIncounteredProblems(
                            binding.txtRetry,
                            binding.loading,
                            binding.noData,
                            binding.data
                        )
                    }
                }
            }
        }
    }


    fun getData() {
        showProgressbarWhileGettingData(
            binding.txtRetry,
            binding.loading,
            binding.noData,
            binding.data
        )
        getUserData()
    }

}