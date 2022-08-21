package com.example.quiz.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.databinding.ItemCategoriesBinding
import com.example.quiz.databinding.ItemQuestionsBinding
import com.example.quiz.network.Question
import com.example.quiz.network.QuizCategories
import kotlinx.android.synthetic.main.item_categories.view.*

class CategoryViewHolder(
    private val binding: ItemCategoriesBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item : String) {
        binding.category.text = item
    }
}