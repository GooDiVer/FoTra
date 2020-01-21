package e.mi.fotra.navigationFragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import e.mi.fotra.R
import e.mi.fotra.adapter.QuestionAdapter
import e.mi.fotra.dataclasses.forum.Question
import e.mi.fotra.viewmodel.ForumViewModel
import kotlinx.android.synthetic.main.fragment_forum.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.lang.IllegalArgumentException

class ForumFragment : Fragment() {

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

        val adapter = QuestionAdapter(listener = object :
            QuestionAdapter.Listener {

            override fun onQuestionClick(question: Question) {
                startActivity(QuestionPageActivity.getIntent(requireContext(), question))
            }
        })

        //some context
        forumRecycler.layoutManager = LinearLayoutManager(activity).apply {
            orientation = LinearLayoutManager.VERTICAL
        }

        forumRecycler.adapter = adapter

        model.listOfQuestions.observe(
            viewLifecycleOwner,
            Observer<List<Question>> { questions -> adapter.submitList(questions) })

        addPostButton.setOnClickListener {
            val intent = Intent(requireActivity(), AddPostActivity::class.java)
            startActivityForResult(intent, ADD_POST_CODE)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            ADD_POST_CODE ->
                if (resultCode == Activity.RESULT_OK) {
                    val questionTitleString =
                        data?.getStringExtra(AddPostActivity.QUESTION_TITLE)?.trim()
                            ?: throw IllegalArgumentException("AddPostActivty must return QUESTION_TITLE on success")
                    val questionBodyString =
                        data.getStringExtra(AddPostActivity.QUESTION_BODY)?.trim()
                            ?: throw IllegalArgumentException("AddPostActivty must return QUESTION_BODY on success")
                    sendPost(questionTitleString, questionBodyString)
                }
        }
    }

    private fun sendPost(questionBody: String, questionTitle: String) {
        model.onPostAdded(questionTitle, questionBody)
    }


    companion object {
        private const val ARG = "ARG_TITLE"
        private const val ADD_POST_CODE = 1

        fun getInstance(title: String): ForumFragment =
            ForumFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG, title)
                }
            }
    }
}