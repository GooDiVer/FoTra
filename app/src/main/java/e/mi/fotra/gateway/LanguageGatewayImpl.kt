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
        Language("tt", "Татарский"),
        Language("pt", "Португальский"),
        Language("hr", "Хорватский"),
        Language("hy", "Армянский"),
        Language("uk", "Украинский"),
        Language("hi", "Хинди"),
        Language("lo", "Лаосский"),
        Language("lt", "Литовский"),
        Language("id", "Индонезийский"),
        Language("mi", "Маори"),
        Language("ur", "Урду"),
        Language("mk", "Македонский"),
        Language("pap", "Папьяменто"),
        Language("ml", "Малаялам"),
        Language("eo", "Эсперанто"),
        Language("is", "Исландский"),
        Language("af", "Африканас"),
        Language("am", "Амхарский"),
        Language("my", "Бирманский"),
        Language("et", "Эстонский"),
        Language("eu", "Баскский")
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