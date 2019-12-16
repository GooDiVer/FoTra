package e.mi.fotra.gateway

import e.mi.fotra.dataclasses.Language

interface LanguageGateway {
    fun getAvailableLanguages(): List<Language>

    fun getDefaultSourceLanguage(): Language

    fun getDefaultTargetLanguage(): Language
}