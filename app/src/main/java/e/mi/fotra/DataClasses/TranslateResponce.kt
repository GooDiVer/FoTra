package e.mi.fotra.dataclasses


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TranslateResponce(
    @Json(name = "code")
    val code: Int,
    @Json(name = "lang")
    val lang: String,
    @Json(name = "text")
    val text: List<String>
)