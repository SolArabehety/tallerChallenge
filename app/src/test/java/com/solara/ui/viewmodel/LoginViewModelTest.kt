package com.solara.ui.viewmodel

import com.solara.myapplication.data.repository.UserRepositoryImpl
import com.solara.myapplication.ui.viewmodel.LoginViewModel
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class LoginViewModelTest {

    private lateinit var viewModel: LoginViewModel
    private lateinit var userRepository: UserRepositoryImpl


    @Before
    fun setUp() {
        userRepository = mockk()
        viewModel = LoginViewModel(userRepository)
    }

    @After
    fun tearDown() {

    }





    @Test
    fun `test login success`() = runTest {
        viewModel.login("username", "password")


    }

    @Test
    fun `test loading status when login attempt`() = runTest {
        viewModel.login("username", "password")


    }


}