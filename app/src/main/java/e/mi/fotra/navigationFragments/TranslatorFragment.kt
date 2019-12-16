package e.mi.fotra.navigationFragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import e.mi.fotra.ChooseLanguageActivity
import e.mi.fotra.R
import e.mi.fotra.ViewModel.TranslateViewModel
import e.mi.fotra.ViewPagerAdapter
import e.mi.fotra.dataclasses.Language
import e.mi.fotra.translatorFragments.YandexFragment
import kotlinx.android.synthetic.main.fragment_translator.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TranslatorFragment : Fragment() {

    //We already have ready implementation of interface from somewhere (KOIN)
    private val model: TranslateViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_translator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val adapter = ViewPagerAdapter(childFragmentManager)

        original_text.setOnEditorActionListener { _, keyCode, event ->
            if (keyCode == EditorInfo.IME_ACTION_DONE) {
                model.onSearchTextInputDone(original_text.text.toString())

                val imm =
                    activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)

                true

            } else {
                false
            }
        }

        adapter.addFragment(YandexFragment.getInstance(), "Yandex")
        adapter.addFragment(YandexFragment.getInstance(), "Google")

        model.sourceLanguage.observe(
            viewLifecycleOwner,
            Observer<Language> { language -> translate_from_button.text = language.name }
        )

        model.targetLanguage.observe(
            viewLifecycleOwner,
            Observer<Language> { language -> translate_to_button.text = language.name }
        )

        translate_from_button.setOnClickListener {
            openFromList()
        }

        translate_to_button.setOnClickListener {
            openToList()
        }

        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }

    private fun openFromList() {
        val resultIntent = Intent(context, ChooseLanguageActivity::class.java)
        startActivityForResult(resultIntent, REQUEST_CODE_FROM)
    }

    private fun openToList() {
        val resultIntent = Intent(context, ChooseLanguageActivity::class.java)
        startActivityForResult(resultIntent, REQUEST_CODE_TO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            val language: Language? = data?.getParcelableExtra(ChooseLanguageActivity.ARG_RESULT)

            language?.let {
                when (requestCode) {
                    REQUEST_CODE_FROM -> model.onSourceLanguageChange(it)
                    REQUEST_CODE_TO -> model.onTargetLanguageChange(it)
                }
            }
        }
    }

    companion object {

        private const val ARG_TITLE = "ARG_TITLE"
        private const val REQUEST_CODE_FROM = 998
        private const val REQUEST_CODE_TO = 999

        fun getInstance(title: String): TranslatorFragment =
            TranslatorFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, title)
                }
            }
    }
}