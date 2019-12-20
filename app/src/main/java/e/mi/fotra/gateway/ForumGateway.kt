package e.mi.fotra.gateway

import e.mi.fotra.dataclasses.forum.Question
import e.mi.fotra.viewmodel.ForumViewModel

interface ForumGateway {
    fun getAllPost(postCallback: ForumViewModel.PostCallback)
    //В будущем здесь будет происходить заполнение модели. В Impl имеется необходимая коллекция "questions"
//    fun addPost()
}