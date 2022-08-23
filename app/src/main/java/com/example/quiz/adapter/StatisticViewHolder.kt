package com.example.quiz.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.databinding.ItemStatisticBinding
import com.example.quiz.network.Question

class StatisticViewHolder(
    private val binding: ItemStatisticBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(list: List<Question>) {

        binding.statistic.text
    }
}
