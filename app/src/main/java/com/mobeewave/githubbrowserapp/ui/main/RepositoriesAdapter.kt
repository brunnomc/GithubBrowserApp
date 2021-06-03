package com.mobeewave.githubbrowserapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobeewave.githubbrowserapp.R
import com.mobeewave.githubbrowserapp.data.Repository
import com.mobeewave.githubbrowserapp.databinding.RepositoryViewItemBinding
import com.mobeewave.githubbrowserapp.ui.main.RepositoriesAdapter.RepositoriesViewHolder


class RepositoriesAdapter :
    ListAdapter<Repository, RepositoriesViewHolder>(RepositoryDiffCallback()) {


    override fun onBindViewHolder(holder: RepositoriesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RepositoryViewItemBinding.inflate(layoutInflater, parent, false)
        return RepositoriesViewHolder(binding)
    }

    class RepositoriesViewHolder constructor(private val binding: RepositoryViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Repository) {
            Glide.with(binding.root)
                .load(item.owner.avatarUrl)
                .centerInside()
                .error(R.drawable.ic_launcher_foreground)
                .fallback(R.drawable.ic_launcher_foreground)
                .into(binding.avatar)

            binding.repoName.text = item.name
            binding.ownerName.text = item.owner.login
            binding.stars.text = item.stargazersCount.toString()
            binding.forks.text = item.forks.toString()
        }
    }
}

class RepositoryDiffCallback : DiffUtil.ItemCallback<Repository>() {
    override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem == newItem
    }
}