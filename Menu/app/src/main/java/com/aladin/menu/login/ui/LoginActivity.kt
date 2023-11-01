package com.aladin.menu.login.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.aladin.menu.databinding.ActivityLoginBinding
import com.aladin.menu.login.viewmodel.LoginViewModel
import com.aladin.menu.login.viewmodel.LoginViewModelFactory
import com.aladin.menu.main.ui.MainActivity
import com.aladin.menu.util.Watcher

class LoginActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, LoginViewModelFactory())[LoginViewModel::class.java]

        binding.button.setOnClickListener {
            viewModel.validateAndGoToMainScreen(
                binding.name.text.toString(),
                binding.phone.text.toString()
            )
        }
    }

    override fun onStart() {
        super.onStart()

        viewModel.isValidFormLiveData.observe(this) { isValid ->
            if (isValid) {
                goToMainScreen()
            }
        }

        viewModel.errorMessageLiveData.observe(this) { message ->
            handleFormErrors(message)

        }
    }

    private fun handleFormErrors(message: String){
        with(binding){

            val emptyName = name.text.toString().isEmpty()
            val emptyPhone = phone.text.toString().isEmpty()

            nameInputLayout.error = if (emptyName) message else null
            phoneInputLayout.error = if (emptyPhone) message else null

            name.addTextChangedListener(Watcher {
                 nameInputLayout.error = null
            })

            phone.addTextChangedListener(Watcher {
                 phoneInputLayout.error = null
            })
        }
    }


    private fun goToMainScreen() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

}