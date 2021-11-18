package com.damien.biblio


import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class LivreDetail : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_livre_detail)

        val TVdetail_titre : TextView = findViewById(R.id.TVdetail_titre) as TextView
        val TVdetail_auteur : TextView = findViewById(R.id.TVdetail_auteur) as TextView
        val TVdetail_lu : TextView = findViewById(R.id.TVdetail_lu) as TextView
        val TVdetail_parution : TextView = findViewById(R.id.TVdetail_parution) as TextView


        TVdetail_titre.text = intent.getStringExtra("titre")
        TVdetail_auteur.text = intent.getStringExtra("auteur")
        TVdetail_lu.text = intent.getStringExtra("lu")
        TVdetail_parution.text = intent.getStringExtra("date")
    }


}