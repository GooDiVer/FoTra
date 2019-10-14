package e.mi.fotra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_translator.*
import java.lang.IllegalArgumentException

class TranslatorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_translator, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        translator_text.text = arguments?.getString(ARG_TITLE) ?: throw IllegalArgumentException("You haven't passed a title")
    }

    companion object {
        private const val ARG_TITLE = "ARG_TITLE"
        fun getInstance(title : String) : TranslatorFragment =
            TranslatorFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, title)
                }
            }
    }
}