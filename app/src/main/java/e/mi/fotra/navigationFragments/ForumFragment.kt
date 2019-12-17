package e.mi.fotra.navigationFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import e.mi.fotra.R
import e.mi.fotra.adapter.QuestionAdapter
import e.mi.fotra.dataclasses.forum.Question
import e.mi.fotra.viewmodel.ForumViewModel
import kotlinx.android.synthetic.main.fragment_forum.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

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


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        forum_text.text = arguments?.getString(ARG)

        adapter = QuestionAdapter()

        //some context
        forumRecycler.layoutManager = LinearLayoutManager(activity).apply {
            orientation = LinearLayoutManager.VERTICAL
        }

        forumRecycler.adapter = adapter

        model.listOfQuestions.observe(viewLifecycleOwner, Observer<List<Question>> { questions -> adapter.submitList(questions)})

        model.onListOfQuestionLoaded()

    }

    companion object {
        private const val ARG = "ARG_TITLE"

        fun getInstance(title: String): ForumFragment =
            ForumFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG, title)
                }
            }
    }
}