package io.software.tmdbmovie.perfil.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import io.software.tmdbmovie.databinding.ItemListBinding
import io.software.tmdbmovie.perfil.model.Profile
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ListItemAdapter() : ListAdapter<Profile, ListItemAdapter.CustomViewHolder>(SampleItemDiffCallback())
{
    class SampleItemDiffCallback : DiffUtil.ItemCallback<Profile>() {
        override fun areItemsTheSame(oldItem: Profile, newItem: Profile): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: Profile, newItem: Profile): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CustomViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    class CustomViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root){

        fun bindTo(profile: Profile){
            binding.profile = profile
            binding.icon.setBackgroundResource(profile.icon)

            binding.executePendingBindings()
        }
    }

}