package com.solara.myapplication.data.exceptions



/**
 * this is a custom exception used to handle empty data fields
 */
class EmptyDataException : Exception(message){

    companion object {
        const val message = "Fields cannot be empty"
    }
}