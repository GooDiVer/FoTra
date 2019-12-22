package e.mi.fotra.navigationFragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import e.mi.fotra.R
import e.mi.fotra.viewmodel.ChooseLanguageViewModel
import e.mi.fotra.dataclasses.Language
import kotlinx.android.synthetic.main.activity_languages.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChooseLanguageActivity : AppCompatActivity() {

    private val model: ChooseLanguageViewModel by viewModel()

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_languages)

        var adapter = LanguageAdapter(
            object : LanguageAdapter.Listener {
                override fun onLanguageClick(language: Language) {
                    onLanguageItemClick(language)
                }
            })

        recycler.layoutManager = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        recycler.adapter = adapter

        model.languages.observe(
            this,
            Observer<List<Language>> { languages -> adapter.submitList(languages) }
        )
    }

    private fun onLanguageItemClick(language: Language) {
        val returnIntent = Intent()
        returnIntent.putExtra(ARG_RESULT, language)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }

    private class LanguageAdapter(
        private val listener: Listener
    ) : ListAdapter<Language, LanguageAdapter.LanguageViewHolder>(LanguageDiffUtilCallback) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
            return LanguageViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.view_language,
                    parent,
                    false
                ), listener
            )
        }

        override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
            holder.bind(getItem(position))
        }

        interface Listener {
            fun onLanguageClick(language: Language)
        }

        private class LanguageViewHolder(
            view: View,
            private val clickListener: Listener
        ) : RecyclerView.ViewHolder(view) {

            private val languageText: TextView = view as TextView

            fun bind(language: Language) {
                languageText.text = language.name
                languageText.setOnClickListener { clickListener.onLanguageClick(language) }
            }
        }

        object LanguageDiffUtilCallback : DiffUtil.ItemCallback<Language>() {
            override fun areItemsTheSame(oldItem: Language, newItem: Language): Boolean =
                oldItem.code == newItem.code

            override fun areContentsTheSame(oldItem: Language, newItem: Language): Boolean =
                oldItem == newItem
        }
    }

    companion object {
        const val ARG_RESULT = "result"
    }
}