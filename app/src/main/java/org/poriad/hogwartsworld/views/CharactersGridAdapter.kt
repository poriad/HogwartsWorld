package org.poriad.hogwartsworld.views

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import org.poriad.hogwartsworld.ListCharactersQuery
import org.poriad.hogwartsworld.R

class CharactersGridAdapter(
    private val characters: List<ListCharactersQuery.GetAllCharacter?>
) : RecyclerView.Adapter<CharactersGridViewHolder>() {
    private var contextParent: Context? = null
    private lateinit var cardView: CardView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersGridViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextParent = parent.context
        return CharactersGridViewHolder(layoutInflater.inflate(R.layout.character_card_grid, parent, false))
    }

    override fun onBindViewHolder(holder: CharactersGridViewHolder, position: Int) {
        cardView = holder.itemView.findViewById(R.id.character_card_grid)
        val character = characters[position]
        cardView.setOnClickListener {
            Toast.makeText(contextParent, character?.name, Toast.LENGTH_LONG).show()
        }
        holder.bind(character)
    }

    override fun getItemCount(): Int {
        return characters.size
    }
}