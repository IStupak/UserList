package com.example.userlist.features.userDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.domain.models.UserModel
import com.example.userlist.R
import com.example.userlist.databinding.FragmentUserDetailsBinding
import com.example.userlist.di.utils.getAppComponent
import com.example.userlist.di.utils.lazyViewModel
import com.example.userlist.utils.loadWithCache
import com.squareup.picasso.Picasso
import kotlinx.coroutines.flow.collect

class UserDetailsFragment : Fragment(R.layout.fragment_user_details) {

    private val arg by navArgs<UserDetailsFragmentArgs>()

    private val vm: UserDetailsViewModel by lazyViewModel {
        getAppComponent().userDetailsViewModel().create(arg.toSavedStateHandle())
    }

    private var _binding: FragmentUserDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentUserDetailsBinding.bind(view)
        setupViewModel()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setupViewModel() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            vm.userModel.collect {
                setUserData(it)
            }
        }
    }

    private fun setUserData(userModel: UserModel) {
        binding.firstNameTv.text = userModel.firstName
        binding.lastNameTv.text = userModel.lastName
        Picasso.get().loadWithCache(userModel.avatar, binding.avatarIv)
    }
}