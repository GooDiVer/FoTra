package e.mi.fotra.navigationFragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import e.mi.fotra.R
import kotlinx.android.synthetic.main.activity_add_post.*
import kotlinx.android.synthetic.main.fragment_forum.*

class AddPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_post)

        finishPostButton.setOnClickListener {
            onPostAdded()
        }
    }

    private fun onPostAdded() {
        var returnIntent = Intent()
        returnIntent.putExtra(QUESTION_BODY, questionBodyEdit.text.toString())
        returnIntent.putExtra(QUESTION_TITLE, questionTitleEdit.text.toString())
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }

    companion object {
        const val ARG_RESULT = "result"
        const val QUESTION_BODY = "QUESTION_BODY"
        const val QUESTION_TITLE = "QUESTION_TITLE"

    }

}