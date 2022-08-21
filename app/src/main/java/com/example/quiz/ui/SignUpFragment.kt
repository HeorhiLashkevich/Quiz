package com.example.quiz.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.quiz.AfterChangedTextListner
import com.example.quiz.R
import com.example.quiz.adapter.UserViewModel
import com.example.quiz.databinding.FragmentSignUpBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpFragment : Fragment() {

    private val viewUserModel by viewModels<UserViewModel>()


    private var binding: FragmentSignUpBinding? = null
    private var firstName: EditText? = null
    private var lastName: EditText? = null
    private var emailSignUp: EditText? = null
    private var passwordSignUp: EditText? = null
    private var signUpButton: Button? = null
    private var errorTextFirstNameSignUp: TextView? = null
    private var errorTextLastName: TextView? = null
    private var errorTextEmail: TextView? = null
    private var errorPasswordSingUp: TextView? = null
    private var textLogIn: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.root.run {
            firstName = findViewById(R.id.firstName)
            lastName = findViewById(R.id.lastName)
            emailSignUp = findViewById(R.id.emailSignUp)
            passwordSignUp = findViewById(R.id.passwordSignUp)
            signUpButton = findViewById(R.id.signUpButton)

            errorTextFirstNameSignUp = findViewById(R.id.errorTextFirstNameSignUp)
            errorTextLastName = findViewById(R.id.errorTextLastName)
            errorTextEmail = findViewById(R.id.errorTextEmail)
            errorPasswordSingUp = findViewById(R.id.errorPasswordSingUp)

            textLogIn = findViewById(R.id.textLogIn)
            textLogIn?.setOnClickListener() {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, LoginFragment())
                    .addToBackStack("")
                    .commit()
            }


            signUpButton?.setOnClickListener() {
                lifecycleScope.launch(Dispatchers.IO) {
                    viewUserModel.insertNewUser(
                        emailSignUp?.text.toString(),
                        firstName?.text.toString(),
                        lastName?.text.toString(),
                        passwordSignUp?.text.toString()
                    )
                }
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, LoginFragment())
                    .addToBackStack("")
                    .commit()
            }



            fun validateForm() {
                binding?.run {
                    signUpButton?.isEnabled = firstName?.text.toString().isNotBlank() &&
                            emailSignUp?.text.toString().isNotBlank() &&
                            passwordSignUp?.text.toString().isNotBlank() &&
                            signUpButton?.text.toString().isNotBlank()
                }

            }


            firstName?.addTextChangedListener(AfterChangedTextListner {
                validateForm()
                if (firstName?.text.toString().isNotBlank())
                    errorTextFirstNameSignUp?.visibility = View.INVISIBLE
            })
            lastName?.addTextChangedListener(AfterChangedTextListner {
                validateForm()
                if (lastName?.text.toString().isNotBlank())
                    errorTextLastName?.visibility = View.INVISIBLE
            })
            emailSignUp?.addTextChangedListener(AfterChangedTextListner {
                validateForm()
                if (emailSignUp?.text.toString().isNotBlank())
                    errorTextEmail?.visibility = View.INVISIBLE
            })
            passwordSignUp?.addTextChangedListener(AfterChangedTextListner {
                validateForm()
                if (passwordSignUp?.text.toString().isNotBlank())
                    errorPasswordSingUp?.visibility = View.INVISIBLE
            })


        }


    }


}