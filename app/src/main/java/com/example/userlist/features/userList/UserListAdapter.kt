package com.example.userlist.features.userList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.UserModel
import com.example.userlist.databinding.ListItemUserBinding
import com.example.userlist.utils.loadWithCache
import com.squareup.picasso.Picasso


class UserListAdapter(private val onUserClick: (UserModel) -> Unit) :
    ListAdapter<UserModel, UserListViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        return UserListViewHolder(
            ListItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bind(getItem(position), onUserClick)
    }
}

class UserListViewHolder(private val binding: ListItemUserBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(userModel: UserModel, onUserClick: (UserModel) -> Unit) {
        binding.firstNameTv.text = userModel.firstName
        binding.lastNameTv.text = userModel.lastName
        Picasso.get().loadWithCache(userModel.avatar, binding.avatarIv)

        binding.root.setOnClickListener {
            onUserClick(userModel)
        }
    }
}

val diffUtil = object : DiffUtil.ItemCallback<UserModel>() {
    override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
        return oldItem.avatar == newItem.avatar &&
                oldItem.firstName == newItem.firstName &&
                oldItem.lastName == newItem.lastName
    }
}