package e.mi.fotra.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import e.mi.fotra.dataclasses.Language
import e.mi.fotra.gateway.LanguageGateway

class ChooseLanguageViewModel(
    languageGateway: LanguageGateway
) : ViewModel() {
    private val _languages = MutableLiveData<List<Language>>()
    val languages: LiveData<List<Language>>
        get() = _languages

    init {
        _languages.value = languageGateway.getAvailableLanguages()
    }


}
