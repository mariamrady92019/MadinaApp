package com.example.madinaapp.ui.login

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("app:error")
fun setTextInputError(textInputLayout: TextInputEditText, error:String){

    textInputLayout.error=error

}