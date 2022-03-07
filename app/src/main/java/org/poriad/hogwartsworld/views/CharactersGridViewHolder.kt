package org.poriad.hogwartsworld.views

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.poriad.hogwartsworld.ListCharactersQuery
import org.poriad.hogwartsworld.databinding.CharacterCardGridBinding

class CharactersGridViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private var binding = CharacterCardGridBinding.bind(view)

    fun bind(character: ListCharactersQuery.GetAllCharacter?) {
        binding.characterName.text = character?.name
        Picasso.get().load(character?.image?.replace("http", "https")).into(binding.characterImage)
    }
}