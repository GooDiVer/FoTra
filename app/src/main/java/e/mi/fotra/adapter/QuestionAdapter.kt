package e.mi.fotra.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import e.mi.fotra.R
import e.mi.fotra.dataclasses.Language
import e.mi.fotra.dataclasses.forum.Question
import e.mi.fotra.viewmodel.ForumViewModel
import kotlinx.android.synthetic.main.raw_question.view.*
import org.koin.core.Koin
import org.koin.core.KoinComponent
import org.koin.core.inject

class QuestionAdapter(
) : ListAdapter<Question, QuestionAdapter.QuestionViewHolder>(
    QuestionDiffUtilCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        return QuestionViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.raw_question,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class QuestionViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {

        private var questionTitle: TextView = view.questionTitleText
        private var rating: TextView = view.ratingText
        private var answers: TextView = view.answerText
        private var date: TextView = view.howLongText
        private var name: TextView = view.nameText

        fun bind(question: Question) {
            questionTitle.text = question.body
            rating.text = question.likes.toString()
            answers.text = question.comments.toString()
            date.text = question.date
            name.text = question.name
        }
    }

    object QuestionDiffUtilCallback : DiffUtil.ItemCallback<Question>() {
        override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean =
            oldItem.idPost == newItem.idPost

        override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean =
            oldItem == newItem
    }
}
