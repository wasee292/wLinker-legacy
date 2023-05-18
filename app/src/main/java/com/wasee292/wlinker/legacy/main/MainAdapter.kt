package com.wasee292.wlinker.legacy.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.wasee292.wlinker.legacy.databinding.CardLinkBinding
import com.wasee292.wlinker.legacy.db.entity.Link

class MainAdapter : ListAdapter<Link, MainAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<Link>() {
        override fun areItemsTheSame(oldItem: Link, newItem: Link): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Link, newItem: Link): Boolean =
            oldItem.value == newItem.value && oldItem.tags == newItem.tags
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(CardLinkBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: CardLinkBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(link: Link) {
            binding.apply {
                tvLink.text = link.value
                link.tags.forEach {
                    chipGroupTags.addView(
                        Chip(binding.root.context).apply {
                            text = it.value
                        }
                    )
                }
            }
        }
    }
}