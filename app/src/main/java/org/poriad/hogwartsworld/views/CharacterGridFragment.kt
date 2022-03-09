package org.poriad.hogwartsworld.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import org.poriad.hogwartsworld.Apollo
import org.poriad.hogwartsworld.databinding.FragmentCharacterGridBinding

class CharacterGridFragment : Fragment() {
    private lateinit var binding: FragmentCharacterGridBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterGridBinding.inflate(inflater, container, false)
        getCharacters()
        return binding.root
    }

    private fun getCharacters() {
        lifecycleScope.launchWhenResumed {
            val adapter = Apollo.getAllCharacters()?.let { CharactersGridAdapter(it) }
            binding.recyclerViewCharactersGrid.layoutManager = GridLayoutManager(context, 3)
            binding.recyclerViewCharactersGrid.adapter = adapter
        }
    }
}