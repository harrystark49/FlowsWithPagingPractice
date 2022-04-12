package com.example.flowswithpagingpractice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flowswithpagingpractice.databinding.RecyclerviewRowBinding


class adap:PagingDataAdapter<Results,adap.vh>(diffutil()) {

    inner class vh(var binding: RecyclerviewRowBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind( item: Results?) {
            binding.tvName.text=item?.name
            binding.tvDes.text=item?.species
            Glide.with(binding.img)
                .load(item?.image)
                .circleCrop()
                .into(binding.img)
        }
    }


    override fun onBindViewHolder(holder: vh, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): vh {
        return vh(RecyclerviewRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


    class diffutil:DiffUtil.ItemCallback<Results>(){
        override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem.type==newItem.type
        }

        override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
            return (oldItem.species==newItem.species && oldItem.name==newItem.name)
        }
    }
}