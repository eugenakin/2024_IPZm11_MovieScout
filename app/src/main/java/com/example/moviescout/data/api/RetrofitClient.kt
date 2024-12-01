package com.example.moviescout.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.themoviedb.org/3/"
const val API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjZjdlOTU3YjQzNjU2OGVjZmY3ZjY3MjZjMjZjNDU1MSIsIm5iZiI6MTczMjA1Njk5NC4yNDk5Mywic3ViIjoiNTk0MmZjYzdjM2EzNjg2YzBkMDEzNTJhIiwic2NvcGVzIjpbImFwaV9yZWFkIl0sInZlcnNpb24iOjF9.7ByvjA7MVAIyycSxKmeuQTwM0Oi19rFsvcKGdx1vKzw"

object RetrofitInstance {
    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $API_KEY")
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    val api: MoviesApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesApiService::class.java)
    }
}