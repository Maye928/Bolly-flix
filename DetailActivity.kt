package Fun.bolly-flix.app.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import Fun.bolly-flix.app.data.models.ContentType
import Fun.bolly-flix.app.data.repository.ContentRepository
import Fun.bolly-flix.app.databinding.ActivityDetailBinding
import Fun.bolly-flix.app.ui.adapters.EpisodeAdapter
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val repo = ContentRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra("id") ?: return finish()
        val type = intent.getStringExtra("type") ?: return finish()
        val title = intent.getStringExtra("title") ?: ""
        val description = intent.getStringExtra("description") ?: ""
        val banner = intent.getStringExtra("banner_url") ?: ""
        val videoUrl = intent.getStringExtra("video_url")
        val year = intent.getIntExtra("year", 0)
        val genre = intent.getStringExtra("genre") ?: ""

        binding.tvTitle.text = title
        binding.tvDescription.text = description
        binding.tvMeta.text = listOfNotNull(
            if (year > 0) year.toString() else null,
            genre.ifEmpty { null }
        ).joinToString(" • ")

        Glide.with(this).load(banner).into(binding.ivBanner)

        if (type == ContentType.FILM.name && videoUrl != null) {
            binding.btnPlay.visibility = View.VISIBLE
            binding.btnPlay.setOnClickListener {
                startActivity(Intent(this, PlayerActivity::class.java).putExtra("video_url", videoUrl))
            }
        } else {
            binding.btnPlay.visibility = View.GONE
        }

        // Load episodes for series types
        if (type in listOf(ContentType.SERIES.name, ContentType.WEB_SERIES.name, ContentType.CARTOON.name)) {
            binding.rvEpisodes.layoutManager = LinearLayoutManager(this)
            lifecycleScope.launch {
                val episodes = when (type) {
                    ContentType.SERIES.name -> repo.getEpisodes(id).getOrNull()?.map {
                        EpisodeAdapter.EpisodeItem(it.title, "S${it.season_number}E${it.episode_number}", it.video_url)
                    }
                    ContentType.WEB_SERIES.name -> repo.getWebSeriesEpisodes(id).getOrNull()?.map {
                        EpisodeAdapter.EpisodeItem(it.title, "S${it.season_number}E${it.episode_number}", it.video_url)
                    }
                    ContentType.CARTOON.name -> repo.getCartoonEpisodes(id).getOrNull()?.map {
                        EpisodeAdapter.EpisodeItem(it.title, "S${it.season_number}E${it.episode_number}", it.video_url)
                    }
                    else -> null
                }
                episodes?.let {
                    binding.rvEpisodes.adapter = EpisodeAdapter(it) { ep ->
                        startActivity(Intent(this@DetailActivity, PlayerActivity::class.java).putExtra("video_url", ep.videoUrl))
                    }
                }
            }
        }
    }
}
