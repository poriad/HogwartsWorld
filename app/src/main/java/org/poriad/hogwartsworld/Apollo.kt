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

        val apolloClientLocal = ApolloClient.Builder()
            .serverUrl("http://192.168.1.138:7000/graphql")
            .okHttpClient(OkHttpClient().newBuilder().build())
            .build()

        suspend fun getAllCharacters(): List<ListCharactersQuery.GetAllCharacter?>? {
            val response = apolloClientLocal.query(ListCharactersQuery()).execute()
            Log.d("LaunchList", "Success ${response.data}")
            var characters = emptyList<ListCharactersQuery.GetAllCharacter?>()
            if (characters != null && !response.hasErrors()) {
                characters = response.data?.getAllCharacters!!
            }
            return characters
        }

        suspend fun getQuiz(): List<QuestionsQuizQuery.GetQuiz?>? {
            val response = apolloClientLocal.query(QuestionsQuizQuery()).execute()
            Log.d("QuizQuery:", "Success: ${response.data}")
            var quiz = emptyList<QuestionsQuizQuery.GetQuiz?>()
            if (quiz != null && !response.hasErrors()) {
                quiz = response.data?.getQuiz!!
            }
            return quiz
        }

    }

}