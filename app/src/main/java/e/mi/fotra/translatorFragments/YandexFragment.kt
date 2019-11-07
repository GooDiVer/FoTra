package e.mi.FoTra.translatorFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import e.mi.FoTra.R
import e.mi.fotra.ViewModel.MyViewModel
import kotlinx.android.synthetic.main.fragment_yandex.*

class YandexFragment: Fragment() {
    internal lateinit var tv_msg: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_yandex, container, false)

        val model = activity?.let { ViewModelProviders.of(it).get(MyViewModel::class.java) }

        //doesn't it work just calling "translated_yandex_text as TextView"
        tv_msg = view.findViewById<View>(R.id.translated_yandex_text) as TextView

        model?.message?.observe(this, object : Observer<Any> {
            override fun onChanged(o: Any?) {
                tv_msg.text = o?.toString()
            }
        })


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