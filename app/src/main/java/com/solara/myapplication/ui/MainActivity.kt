package com.solara.myapplication.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.solara.myapplication.R
import com.solara.myapplication.data.LoginStatus
import com.solara.myapplication.viewmodel.LoginViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)

        btnLogin.setOnClickListener {
            viewModel.login(etUsername.text.trim().toString(), etPassword.text.trim().toString())
        }

        viewModel.loginStatus.observe(this) { loginStatus ->
            if (loginStatus == LoginStatus.SUCCESS)
                Toast.makeText(this, getText(R.string.login_correct), Toast.LENGTH_SHORT).show()
            else if (loginStatus == LoginStatus.WRONG_PASSWORD)
                Toast.makeText(this, getText(R.string.wrong_credentials), Toast.LENGTH_SHORT).show()
        }


        val buttonEnablerWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                btnLogin.isEnabled = etUsername.text.toString().trim().isNotEmpty() &&
                        etPassword.text.toString().trim().isNotEmpty()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        }

        etUsername.addTextChangedListener(buttonEnablerWatcher)
        etPassword.addTextChangedListener(buttonEnablerWatcher)
    }

}