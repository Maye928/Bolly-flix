package Fun.bolly-flix.app.data.models

data class Film(
    val id: String,
    val title: String,
    val description: String?,
    val thumbnail_url: String,
    val banner_url: String,
    val video_url: String,
    val genre: String,
    val year: Int?,
    val duration: String?,
    val is_featured: Boolean?,
    val is_popular: Boolean?,
    val is_new: Boolean?,
    val labels: List<String>?
)

data class Series(
    val id: String,
    val title: String,
    val description: String?,
    val thumbnail_url: String,
    val banner_url: String,
    val genre: String,
    val year: Int?,
    val is_featured: Boolean?,
    val is_popular: Boolean?,
    val is_new: Boolean?,
    val labels: List<String>?
)

data class Episode(
    val id: String,
    val series_id: String,
    val title: String,
    val episode_number: Int,
    val season_number: Int?,
    val video_url: String,
    val thumbnail_url: String?,
    val duration: String?,
    val labels: List<String>?
)

data class WebSeries(
    val id: String,
    val title: String,
    val description: String?,
    val thumbnail_url: String,
    val banner_url: String,
    val genre: String,
    val year: Int?,
    val is_featured: Boolean?,
    val is_popular: Boolean?,
    val is_new: Boolean?,
    val labels: List<String>?
)

data class WebSeriesEpisode(
    val id: String,
    val web_series_id: String,
    val title: String,
    val episode_number: Int,
    val season_number: Int?,
    val video_url: String,
    val thumbnail_url: String?,
    val duration: String?,
    val labels: List<String>?
)

data class Cartoon(
    val id: String,
    val title: String,
    val description: String?,
    val thumbnail_url: String,
    val banner_url: String,
    val genre: String,
    val year: Int?,
    val is_featured: Boolean?,
    val is_popular: Boolean?,
    val is_new: Boolean?,
    val labels: List<String>?
)

data class CartoonEpisode(
    val id: String,
    val cartoon_id: String,
    val title: String,
    val episode_number: Int,
    val season_number: Int?,
    val video_url: String,
    val thumbnail_url: String?,
    val duration: String?,
    val labels: List<String>?
)

data class LiveChannel(
    val id: String,
    val name: String,
    val logo_url: String?,
    val stream_url: String,
    val category: String?,
    val is_active: Boolean?,
    val channel_type: String
)

data class Category(
    val id: String,
    val name: String,
    val slug: String
)

data class AuthResponse(
    val access_token: String,
    val token_type: String,
    val user: AuthUser?
)

data class AuthUser(
    val id: String,
    val email: String?
)

data class AuthRequest(
    val email: String,
    val password: String
)

data class ContentSection(
    val title: String,
    val items: List<ContentItem>
)

data class ContentItem(
    val id: String,
    val title: String,
    val thumbnailUrl: String,
    val type: ContentType
)

enum class ContentType {
    FILM, SERIES, WEB_SERIES, CARTOON, LIVE
}
