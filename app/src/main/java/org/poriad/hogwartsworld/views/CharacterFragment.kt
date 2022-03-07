package org.poriad.hogwartsworld.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import org.poriad.hogwartsworld.Apollo
import org.poriad.hogwartsworld.databinding.FragmentCharacterBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CharacterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CharacterFragment : Fragment() {
    private lateinit var binding: FragmentCharacterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterBinding.inflate(inflater, container, false)
        getCharacters()
        return binding.root
    }

    private fun getCharacters() {
        lifecycleScope.launchWhenResumed {
                val adapter = Apollo.getAllCharacters()?.let { CharactersAdapter(it) }
                binding.recyclerViewCharacters.layoutManager = LinearLayoutManager(context)
                binding.recyclerViewCharacters.adapter = adapter
        }
    }
}