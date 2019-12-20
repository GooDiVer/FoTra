package e.mi.fotra.navigationFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import e.mi.fotra.R
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_profile, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        profile_text.text = arguments?.getString(ARG)
    }
    companion object {
        private const val ARG = "ARG_TITLE"

        fun getInstance(title: String): ProfileFragment =
        ProfileFragment().apply {
            arguments = Bundle().apply {
                putString(ARG, title)
            }
        }
    }


}