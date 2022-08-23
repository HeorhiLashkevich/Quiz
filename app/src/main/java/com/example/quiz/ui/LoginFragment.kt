package com.example.quiz.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.quiz.AfterChangedTextListner
import com.example.quiz.R
import com.example.quiz.adapter.UserViewModel
import com.example.quiz.databinding.FragmentLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {

    private val viewUserModel by viewModels<UserViewModel>()

    private lateinit var binding: FragmentLoginBinding
    private var returnToSingUp: TextView? = null
    private var errorTextEmailLogIn: TextView? = null
    private var errorTextPasswordLogIn: TextView? = null
    private var signUpButtonLogIn: Button? = null
    private var emailLogIn: EditText? = null
    private var passwordLogIn: EditText? = null
    private var currentEmailUser : String? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewUserModel.currentUserEmail.observe(viewLifecycleOwner) {
            currentEmailUser = it
        }

        binding.root.run {
            returnToSingUp = findViewById(R.id.returnToSingUp)
            returnToSingUp?.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, SignUpFragment())
                    .addToBackStack("")
                    .commit()
            }
            signUpButtonLogIn = findViewById(R.id.signUpButtonLogIn)
            emailLogIn = findViewById(R.id.emailLogIn)
            passwordLogIn = findViewById(R.id.passwordLogIn)
            errorTextEmailLogIn = findViewById(R.id.errorTextEmailLogIn)
            errorTextPasswordLogIn = findViewById(R.id.errorTextPasswordLogIn)

            signUpButtonLogIn?.setOnClickListener() {
                lifecycleScope.launch(Dispatchers.IO) {
                    if (viewUserModel.findUser(
                            emailLogIn?.text.toString(),
                            passwordLogIn?.text.toString()

                        ) != null
                    ) {
                        viewUserModel.currentEmail(emailLogIn?.text.toString())
                        viewUserModel.currentUser(
                            emailLogIn?.text.toString(),
                            passwordLogIn?.text.toString()
                        )

                        parentFragmentManager.beginTransaction()
                            .replace(
                                R.id.container,
                                CategoryFragment(currentEmailUser.toString())
                            )
                            .addToBackStack("")
                            .commit()
                    } else Toast.makeText(
                        requireContext(),
                        "Такого пользователя не существует или введены неверные данные",
                        12
                    ).show()
                }
            }


            emailLogIn?.addTextChangedListener(AfterChangedTextListner {
                validateForm()
                if (emailLogIn?.text.toString().isNotBlank()) {
                    errorTextEmailLogIn?.visibility = View.INVISIBLE
                }
            })
            passwordLogIn?.addTextChangedListener(AfterChangedTextListner {
                validateForm()
                if (passwordLogIn?.text.toString().isNotBlank()) {
                    errorTextPasswordLogIn?.visibility = View.INVISIBLE
                }
            })
        }


    }

    private fun validateForm() {
        binding.run {
            signUpButtonLogIn?.isEnabled = emailLogIn?.text.toString().isNotBlank() &&
                    passwordLogIn?.text.toString().isNotBlank()
        }

    }
}


