package com.example.userlist.features.userList

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.domain.utils.ResultOf
import com.example.userlist.R
import com.example.userlist.databinding.FragmentUserListBinding
import com.example.userlist.di.utils.getAppComponent
import com.example.userlist.di.utils.lazyViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect

class UserListFragment : Fragment(R.layout.fragment_user_list) {

    private val vm: UserListViewModel by lazyViewModel {
        getAppComponent().userListViewModel().create()
    }

    private val adapter = UserListAdapter { vm.openUserDetails(it) }

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupViewModel()
        setupListeners()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setupView(view: View) {
        _binding = FragmentUserListBinding.bind(view)

        binding.userListRv.adapter = adapter
        binding.userListRv.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayout.VERTICAL
            )
        )
    }

    private fun setupViewModel() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            vm.userList.collect {
                binding.errorTv.isVisible = it is ResultOf.Failure
                binding.refreshBtn.isVisible = it is ResultOf.Failure
                binding.progressBar.isVisible = it is ResultOf.Loading
                binding.userListRv.isVisible = it is ResultOf.Success
                if (it is ResultOf.Success) {
                    adapter.submitList(it.value)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            vm.showMessage.collect {
                Snackbar.make(
                    requireView(),
                    it,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            vm.openUserDetails.collect {
                findNavController().navigate(
                    UserListFragmentDirections.actionUserListFragmentToUserDetailsFragment(
                        it
                    )
                )
            }
        }
    }

    private fun setupListeners() {
        binding.refreshBtn.setOnClickListener {
            vm.getUsers()
        }
    }
}
