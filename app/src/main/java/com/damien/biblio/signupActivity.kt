package com.damien.biblio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class signupActivity : AppCompatActivity() {
    var auth = FirebaseAuth.getInstance();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val mail = findViewById<EditText>(R.id.emailIn);
        val password = findViewById<EditText>(R.id.passwordIn);
        val SignupBtn = findViewById<Button>(R.id.SignupBtn)


        SignupBtn.setOnClickListener(){

            if (!Patterns.EMAIL_ADDRESS.matcher(mail.toString()).matches()){
                mail.error = "Email Invalide"
            }
            else if (TextUtils.isEmpty(password.toString())){
                password.error = "Mot de passe Invalide"
            }
            else if (password.length() < 6 ) {
                password.error = "Le MDP doit contenir au moins 6 charactères"
            }
            else {
                // Données valides check
                signup(mail.toString(), password.toString())
            }
        }

    }


    // Inscription à FireBase
    private fun signup(txt_mail : String, txt_password : String) {

        auth.createUserWithEmailAndPassword(txt_mail, txt_password)
            .addOnSuccessListener(this) {
            val user = auth.currentUser
            val email = user!!.email
            Toast.makeText(
                this, "Inscription réussie en tant que $email.",Toast.LENGTH_SHORT).show()
        }
            .addOnFailureListener { e ->
                // If sign up fails, display a message to the user.
                Toast.makeText(this, "Échec de l' inscription : ${e.message}.",Toast.LENGTH_SHORT).show()
            }
    }

}





