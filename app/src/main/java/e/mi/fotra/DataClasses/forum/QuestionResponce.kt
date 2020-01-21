package e.mi.fotra.dataclasses.forum


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuestionResponce(
    @Json(name = "accepted")
    val accepted: Boolean,
    @Json(name = "body")
    val body: String,
    @Json(name = "comments")
    val comments: Int,
    @Json(name = "date")
    val date: String,
    @Json(name = "id_answer")
    val idAnswer: String,
    @Json(name = "likes")
    val likes: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "post_id_var")
    val postIdVar: String
)