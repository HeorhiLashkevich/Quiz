package com.example.quiz.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quiz.R
import com.example.quiz.adapter.CategoryAdapter
import com.example.quiz.adapter.CategoryViewModel
import com.example.quiz.databinding.FragmentCategoryBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryFragment(
    private var userEmail: String
) : Fragment() {

    private lateinit var binding: FragmentCategoryBinding
    private val viewModel by viewModels<CategoryViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCategories()
        viewModel.categories.observe(viewLifecycleOwner) {
            setList(it)
        }


    }

    private fun setList(list: ArrayList<String>) {
        binding.listItem.run {
            if (adapter == null) {
                adapter = CategoryAdapter {
                    lifecycleScope.launch(Dispatchers.IO) {
                        parentFragmentManager.beginTransaction()
                            .replace(R.id.container, QuizFragment(it, userEmail))
                            .addToBackStack("")
                            .commit()
                    }
                }
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
            (adapter as? CategoryAdapter)?.setList(list)

        }
    }
}



