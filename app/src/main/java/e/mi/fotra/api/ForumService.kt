package e.mi.fotra.api

import e.mi.fotra.dataclasses.forum.Question
import e.mi.fotra.dataclasses.forum.QuestionList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ForumService {

    @Headers("Authorization: Bearer_" +
            "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJnb29keSIsInJvbGVzIjoiVVNFUiIsImlhdCI6MTU3NjYxNzE1MiwiZXhwIjoxNTc5MjA5MTUyfQ.hmBH1uKP6Rt02vGFpZOcURxYGwB7z2ibjHcSzw1gMk0")
    @GET("/v/auth/forum/allposts")
    fun getAllPosts(): Call<List<Question>>
}