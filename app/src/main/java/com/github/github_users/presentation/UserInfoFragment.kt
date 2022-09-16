package com.github.github_users.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.github.github_users.R
import com.github.github_users.databinding.FragmentUserInfoBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserInfoFragment : Fragment() {

    lateinit var binding: FragmentUserInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_user_info, container, false)
        return binding.root
    }


}