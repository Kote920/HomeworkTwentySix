package com.example.homeworktwentysix.presentation.adapter

import android.opengl.Visibility
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworktwentysix.databinding.ItemRecyclerViewBinding
import com.example.homeworktwentysix.domain.model.Category
import com.example.homeworktwentysix.presentation.model.CategoryUI

class CategoriesRecyclerAdapter() :
    ListAdapter<CategoryUI, CategoriesRecyclerAdapter.CategoriesViewHolder>(CategoriesDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CategoriesViewHolder(
        ItemRecyclerViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind()
    }

    inner class CategoriesViewHolder(private val binding: ItemRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var model: CategoryUI
        private var childList: List<CategoryUI>? = null

        fun bind() {
            model = currentList[adapterPosition]
            childList = model.children
            val counter = model.parentCounter
            binding.apply {

                when (counter) {
                    1 -> binding.circle1.visibility = View.VISIBLE

                    2 -> {
                        binding.circle1.visibility = View.VISIBLE
                        binding.circle2.visibility = View.VISIBLE
                    }

                    3 -> {
                        binding.circle1.visibility = View.VISIBLE
                        binding.circle2.visibility = View.VISIBLE
                        binding.circle3.visibility = View.VISIBLE
                    }

                    4 -> {
                        binding.circle1.visibility = View.VISIBLE
                        binding.circle2.visibility = View.VISIBLE
                        binding.circle3.visibility = View.VISIBLE
                        binding.circle4.visibility = View.VISIBLE
                    }


                }

                tvName.text = model.name

                if (!childList.isNullOrEmpty()) {
                    val childAdapter = CategoriesRecyclerAdapter()
                    binding.childRecycler.adapter = childAdapter
                    childRecycler.layoutManager = LinearLayoutManager(root.context)
                    childAdapter.submitList(childList)
                }
            }
            listeners()
        }

        private fun listeners() {
            binding.root.setOnClickListener {
                model.open = !model.open

                if(model.open){
                    if (!childList.isNullOrEmpty()) {
                        binding.childRecycler.visibility = View.VISIBLE
                    }
                }else{
                    binding.childRecycler.visibility = View.GONE
                }

            }
        }

    }

    class CategoriesDiffUtil : DiffUtil.ItemCallback<CategoryUI>() {
        override fun areItemsTheSame(oldItem: CategoryUI, newItem: CategoryUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CategoryUI, newItem: CategoryUI): Boolean {
            return oldItem == newItem
        }
    }

}
