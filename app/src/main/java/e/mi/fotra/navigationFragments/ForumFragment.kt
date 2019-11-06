package e.mi.FoTra.navigationFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import e.mi.FoTra.R
import kotlinx.android.synthetic.main.fragment_forum.*

class ForumFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_forum,container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        forum_text.text = arguments?.getString(ARG)

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