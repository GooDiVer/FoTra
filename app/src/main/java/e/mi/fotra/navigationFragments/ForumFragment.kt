package e.mi.fotra.navigationFragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import e.mi.fotra.MainRouter
import e.mi.fotra.MainRouterPhone
import e.mi.fotra.R
import e.mi.fotra.adapter.QuestionAdapter
import e.mi.fotra.api.ForumService
import e.mi.fotra.dataclasses.forum.Question
import e.mi.fotra.dataclasses.forum.QuestionResponce
import e.mi.fotra.viewmodel.ForumViewModel
import kotlinx.android.synthetic.main.fragment_forum.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.*

class ForumFragment: Fragment() {
    private lateinit var adapter: QuestionAdapter
    private val model: ForumViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_forum, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        forum_text.text = arguments?.getString(ARG)

        adapter = QuestionAdapter()

        //some context
        forumRecycler.layoutManager = LinearLayoutManager(activity).apply {
            orientation = LinearLayoutManager.VERTICAL
        }

        forumRecycler.adapter = adapter

        model.listOfQuestions.observe(viewLifecycleOwner, Observer<List<Question>> { questions -> adapter.submitList(questions)})

        model.onListOfQuestionLoaded()

        addPostButton.setOnClickListener {
            var intent = Intent(requireActivity(), AddPostActivity::class.java)
            startActivityForResult(intent, POSTED)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if(resultCode == Activity.RESULT_OK) {
            var questionBodyString = data?.getStringExtra(AddPostActivity.QUESTION_BODY)?.trim()
            var questionTitleString = data?.getStringExtra(AddPostActivity.QUESTION_TITLE)?.trim()
            sendPost(questionTitleString!!, questionBodyString!!)
            println()
        }
    }

    private fun sendPost(questionBody: String, questionTitle: String) {
        var question = QuestionResponce(body = questionBody,head = questionTitle,name = "goody",idPost = "en")
        model.onPostAdded(question)
    }

    companion object {
        private const val ARG = "ARG_TITLE"
        private const val POSTED = 1

        fun getInstance(title: String): ForumFragment =
            ForumFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG, title)
                }
            }
    }
}