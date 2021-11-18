package com.damien.biblio

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*


class MainActivity : AppCompatActivity() {

    private val mRecyclerView by lazy { findViewById<RecyclerView>(R.id.recycler_view) }

    private lateinit var auth: FirebaseAuth

    private var livre = ArrayList<Livre>()

    private lateinit var myAdapter: LivreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        checkUser()

        myAdapter = LivreAdapter {
            val intent = Intent(this, LivreDetail::class.java)
            Log.e("Detail livre", "${it.id} clicked")
            intent.putExtra("auteur","${it.auteur}")
            intent.putExtra("titre","${it.titre}")
            intent.putExtra("date","${it.date}")
            intent.putExtra("lu","${it.lu}")
            startActivity(intent)
        }

        mRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
            itemAnimator = DefaultItemAnimator()
            adapter = myAdapter
        }
    }

    private fun postList(){
        myAdapter.submitList(livre)
    }

    private fun checkUser() {
        val firebaseUser = auth.currentUser

        if (firebaseUser != null) {
            loadFireStore()
        } else {
            loadFireStore()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun loadFireStore() {
        val db = FirebaseFirestore.getInstance()
        db.collection("Livres")
            .addSnapshotListener(object : EventListener<QuerySnapshot>{
                override fun onEvent(
                    value: QuerySnapshot?,
                    error: FirebaseFirestoreException?
                ){
                    if(error != null){
                        Log.e("Error firestore", error.message.toString())
                        return
                    }

                    for(dc: DocumentChange in value?.documentChanges!!){
                        if(dc.type == DocumentChange.Type.ADDED){
                            Log.e("ifhdsiuf", dc.document.id)
                            livre.add(Livre(dc.document.id, dc.document.get("titre").toString(), dc.document.get("auteur").toString(), dc.document.get("date").toString(), dc.document.get("lu").toString(),))
                        }
                    }

                    postList()

                }
            })
    }

    private fun signOut() {
        // [START auth_sign_out]
        auth.signOut()
        checkUser()
        // [END auth_sign_out]

    }

    override fun onSupportNavigateUp(): Boolean {
        signOut() // Retour sur Login  lorsque click sur bouton retour
        return super.onSupportNavigateUp()
    }


}