package com.solara.myapplication.ui.status


sealed interface LoginUiStatus {
    data object Loading : LoginUiStatus
    data object Success : LoginUiStatus
    class Error(val message: String) : LoginUiStatus
}

