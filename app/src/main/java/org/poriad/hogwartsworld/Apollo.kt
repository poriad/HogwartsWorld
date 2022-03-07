package org.poriad.hogwartsworld

import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import okhttp3.OkHttpClient

class Apollo {
    companion object Queries {
        val apolloClient = ApolloClient.Builder()
            .serverUrl("https://hogwarts-peterpin.herokuapp.com/graphql")
            .okHttpClient(OkHttpClient().newBuilder().build())
            .build()

        suspend fun getAllCharacters(): List<ListCharactersQuery.GetAllCharacter?>? {
            val response = apolloClient.query(ListCharactersQuery()).execute()
            Log.d("LaunchList", "Success ${response.data}")
            var launches = emptyList<ListCharactersQuery.GetAllCharacter?>()
            if (launches != null && !response.hasErrors()) {
                launches = response.data?.getAllCharacters!!
            }
            return launches
        }
    }

}