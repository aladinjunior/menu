package com.aladin.menu.login.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.aladin.menu.databinding.ActivityLoginBinding
import com.aladin.menu.login.viewmodel.LoginViewModel
import com.aladin.menu.login.viewmodel.LoginViewModelFactory
import com.aladin.menu.main.ui.MainActivity

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
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun goToMainScreen() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

}