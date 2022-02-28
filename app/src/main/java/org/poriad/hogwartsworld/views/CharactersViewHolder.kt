package org.poriad.hogwartsworld.views

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.poriad.hogwartsworld.ListCharactersQuery
import org.poriad.hogwartsworld.databinding.CharacterCardBinding

class CharactersViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private var binding = CharacterCardBinding.bind(view)

    fun bind(character: ListCharactersQuery.GetAllCharacter?) {
        binding.textCharacter.text = character?.name
        binding.characterHouse.text = character?.house
        binding.characterGender.text = character?.gender
        binding.characterSpecie.text = character?.specie
        binding.characterBirthday.text = character?.dateOfBirth

        Picasso.get().load(character?.image?.replace("http", "https")).into(binding.characterImage)
    }
}