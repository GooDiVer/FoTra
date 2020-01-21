package e.mi.fotra.navigationFragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import e.mi.fotra.R
import e.mi.fotra.dataclasses.forum.Question
import kotlinx.android.synthetic.main.activity_questionpage.*

class QuestionPageActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionpage)
        var question = intent.getParcelableExtra<Question>(EXTRA_QUESTION)
        titleQuestionText.text = question.head
        bodyQuestionText.text = question.body
    }

    companion object {
        private const val EXTRA_QUESTION = "EXTRA_QUESTION"
        fun getIntent(context: Context, question: Question): Intent =
            Intent(context, QuestionPageActivity::class.java)
                .putExtra(EXTRA_QUESTION, question)
    }
}