package Fun.bolly-flix.app.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import Fun.bolly-flix.app.data.repository.ContentRepository
import Fun.bolly-flix.app.databinding.FragmentHomeBinding
import Fun.bolly-flix.app.ui.PlayerActivity
import Fun.bolly-flix.app.ui.adapters.LiveChannelAdapter
import kotlinx.coroutines.launch

class LiveFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val repo = ContentRepository()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvHome.layoutManager = GridLayoutManager(requireContext(), 2)
        loadData()
    }

    private fun loadData() {
        lifecycleScope.launch {
            binding.swipeRefresh.isRefreshing = true
            repo.getLiveChannels().getOrNull()?.let { channels ->
                binding.rvHome.adapter = LiveChannelAdapter(channels) { channel ->
                    startActivity(Intent(requireContext(), PlayerActivity::class.java).putExtra("video_url", channel.stream_url))
                }
            }
            binding.swipeRefresh.isRefreshing = false
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
