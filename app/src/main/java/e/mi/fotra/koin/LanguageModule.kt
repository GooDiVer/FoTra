package e.mi.fotra.koin

import e.mi.fotra.gateway.LanguageGateway
import e.mi.fotra.gateway.LanguageGatewayImpl
import org.koin.dsl.module

object LanguageModule {

    val module = module {
        factory<LanguageGateway> { LanguageGatewayImpl() }
    }
}