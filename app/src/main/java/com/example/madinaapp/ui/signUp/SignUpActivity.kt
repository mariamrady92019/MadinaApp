package com.example.madinaapp.ui.signUp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.madinaapp.HomeActivity
import com.example.madinaapp.R
import com.example.madinaapp.databinding.ActivityHomeBinding
import com.example.madinaapp.databinding.ActivitySignUpBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    lateinit var name:String
    lateinit var ssn:String
    lateinit var phone:String
    lateinit var adress:String
    lateinit var mail:String
    lateinit var password:String
    lateinit var confirmpassword:String
    lateinit var fAouth : FirebaseAuth


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
      // checkCurrentUser()

        var btn = binding.sigUpBtn

        btn.setOnClickListener {
            getDataFromInput()

            signUp()

             }

    }




    private fun checkCurrentUser() {
        fAouth = FirebaseAuth.getInstance()
        val user : FirebaseUser? = fAouth.currentUser
        if(user!=null){
            goToHomeScreen()
        }

    }

    private fun goToHomeScreen() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }


    private fun signUp() {
        fAouth = FirebaseAuth.getInstance()

        val user = SignUpModel(name,ssn,phone,adress,mail,password)
        //firebase
      fAouth.createUserWithEmailAndPassword(mail,password).
      addOnCompleteListener(this) {
          if (it.isSuccessful) {
              Toast.makeText(this, "Successfully Singed Up", Toast.LENGTH_SHORT).show()
              //add to user collections
              // move
            goToHomeScreen()
            // finish()
          } else {
               Log.e("valid" ,  it.exception?.localizedMessage.toString())

              Toast.makeText(this, it.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
          }
      }


    }

    private fun chackValidationForSignUpData(mail: String , pass:String):Boolean{

        if (mail.toString().isEmpty()|| password.toString().isEmpty() || confirmpassword.toString().isEmpty()) {
            Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return false
        }

  //    if (!password.equals(confirmpassword) ){
//            Toast.makeText(this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT)
//                .show()
//          return false
//        }
     return true
    }

    private fun getDataFromInput() {
        name=binding.namelayout.text.toString()
         Log.e("name" , name.toString())
        ssn=binding.ssnLayout.text.toString()
        Log.e("name" , ssn.toString())

        phone=binding.phoneLayout.text.toString()
        Log.e("name" , phone.toString())

        adress=binding.addressLayout.text.toString()
        Log.e("name" , adress.toString())

        mail=binding.mailLayout.text.toString()
        Log.e("name" , mail.toString())

        if(mail.isNotBlank()==false){
            binding.mailLayout.setError("enter mail")
        }

        password=binding.mailLayout.text.toString()
        Log.e("name" , password.toString())
       // chackValidationForSignUpData(mail,password)
        confirmpassword =binding.confirmPasswordLayout.text.toString()
//
//        if(password.equals(confirmpassword)==false)
//        {
//            binding.confirmPasswordLayout.setError("must match the pass")
//        }
    }
}