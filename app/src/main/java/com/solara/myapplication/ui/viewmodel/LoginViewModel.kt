package com.solara.myapplication.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solara.myapplication.Constants
import com.solara.myapplication.data.exceptions.EmptyDataException
import com.solara.myapplication.data.repository.UserRepository
import com.solara.myapplication.data.repository.UserRepositoryImpl
import com.solara.myapplication.data.responses.LoginResponse
import com.solara.myapplication.ui.status.LoginUiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject internal constructor(private val userRepository: UserRepository) :
    ViewModel() {

    val loginStatus: LiveData<LoginUiStatus> get() = _loginStatus
    private val _loginStatus = MutableLiveData<LoginUiStatus>()


    fun login(username: String, password: String) {

        viewModelScope.launch(Dispatchers.IO) {
            _loginStatus.postValue(LoginUiStatus.Loading)

            val state = withContext(Dispatchers.Default) {
                try {
                    delay(Constants.LOGIN_DEFAULT_DELAY_IN_MILLIS) // simulate 2 seconds delay

                    when (userRepository.simulateLogin(username, password)) {
                        LoginResponse.SUCCESS -> LoginUiStatus.Success
                        LoginResponse.WRONG_PASSWORD -> LoginUiStatus.Error("wrong password")
                        else -> LoginUiStatus.Error("an error occurred")
                    }
                } catch (exception: EmptyDataException) {
                    LoginUiStatus.Error("Fields cannot be empty")
                } catch (exception: Exception) {
                    LoginUiStatus.Error(exception.message ?: "An error occurred")
                }
            }

            withContext(Dispatchers.Main) {
                _loginStatus.value = state
            }
        }
    }

}