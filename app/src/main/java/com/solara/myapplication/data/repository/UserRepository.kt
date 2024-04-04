package com.solara.myapplication.data.repository


import android.util.Log
import com.solara.myapplication.Constants.PASSWORD
import com.solara.myapplication.Constants.USERNAME
import com.solara.myapplication.data.exceptions.EmptyDataException
import com.solara.myapplication.data.exceptions.GenericException
import com.solara.myapplication.data.responses.LoginResponse
import javax.inject.Inject

/**
 * Repository for managing user-related data.
 *
 * Provides abstraction over the data layer, offering an API to perform
 * operations like authentication, fetching user profiles, and updating user
 * information. This repository hides the underlying data source, which could
 * be a remote server, a local database, or a combination of both.
 *
 */
class UserRepository @Inject constructor() {

    /**
     * Simulates a login with given credentials.
     * Use @see USERNAME and @see PASSWORD constants to simulate a successful login.
     */
    @Throws(EmptyDataException::class)
    fun simulateLogin(username: String, password: String): LoginResponse {
        if (username.isBlank() || password.isBlank()) {
            throw EmptyDataException()
        }

        return if (username == USERNAME && password == PASSWORD) {
            LoginResponse.SUCCESS
        } else {
            LoginResponse.WRONG_PASSWORD
        }
    }


}