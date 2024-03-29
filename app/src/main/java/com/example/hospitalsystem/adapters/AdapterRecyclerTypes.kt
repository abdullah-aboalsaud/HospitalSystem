package com.example.hospitalsystem.adapters

import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.hospitalsystem.R
import com.example.hospitalsystem.R.color.black
import com.example.hospitalsystem.R.color.mintGreen
import com.example.hospitalsystem.data.ModelCategory
import com.example.hospitalsystem.databinding.ItemTabsBinding

class AdapterRecyclerTypes : RecyclerView.Adapter<AdapterRecyclerTypes.TypesViewHolder>() {


    inner class TypesViewHolder(val binding: ItemTabsBinding) : ViewHolder(binding.root) {
        fun bind(data: ModelCategory, isSelected: Boolean) {
            binding.apply {
                tvCategory.text = data.title
                if (isSelected) {
                    tvCategory.background =
                        ColorDrawable(getColor(itemView.context, mintGreen))
                    tvCategory.setTextColor(itemView.context.resources.getColor(R.color.white))
                } else {
                    tvCategory.setBackgroundResource(R.drawable.item_tab_background)
                    tvCategory.setTextColor(getColor(itemView.context, black))
                }

            }
        }
    }


    // diffUtil
    private val diffUtil = object : DiffUtil.ItemCallback<ModelCategory>() {
        override fun areItemsTheSame(oldItem: ModelCategory, newItem: ModelCategory): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: ModelCategory, newItem: ModelCategory): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffUtil)




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypesViewHolder {
        return TypesViewHolder(
            ItemTabsBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    init {
        differ.addListListener { _, _ ->
            notifyItemChanged(selectedPosition)
        }
    }

    private var selectedPosition = -1
    override fun onBindViewHolder(holder: TypesViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.bind(data, selectedPosition == position)
        holder.binding.tvCategory.setOnClickListener {

            if (selectedPosition > 0) {
                notifyItemChanged(selectedPosition)
            }

            if (selectedPosition <= 0) {
                notifyItemChanged(selectedPosition)
            }

            selectedPosition = holder.adapterPosition
            notifyItemChanged(selectedPosition)

        }
    }


}