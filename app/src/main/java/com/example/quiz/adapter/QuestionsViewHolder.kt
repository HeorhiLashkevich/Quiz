package com.example.quiz.adapter

import androidx.recyclerview.widget.RecyclerView


import com.example.quiz.databinding.ItemQuestionsBinding
import com.example.quiz.network.Question
import java.util.*
import kotlin.collections.ArrayList


class QuestionsViewHolder(
    private val binding: ItemQuestionsBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Question) {
        val answer: ArrayList<String> = arrayListOf(item.correctAnswer, item.incorrectAnswers[0], item.incorrectAnswers[1], item.incorrectAnswers[2])
        answer.shuffle()
        binding.question.text = item.question
        binding.firstOption.text = answer[0]
        binding.secondOption.text = answer[1]
        binding.thirdOption.text = answer[2]
        binding.fourthOption.text = answer[3]
    }
}