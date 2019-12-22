package e.mi.fotra.dataclasses.forum
import androidx.annotation.Nullable
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json

@JsonClass(generateAdapter = true)
data class Question(
    @Json(name = "body")
    val body: String,
    @Json(name = "comments")
    val comments: Int,
    @Json(name = "date")
    val date: String,
    @Json(name = "head")
    val head: String,
    @Json(name = "likes")
    val likes: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "open_close")
    val openClose: Boolean,
    @Json(name = "topic_var")
    val topicVar: String,
    @Json(name = "id_post")
    val idPost: String
)

@JsonClass(generateAdapter = true)
data class QuestionRequest(
    @Json(name = "head")
    val head: String,
    @Json(name = "body")
    val body: String,
    @Json(name = "username")
    val name: String,
    @Json(name = "langID")
    val idPost: String
)