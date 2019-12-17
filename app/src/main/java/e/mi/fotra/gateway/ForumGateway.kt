package e.mi.fotra.gateway

import e.mi.fotra.dataclasses.forum.Question

interface ForumGateway {
    fun getAllPost(): List<Question>?
}