package com.example.quiz.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.databinding.ItemStatisticBinding
import com.example.quiz.network.Question

class StatisticAdapter(

) : RecyclerView.Adapter<StatisticViewHolder>() {
    private var list = arrayListOf<List<Question>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatisticViewHolder {
        return StatisticViewHolder(
            ItemStatisticBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )


    }

    override fun onBindViewHolder(holder: StatisticViewHolder, position: Int) {
        holder.bind(list[position])

    }

    override fun getItemCount() =
        list.size


    fun setList(list: ArrayList<List<Question>>) {
        this.list = list
        notifyDataSetChanged()

    }


}