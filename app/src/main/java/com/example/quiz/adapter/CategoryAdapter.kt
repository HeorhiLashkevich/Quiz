package com.example.quiz.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.R
import com.example.quiz.databinding.ItemCategoriesBinding

class CategoryAdapter(
    private val onItemClick: ((name: String) -> Unit)

) : RecyclerView.Adapter<CategoryViewHolder>() {
    private var list = arrayListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ItemCategoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )


    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            onItemClick.invoke(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    fun setList(list: ArrayList<String>) {
        this.list = list
        notifyDataSetChanged()

    }


}