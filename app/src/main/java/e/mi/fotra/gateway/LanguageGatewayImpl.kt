package e.mi.fotra.gateway

import e.mi.fotra.dataclasses.Language

class LanguageGatewayImpl : LanguageGateway {
    private val languages = arrayListOf(
        Language("ru", "Русский"),
        Language("en", "Английский"),
        Language("de", "Немецкий"),
        Language("fr", "Французский"),
        Language("zh", "Китайский"),
        Language("es", "Испанский"),
        Language("bn", "Бенгальский")
    )

    override fun getAvailableLanguages(): List<Language> {
        return languages
    }

    override fun getDefaultSourceLanguage(): Language {
        return languages[1]
    }

    override fun getDefaultTargetLanguage(): Language {
        return languages[0]
    }
}