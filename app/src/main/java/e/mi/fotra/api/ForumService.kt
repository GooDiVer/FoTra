package e.mi.fotra.api

import e.mi.fotra.dataclasses.forum.Question
import e.mi.fotra.dataclasses.forum.QuestionResponce
import retrofit2.Call
import retrofit2.http.*

interface ForumService {

    @GET("/v/auth/forum/allposts")
    fun getAllPosts(): Call<List<Question>>

    @POST("/v/auth/forum/addpost")
    fun addPost(@Body body: QuestionResponce): Call<QuestionResponce>
}