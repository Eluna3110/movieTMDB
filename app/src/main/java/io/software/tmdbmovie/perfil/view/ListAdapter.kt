package io.software.tmdbmovie.perfil.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.software.tmdbmovie.R
import io.software.tmdbmovie.databinding.ItemListBinding
import io.software.tmdbmovie.perfil.model.Profile

class ListAdapter(var data: MutableList<Profile>) :  RecyclerView.Adapter<ListAdapter.ViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemListBinding>(inflater, R.layout.item_list, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class ViewHolder(val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root)
    {
        fun bind(item: Profile) {
            binding.icon.setBackgroundResource(item.icon)
            binding.profile = item

            binding.executePendingBindings()
        }
    }

    fun updateDataSet(newData: MutableList<Profile>) {
        this.data.clear()
        this.data = newData
        notifyDataSetChanged()
    }
}