package com.solara.myapplication.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.solara.myapplication.R
import com.solara.myapplication.databinding.ActivityLoginBinding
import com.solara.myapplication.ui.extensions.enableIfNotEmpty
import com.solara.myapplication.ui.extensions.hideKeyboard
import com.solara.myapplication.ui.status.LoginUiStatus
import com.solara.myapplication.ui.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initViews()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.loginStatus.observe(this) { loginStatus ->
            binding.btnLogin.isClickable = true

            when (loginStatus) {
                is LoginUiStatus.Success ->
                    Toast.makeText(this, getText(R.string.login_correct), Toast.LENGTH_SHORT).show()

                is LoginUiStatus.Error ->
                    Toast.makeText(this, loginStatus.message, Toast.LENGTH_SHORT).show()

                else -> {}
            }
        }
    }

    private fun initViews() {
        binding.btnLogin.enableIfNotEmpty(listOf(binding.etUsername, binding.etPassword))

        binding.btnLogin.setOnClickListener {
            hideKeyboard()

            viewModel.login(
                binding.etUsername.text.trim().toString(),
                binding.etPassword.text.trim().toString()
            )
        }

    }

}