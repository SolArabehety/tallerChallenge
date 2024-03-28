package com.solara.myapplication.repository


import com.solara.myapplication.Constants.LOGIN_DEFAULT_DELAY_IN_MILLIS
import com.solara.myapplication.Constants.PASSWORD
import com.solara.myapplication.Constants.USERNAME
import com.solara.myapplication.data.LoginStatus
import kotlinx.coroutines.delay

class LoginRepository {


    suspend fun simulateLogin(username: String, password: String): LoginStatus {
        delay(LOGIN_DEFAULT_DELAY_IN_MILLIS) // simulate 2 seconds delay

        return if (username == USERNAME && password == PASSWORD) {
            LoginStatus.SUCCESS
        } else {
            LoginStatus.WRONG_PASSWORD
        }
    }


}