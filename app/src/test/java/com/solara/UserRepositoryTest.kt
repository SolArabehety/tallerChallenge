package com.solara

import com.solara.myapplication.data.repository.UserRepositoryImpl
import com.solara.myapplication.data.responses.LoginResponse
import junit.framework.TestCase.assertEquals
import org.junit.Test

class UserRepositoryTest {

    @Test
    fun `test successful login`() {
        // Given
        val username = "admin"
        val password = "admin"
        val userRepository = UserRepositoryImpl()

        // When
        val result = userRepository.simulateLogin(username, password)

        // Then
        assertEquals(LoginResponse.SUCCESS, result)
    }

    @Test
    fun `test error login`() {
        // Given
        val username = "admin"
        val password = "wrong"
        val userRepository = UserRepositoryImpl()

        // When
        val result = userRepository.simulateLogin(username, password)

        // Then
        assertEquals(LoginResponse.WRONG_PASSWORD, result)
    }
}