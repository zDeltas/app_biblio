package com.damien.biblio


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore


class LivreDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PrintFireStorageDB()
    }

    private fun PrintFireStorageDB() {
        val db = FirebaseFirestore.getInstance()
        db.collection("Livres").document(intent.getStringExtra("1").toString())
            .get()
            .addOnSuccessListener {livreRef ->
                Log.d("dsqiuhdsq", "DocumentSnapshot written with ID: ${livreRef.id}")
            }


    }
}