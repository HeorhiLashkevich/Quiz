package com.example.quiz.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.quiz.databinding.ItemQuestionsBinding
import com.example.quiz.network.Question


class QuestionsAdapter() : RecyclerView.Adapter<QuestionsViewHolder>() {

    private var list = arrayListOf<Question>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionsViewHolder {
        return QuestionsViewHolder(
            ItemQuestionsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: QuestionsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    fun setQuestions(list: ArrayList<Question>) {
        this.list = list
        notifyDataSetChanged()
    }
}