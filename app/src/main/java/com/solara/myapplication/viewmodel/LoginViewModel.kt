package com.solara.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solara.myapplication.data.LoginStatus
import com.solara.myapplication.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(private val repository: LoginRepository = LoginRepository()) : ViewModel() {
    val loginStatus = MutableLiveData<LoginStatus>()


    fun login(username: String, password: String) {
        loginStatus.postValue(LoginStatus.LOADING)

        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.simulateLogin(username, password)
            withContext(Dispatchers.Main) {
                if (result == LoginStatus.SUCCESS)
                    loginStatus.postValue(LoginStatus.SUCCESS)
                else {
                    loginStatus.postValue(LoginStatus.WRONG_PASSWORD)
                }
            }
        }

    }

}