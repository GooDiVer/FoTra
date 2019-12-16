package e.mi.fotra.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import e.mi.fotra.TranslateService
import e.mi.fotra.dataclasses.Language
import e.mi.fotra.dataclasses.TranslateResponce
import e.mi.fotra.gateway.LanguageGateway
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.sql.Struct

class TranslateViewModel(
    private val translateService: TranslateService,
    private val languageGateway: LanguageGateway
) : ViewModel() {


    //field for text to translate
    private val _originalText = MutableLiveData<String>()
    val originalText: LiveData<String>
        get() =  _originalText

    //field for translated text
    private val _translatedText = MutableLiveData<String>()
    val translatedText: LiveData<String>
        get() = _translatedText

    //field for left language-frame
    private val _sourceLanguage = MutableLiveData<Language>()
    val sourceLanguage: LiveData<Language>
        get() = _sourceLanguage

    //field for right language-frame
    private val _targetLanguage = MutableLiveData<Language>()
    val targetLanguage: LiveData<Language>
        get() = _targetLanguage

    init {
        _sourceLanguage.value = languageGateway.getDefaultSourceLanguage()
        _targetLanguage.value = languageGateway.getDefaultTargetLanguage()
    }

    /* Get input, translate, put in  */
    fun onSearchTextInputDone(text: String) {
        val call = translateService.getCurrentTranslate(
            "${_sourceLanguage.value!!.code}-${_targetLanguage.value!!.code}",
            text
        )

        _originalText.value = text;

        call.enqueue(object : Callback<TranslateResponce> {
            override fun onResponse(
                call: Call<TranslateResponce>,
                response: Response<TranslateResponce>
            ) {
                val translateResponce = response.body()!!
                setTranslatedText(translateResponce.text[0])
            }

            override fun onFailure(call: Call<TranslateResponce>, t: Throwable) {
                println(t)
            }
        })
    }

    fun setTranslatedText(txt: String) {
        _translatedText.value = txt

    }

    fun onSourceLanguageChange(language: Language) {
        _sourceLanguage.value = language
    }

    fun onTargetLanguageChange(language: Language) {
        _targetLanguage.value = language
    }
}