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


class ForumViewModel(private val forumGateway: ForumGateway): ViewModel() {
    private val _listOfQuestions = MutableLiveData<List<Question>>()
    val listOfQuestions: LiveData<List<Question>>
        get() = _listOfQuestions

    fun onListOfQuestionLoaded() {
        _listOfQuestions.value = forumGateway.getAllPost()
    }
}