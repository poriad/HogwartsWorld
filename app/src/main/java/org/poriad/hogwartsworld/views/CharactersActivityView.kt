package org.poriad.hogwartsworld.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import org.poriad.hogwartsworld.R
import org.poriad.hogwartsworld.databinding.ActivityCharactersBinding


class CharactersActivityView : AppCompatActivity() {
    private lateinit var binding: ActivityCharactersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tabs.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab!!.position) {
                    0 -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.characters, CharacterGridFragment())
                            .commit()
                    }
                    1 -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.characters, CharacterFragment())
                            .commit()
                    }
                    else -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.characters, CharacterGridFragment())
                            .commit()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }
}
