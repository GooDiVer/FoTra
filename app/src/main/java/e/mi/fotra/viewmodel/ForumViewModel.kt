package e.mi.fotra.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import e.mi.fotra.api.ForumService
import e.mi.fotra.dataclasses.forum.Question
import e.mi.fotra.dataclasses.forum.QuestionList
import e.mi.fotra.gateway.ForumGateway
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class ForumViewModel(private val forumGateway: ForumGateway): ViewModel() {
    private val _listOfQuestions = MutableLiveData<List<Question>>()
    val listOfQuestions: LiveData<List<Question>>
        get() = _listOfQuestions

    fun onListOfQuestionLoaded() {
        forumGateway.getAllPost(object : PostCallback {
            override fun onSuccess(value: List<Question>) {
                _listOfQuestions.value = value
            }

            override fun onError(e: Exception) {
            }
        })
    }

    interface PostCallback {
        fun onSuccess(value: List<Question>)
        fun onError(e: Exception)
    }
}