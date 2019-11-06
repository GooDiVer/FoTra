package e.mi.FoTra.translatorFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import e.mi.FoTra.R

class YandexFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_yandex, container, false)
        return view
    }

    companion object {
        private const val ARG = "ARG_YANDEX"

        fun getInstance(text: String): YandexFragment =
            YandexFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG, text)
                }
        }
    }
}