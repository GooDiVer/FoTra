package e.mi.FoTra.navigationFragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import e.mi.FoTra.R
import e.mi.FoTra.ViewPagerAdapter
import e.mi.FoTra.translatorFragments.YandexFragment
import kotlinx.android.synthetic.main.fragment_translator.*

class TranslatorFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_translator, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(YandexFragment(),"Yandex")
        adapter.addFragment(YandexFragment(),"Google")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        translate_text.setOnKeyListener { v,keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
            false
        }
    }

    companion object {
        private const val ARG_TITLE = "ARG_TITLE"

        fun getInstance(title: String): TranslatorFragment =
            TranslatorFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, title)
                }
            }
    }
}