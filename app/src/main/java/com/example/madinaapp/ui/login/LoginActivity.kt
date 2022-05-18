package com.example.madinaapp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.example.madinaapp.HomeActivity
import com.example.madinaapp.R
import com.example.madinaapp.databinding.ActivityLoginBinding
import com.example.madinaapp.ui.signUp.SignUpActivity
import com.example.myvacation.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


lateinit var sign: Button
lateinit var signin: Button

lateinit var mail: String
lateinit var pass : String

lateinit var  viewModel : LoginViewModel
lateinit var  binding : ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        // checkCurrentUser()
        sign = findViewById(R.id.sig_up_btn)
        signin = findViewById(R.id.sig_in_btn)




        viewModel.messageLivedata.observe(this,{
           // if (it.contains("login succes"))
            // goToHomeScreen()
        })

        sign.setOnClickListener{

            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        signin.setOnClickListener {
            getInputData()

        }

    }

    private fun getInputData() {
        mail = binding.mailLayout.text.toString()
        Log.e("mail" ,  mail.toString())

        pass= binding.passLayout.text.toString()
       login(mail, pass)
    }

    private fun goToHomeScreen() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }



     fun initViewModel(): LoginViewModel {
        return ViewModelProvider(this).get(LoginViewModel::class.java)
    }


    private fun checkCurrentUser() {
       var fAouth : FirebaseAuth = FirebaseAuth.getInstance()
        val user : FirebaseUser? = fAouth.currentUser
        if(user!=null){
         goToHomeScreen()
        }

    }
    fun login(mail: String ,pass:String ){

      var  auth= FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(mail, pass)
            .addOnCompleteListener(this) { task ->
                if(task.isSuccessful) {
                    Log.e("login" ,  "success")
                    goToHomeScreen()

                }
                else{
                    Log.e("login" , task.exception.localizedMessage.toString() )

                    Log.e("login" ,  "fail")

            }}


    }

}