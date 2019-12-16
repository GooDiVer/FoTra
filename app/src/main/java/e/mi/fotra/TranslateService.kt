package e.mi.fotra

import e.mi.fotra.dataclasses.TranslateResponce


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TranslateService {
    @GET("/perm/an/api/translate/yandex/{lang}/{text}")
    fun getCurrentTranslate(@Path("lang") lang: String, @Path("text") text: String): Call<TranslateResponce>
}