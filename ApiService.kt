package Fun.bolly-flix.app.data.api

import Fun.bolly-flix.app.data.models.*
import retrofit2.http.*

interface ApiService {

    // Auth
    @POST("auth/v1/token?grant_type=password")
    suspend fun login(@Body request: AuthRequest): AuthResponse

    @POST("auth/v1/signup")
    suspend fun signup(@Body request: AuthRequest): AuthResponse

    // Films
    @GET("rest/v1/films")
    suspend fun getFilms(
        @Query("select") select: String = "*",
        @Query("order") order: String = "created_at.desc"
    ): List<Film>

    // Series
    @GET("rest/v1/series")
    suspend fun getSeries(
        @Query("select") select: String = "*",
        @Query("order") order: String = "created_at.desc"
    ): List<Series>

    @GET("rest/v1/episodes")
    suspend fun getEpisodes(
        @Query("series_id") seriesId: String? = null,
        @Query("select") select: String = "*",
        @Query("order") order: String = "season_number,episode_number"
    ): List<Episode>

    // Web Series
    @GET("rest/v1/web_series")
    suspend fun getWebSeries(
        @Query("select") select: String = "*",
        @Query("order") order: String = "created_at.desc"
    ): List<WebSeries>

    @GET("rest/v1/web_series_episodes")
    suspend fun getWebSeriesEpisodes(
        @Query("web_series_id") webSeriesId: String? = null,
        @Query("select") select: String = "*",
        @Query("order") order: String = "season_number,episode_number"
    ): List<WebSeriesEpisode>

    // Cartoons
    @GET("rest/v1/cartoons")
    suspend fun getCartoons(
        @Query("select") select: String = "*",
        @Query("order") order: String = "created_at.desc"
    ): List<Cartoon>

    @GET("rest/v1/cartoon_episodes")
    suspend fun getCartoonEpisodes(
        @Query("cartoon_id") cartoonId: String? = null,
        @Query("select") select: String = "*",
        @Query("order") order: String = "season_number,episode_number"
    ): List<CartoonEpisode>

    // Live
    @GET("rest/v1/live_channels")
    suspend fun getLiveChannels(
        @Query("select") select: String = "*",
        @Query("is_active") isActive: String = "eq.true"
    ): List<LiveChannel>

    // Categories
    @GET("rest/v1/categories")
    suspend fun getCategories(): List<Category>
}
