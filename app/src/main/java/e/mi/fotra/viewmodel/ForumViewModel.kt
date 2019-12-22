package e.mi.fotra.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import e.mi.fotra.UniversalCallback
import e.mi.fotra.dataclasses.forum.Question
import e.mi.fotra.gateway.ForumGateway


class ForumViewModel(private val forumGateway: ForumGateway): ViewModel() {
    private val _listOfQuestions = MutableLiveData<List<Question>>()
    val listOfQuestions: LiveData<List<Question>>
        get() = _listOfQuestions

    init {
        loadPosts()
    }


    fun onPostAdded(questionTitle: String, questionBody: String) {
        forumGateway.addPost(questionTitle, questionBody, object : UniversalCallback<Unit>{
            override fun onSuccess(value: Unit) {
                loadPosts()
            }

            override fun onFailure(e: Throwable?) {
                e?.printStackTrace()
            }
        })
    }

    private fun loadPosts() {
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