package e.mi.FoTra.navigationFragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import e.mi.FoTra.R
import e.mi.FoTra.ViewPagerAdapter
import e.mi.FoTra.translatorFragments.YandexFragment
import e.mi.fotra.ViewModel.MyViewModel
import kotlinx.android.synthetic.main.fragment_translator.*
import org.w3c.dom.Text

class TranslatorFragment : Fragment() {

    lateinit var model: MyViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_translator, container, false)

        model = activity?.let { ViewModelProviders.of(it).get(MyViewModel::class.java) }!!

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ViewPagerAdapter(childFragmentManager)

        translate_text.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                val tv_send = view.findViewById<View>(R.id.translate_text) as TextView

                model?.myMessage(tv_send.text.toString())

                val imm =
                    activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
            false
        }

        adapter.addFragment(YandexFragment(), "Yandex")
        adapter.addFragment(YandexFragment(), "Google")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)


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