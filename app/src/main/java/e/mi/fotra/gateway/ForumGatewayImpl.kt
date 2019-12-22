package e.mi.fotra.gateway

import e.mi.fotra.UniversalCallback
import e.mi.fotra.api.ForumService
import e.mi.fotra.dataclasses.forum.Question
import e.mi.fotra.dataclasses.forum.QuestionRequest
import e.mi.fotra.viewmodel.ForumViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForumGatewayImpl(val forumService: ForumService) : ForumGateway {

    override fun getAllPost(postCallback: ForumViewModel.PostCallback) {
        forumService.getAllPosts().enqueue(object : Callback<List<Question>> {
            override fun onFailure(call: Call<List<Question>>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<List<Question>>,
                response: Response<List<Question>>
            ) {
                val body = response.body()

                if (response.isSuccessful && body != null) {
                    postCallback.onSuccess(body)
                }
            }

        })
    }

    override fun addPost(
        questionTitle: String,
        questionBody: String,
        callback: UniversalCallback<Unit>
    ) {
        forumService.addPost(QuestionRequest(questionTitle, questionBody, "goody", "en"))
            .enqueue(object : Callback<Unit> {
                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    callback.onFailure(t)
                }

                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if (response.isSuccessful) {
                        callback.onSuccess(Unit)
                    } else {
                        callback.onFailure()
                    }
                }
            })
    }

}