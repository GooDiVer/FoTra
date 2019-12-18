package e.mi.fotra.gateway

import e.mi.fotra.api.ForumService
import e.mi.fotra.dataclasses.forum.Question
import e.mi.fotra.dataclasses.forum.QuestionList
import e.mi.fotra.viewmodel.ForumViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForumGatewayImpl(val forumService: ForumService) : ForumGateway {
    var questions: List<Question>? = null

    override fun getAllPost(postCallback: ForumViewModel.PostCallback) {

        val call = forumService.getAllPosts()
        println(call)
        call.enqueue( object : Callback<List<Question>>{
            override fun onFailure(call: Call<List<Question>>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<List<Question>>, response: Response<List<Question>>) {
                postCallback.onSuccess(response.body()!!)
            }

        })
    }
}