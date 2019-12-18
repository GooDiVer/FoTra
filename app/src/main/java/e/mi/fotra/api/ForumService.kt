package e.mi.fotra.api

import e.mi.fotra.dataclasses.forum.Question
import e.mi.fotra.dataclasses.forum.QuestionList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ForumService {

//    @Headers("Authorization: Bearer_" +
//            "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJnb29keSIsInJvbGVzIjoiVVNFUiIsImlhdCI6MTU3NjY5NDAxNywiZXhwIjoxNTc5Mjg2MDE3fQ.q5mCmgHF7Vw64uBSsd-kaQGcMXEIuqe7FPQ2SjTdtyo")
    @GET("/v/auth/forum/allposts")
    fun getAllPosts(): Call<List<Question>>
}