package org.poriad.hogwartsworld.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import okhttp3.OkHttpClient
import org.poriad.hogwartsworld.Apollo
import org.poriad.hogwartsworld.ListCharactersQuery
import org.poriad.hogwartsworld.R
import org.poriad.hogwartsworld.databinding.FragmentCharacterGridBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CharacterGridFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
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