package org.poriad.hogwartsworld

import com.apollographql.apollo3.ApolloClient

class Apollo {

    val apolloClient = ApolloClient.Builder()
        .serverUrl("https://apollo-fullstack-tutorial.herokuapp.com/graphql")
        .build()

}