package e.mi.fotra.koin

import e.mi.fotra.api.ForumService
import e.mi.fotra.gateway.ForumGateway
import e.mi.fotra.gateway.ForumGatewayImpl
import org.koin.dsl.module

object ForumModule {
    val module = module {
        single<ForumGateway> {ForumGatewayImpl(forumService = get())}
    }
}