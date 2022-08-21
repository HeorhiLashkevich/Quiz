package com.example.quiz.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.quiz.R
import com.example.quiz.databinding.FragmentStatisticBinding
import com.example.quiz.di.SharedPreferencesRepository
import com.example.quiz.network.Question
import com.example.quiz.network.Questions

class StatsFragment(
    private var currentEmail: String,
    private var currentQuestions: Questions
) : Fragment() {

    private lateinit var binding: FragmentStatisticBinding
    private lateinit var backButton: Button
    private lateinit var addToFavorites: Button
    private lateinit var sharedPreferences: SharedPreferencesRepository


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStatisticBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        sharedPreferences = SharedPreferencesRepository(requireContext(), currentEmail)

        binding.root.run {

            addToFavorites = findViewById(R.id.addToFavorites)
            addToFavorites.setOnClickListener {
                sharedPreferences.addTheQuestionsToFavorites(currentQuestions, currentEmail)
            }

            backButton = findViewById(R.id.backButtonFromStatsToCategories)
            backButton.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, CategoryFragment())
                    .addToBackStack("")
                    .commit()
            }
        }
    }
}