package com.solara.myapplication.ui.extensions

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText


/**
* function to enable or disable it based on the content of a list of EditText fields.
*
* @param editTexts A list of EditText fields to watch.
*/
fun Button.enableIfNotEmpty(editTexts: List<EditText>) {
    val watcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            // Check if all EditTexts are not blank
            this@enableIfNotEmpty.isEnabled = editTexts.all { it.text.isNotBlank() }
        }
    }

    // Add the TextWatcher to each EditText in the list
    editTexts.forEach { it.addTextChangedListener(watcher) }

    // Initial check in case the EditText fields are not empty to begin with
    this.isEnabled = editTexts.all { it.text.isNotBlank() }
}


fun Activity.hideKeyboard(){
    // Only runs if there is a view that is currently focused
    this.currentFocus?.let { view ->
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}