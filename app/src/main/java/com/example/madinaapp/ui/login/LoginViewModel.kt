package com.example.myvacation

import android.util.Patterns
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.FirebaseCommonKtxRegistrar

class LoginViewModel:ViewModel(){

    val email=ObservableField<String>()
    val emailError=ObservableField<String>()
    val pass=ObservableField<String>()
    val passError=ObservableField<String>()

     lateinit var auth:FirebaseAuth
     val messageLivedata=MutableLiveData<String>()


    fun validate():Boolean{

      var validate=true
        if (passError.get().isNullOrBlank()){
          passError.set("Please write your pass")
            validate=false}

        else
            passError.set(null)
        if (email.get().isNullOrBlank()){
            emailError.set("Please write your emai")
            validate =false}
        if (email.get().toString().equals(Patterns.EMAIL_ADDRESS.matcher(email.toString()).matches())){
            emailError.set(null)
            validate =false
        }

        return validate

    }

   fun login(){

      // auth= FirebaseAuth.getInstance()
       auth.signInWithEmailAndPassword(email.get()!!,pass.get()!!)
           . addOnCompleteListener { task ->
          if(task.isSuccessful) {

              messageLivedata.value="login succes"
          }
    else
     messageLivedata.value=task.exception?.localizedMessage
      }


               }

}



