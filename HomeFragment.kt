package Fun.bolly-flix.app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import Fun.bolly-flix.app.R
import Fun.bolly-flix.app.data.repository.ContentRepository
import Fun.bolly-flix.app.ui.adapters.HomeSectionAdapter
import Fun.bolly-flix.app.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val repo = ContentRepository()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvHome.layoutManager = LinearLayoutManager(requireContext())
        binding.swipeRefresh.setOnRefreshListener { loadData() }
        loadData()
    }

    private fun loadData() {
        lifecycleScope.launch {
            binding.swipeRefresh.isRefreshing = true
            val sections = repo.getHomeSections()
            binding.rvHome.adapter = HomeSectionAdapter(requireContext(), sections)
            binding.swipeRefresh.isRefreshing = false
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
