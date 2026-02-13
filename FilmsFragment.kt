package Fun.bolly-flix.app.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import Fun.bolly-flix.app.data.models.ContentType
import Fun.bolly-flix.app.data.repository.ContentRepository
import Fun.bolly-flix.app.databinding.FragmentHomeBinding
import Fun.bolly-flix.app.ui.DetailActivity
import Fun.bolly-flix.app.ui.adapters.ContentGridAdapter
import kotlinx.coroutines.launch

class FilmsFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val repo = ContentRepository()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvHome.layoutManager = GridLayoutManager(requireContext(), 3)
        loadData()
    }

    private fun loadData() {
        lifecycleScope.launch {
            binding.swipeRefresh.isRefreshing = true
            repo.getFilms().getOrNull()?.let { films ->
                binding.rvHome.adapter = ContentGridAdapter(films.map {
                    ContentGridAdapter.GridItem(it.id, it.title, it.thumbnail_url, it.banner_url, it.description, it.video_url, it.year, it.genre, ContentType.FILM)
                }) { item ->
                    startActivity(Intent(requireContext(), DetailActivity::class.java).apply {
                        putExtra("id", item.id); putExtra("type", item.type.name)
                        putExtra("title", item.title); putExtra("banner_url", item.bannerUrl)
                        putExtra("description", item.description); putExtra("video_url", item.videoUrl)
                        putExtra("year", item.year ?: 0); putExtra("genre", item.genre ?: "")
                    })
                }
            }
            binding.swipeRefresh.isRefreshing = false
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
