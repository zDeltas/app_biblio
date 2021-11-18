package com.damien.biblio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


class LivreAdapter(private val onClick: (Livre) -> Unit) :
    ListAdapter<Livre, LivreAdapter.LivreViewHolder>(LivreDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LivreViewHolder =
        LivreViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )

    override fun onBindViewHolder(holder: LivreViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class LivreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName by lazy {
            itemView.findViewById<TextView>(R.id.TVTitre)
        }

        private val tvAuteur by lazy {
            itemView.findViewById<TextView>(R.id.TVAuteur)
        }

        init {
            itemView.setOnClickListener {
                onClick(getItem(adapterPosition))
            }
        }

        fun bind(livre: Livre) {
            tvName.text = livre.titre
            tvAuteur.text = livre.auteur
        }
    }

    class LivreDiffUtil : DiffUtil.ItemCallback<Livre>() {
        override fun areItemsTheSame(oldItem: Livre, newItem: Livre): Boolean {
            return oldItem.titre == newItem.titre || oldItem.auteur == newItem.auteur
        }

        override fun areContentsTheSame(oldItem: Livre, newItem: Livre): Boolean {
            return oldItem == newItem
        }
    }
}