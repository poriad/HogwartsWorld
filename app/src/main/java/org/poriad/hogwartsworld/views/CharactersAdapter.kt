package org.poriad.hogwartsworld.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.poriad.hogwartsworld.ListCharactersQuery
import org.poriad.hogwartsworld.R

class CharactersAdapter(
    private val characters: List<ListCharactersQuery.GetAllCharacter?>
) : RecyclerView.Adapter<CharactersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CharactersViewHolder(layoutInflater.inflate(R.layout.character_card, parent, false))
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount(): Int {
        return characters.size
    }
}