package Fun.bolly-flix.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import Fun.bolly-flix.app.R
import Fun.bolly-flix.app.databinding.ActivityMainBinding
import Fun.bolly-flix.app.ui.fragments.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Restore auth token
        val token = getSharedPreferences("auth", MODE_PRIVATE).getString("token", null)
        Fun.bolly-flix.app.data.api.ApiClient.setAuthToken(token)

        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }

        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> loadFragment(HomeFragment())
                R.id.nav_films -> loadFragment(FilmsFragment())
                R.id.nav_series -> loadFragment(SeriesFragment())
                R.id.nav_live -> loadFragment(LiveFragment())
                R.id.nav_profile -> loadFragment(ProfileFragment())
            }
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
