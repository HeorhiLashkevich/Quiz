package com.example.quiz.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.quiz.R
import com.example.quiz.databinding.FragmentStatisticBinding
import com.example.quiz.di.SharedPreferencesRepository
import com.example.quiz.network.Question
import com.example.quiz.network.Questions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StatsFragment(
    private var currentEmail: String,
    private val result: Int,
    private var currentQuestions: List<Question>
) : Fragment() {

    private lateinit var binding: FragmentStatisticBinding
    private lateinit var backButton: ImageButton
    private lateinit var addToFavorites: Button
    private lateinit var sharedPreferences: SharedPreferencesRepository
    private lateinit var currentResult: TextView


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
            currentResult = findViewById(R.id.currentResult)
            currentResult.text = "$result/10"
            backButton = findViewById(R.id.backButtonFromStatsToCategories)
            backButton.setOnClickListener {
                lifecycleScope.launch(Dispatchers.IO) {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.container, CategoryFragment(currentEmail))
                        .addToBackStack("")
                        .commit()
                }
            }
        }
    }
}