package com.wasee292.wlinker.legacy.addlink

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wasee292.wlinker.legacy.Selectable
import com.wasee292.wlinker.legacy.databinding.CardSelectableTagBinding
import com.wasee292.wlinker.legacy.db.entity.Tag

class SelectableTagsAdapter : ListAdapter<Selectable<Tag>, SelectableTagsAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<Selectable<Tag>>() {
        override fun areItemsTheSame(oldItem: Selectable<Tag>, newItem: Selectable<Tag>) =
            oldItem.data.id == newItem.data.id

        override fun areContentsTheSame(oldItem: Selectable<Tag>, newItem: Selectable<Tag>) =
            oldItem.isSelected == newItem.isSelected
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(CardSelectableTagBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: CardSelectableTagBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(selectableTag: Selectable<Tag>) {
            binding.checkbox.apply {
                isChecked = selectableTag.isSelected
                text = selectableTag.data.value
                setOnCheckedChangeListener { _, isChecked ->
                    selectableTag.isSelected = isChecked
                }
            }
        }
    }
}