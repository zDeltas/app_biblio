package com.damien.biblio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    var auth = FirebaseAuth.getInstance();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val mail = findViewById<EditText>(R.id.emailIn);
        val password = findViewById<EditText>(R.id.passwordIn);
        val login = findViewById<Button>(R.id.loginBtn)
        val signUp = findViewById<Button>(R.id.signupBtn)

        login.setOnClickListener(){

            val txt_mail = mail.text.toString();
            val txt_password = password.text.toString();

            login(txt_mail, txt_password);

        }

        signUp.setOnClickListener(){
            startActivity(Intent(this, signupActivity::class.java))
        }
    }

    private fun login(txt_mail : String, txt_password : String){
        auth.signInWithEmailAndPassword(txt_mail, txt_password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Toast.makeText(baseContext, "Authentication Success.",
                    Toast.LENGTH_SHORT).show();
                startActivity(Intent(this, MainActivity::class.java))
                finish()
                // alert-dialog
            } else {
                Toast.makeText(baseContext, "Authentication failed.",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}