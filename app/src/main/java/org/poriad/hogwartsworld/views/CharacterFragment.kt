package org.poriad.hogwartsworld.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import okhttp3.OkHttpClient
import org.poriad.hogwartsworld.ListCharactersQuery
import org.poriad.hogwartsworld.R
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
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentCharacterBinding
    private lateinit var apolloClient: ApolloClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterBinding.inflate(layoutInflater)
        apolloClient = ApolloClient.Builder()
            .serverUrl("https://hogwarts-peterpin.herokuapp.com/graphql")
            .okHttpClient(OkHttpClient().newBuilder().build())
            .build()
        lifecycleScope.launchWhenResumed {
            val response = apolloClient.query(ListCharactersQuery()).execute()

            Log.d("LaunchList", "Success ${response.data}")
            val launches = response.data?.getAllCharacters
            if (launches != null && !response.hasErrors()) {
                val adapter = CharactersAdapter(launches)
                binding.recyclerViewCharacters.layoutManager = LinearLayoutManager(activity)
                binding.recyclerViewCharacters.adapter = adapter
            }
        }
        return inflater.inflate(R.layout.fragment_character, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CharacterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CharacterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}