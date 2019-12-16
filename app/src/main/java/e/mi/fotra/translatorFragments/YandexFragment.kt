package e.mi.fotra.translatorFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import e.mi.fotra.R
import e.mi.fotra.ViewModel.TranslateViewModel
import kotlinx.android.synthetic.main.fragment_yandex.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class YandexFragment: Fragment() {
    private lateinit var vMessage: TextView
    private val viewModel: TranslateViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_yandex, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vMessage = translated_yandex_text

        //read about lifeCycle
        viewModel.translatedText.observe(viewLifecycleOwner, Observer<String> { o -> vMessage.text = o })
    }

    companion object {

        fun getInstance() = YandexFragment()
    }
}