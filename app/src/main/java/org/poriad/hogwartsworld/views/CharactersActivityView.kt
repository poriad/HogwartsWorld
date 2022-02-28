package org.poriad.hogwartsworld.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.apollographql.apollo3.ApolloClient
import org.poriad.hogwartsworld.R
import org.poriad.hogwartsworld.databinding.ActivityCharactersBinding


class CharactersActivityView : AppCompatActivity() {
    private lateinit var apolloClient: ApolloClient
    private lateinit var binding: ActivityCharactersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, CharacterFragment())
            .commit()
    }
}
