package Fun.bolly-flix.app.data.repository

import Fun.bolly-flix.app.data.api.ApiClient
import Fun.bolly-flix.app.data.models.*

class ContentRepository {
    private val api = ApiClient.api

    suspend fun login(email: String, password: String): Result<AuthResponse> = runCatching {
        api.login(AuthRequest(email, password))
    }

    suspend fun signup(email: String, password: String): Result<AuthResponse> = runCatching {
        api.signup(AuthRequest(email, password))
    }

    suspend fun getFilms(): Result<List<Film>> = runCatching { api.getFilms() }
    suspend fun getSeries(): Result<List<Series>> = runCatching { api.getSeries() }
    suspend fun getEpisodes(seriesId: String): Result<List<Episode>> = runCatching {
        api.getEpisodes(seriesId = "eq.$seriesId")
    }
    suspend fun getWebSeries(): Result<List<WebSeries>> = runCatching { api.getWebSeries() }
    suspend fun getWebSeriesEpisodes(id: String): Result<List<WebSeriesEpisode>> = runCatching {
        api.getWebSeriesEpisodes(webSeriesId = "eq.$id")
    }
    suspend fun getCartoons(): Result<List<Cartoon>> = runCatching { api.getCartoons() }
    suspend fun getCartoonEpisodes(id: String): Result<List<CartoonEpisode>> = runCatching {
        api.getCartoonEpisodes(cartoonId = "eq.$id")
    }
    suspend fun getLiveChannels(): Result<List<LiveChannel>> = runCatching { api.getLiveChannels() }

    suspend fun getHomeSections(): List<ContentSection> {
        val sections = mutableListOf<ContentSection>()
        
        getFilms().getOrNull()?.take(20)?.let { films ->
            if (films.isNotEmpty()) {
                sections.add(ContentSection("Films populaires", films.map {
                    ContentItem(it.id, it.title, it.thumbnail_url, ContentType.FILM)
                }))
            }
        }
        getSeries().getOrNull()?.take(20)?.let { list ->
            if (list.isNotEmpty()) {
                sections.add(ContentSection("Séries", list.map {
                    ContentItem(it.id, it.title, it.thumbnail_url, ContentType.SERIES)
                }))
            }
        }
        getWebSeries().getOrNull()?.take(20)?.let { list ->
            if (list.isNotEmpty()) {
                sections.add(ContentSection("Web-séries", list.map {
                    ContentItem(it.id, it.title, it.thumbnail_url, ContentType.WEB_SERIES)
                }))
            }
        }
        getCartoons().getOrNull()?.take(20)?.let { list ->
            if (list.isNotEmpty()) {
                sections.add(ContentSection("Dessins animés", list.map {
                    ContentItem(it.id, it.title, it.thumbnail_url, ContentType.CARTOON)
                }))
            }
        }
        return sections
    }
}
