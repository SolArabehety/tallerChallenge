package com.solara.myapplication.ui.status


sealed class LoginUiStatus {
    data object Loading : LoginUiStatus()
    data object Success : LoginUiStatus()
    class Error(val message: String) : LoginUiStatus()
}

