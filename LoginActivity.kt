package Fun.bolly-flix.app.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import Fun.bolly-flix.app.data.api.ApiClient
import Fun.bolly-flix.app.data.repository.ContentRepository
import Fun.bolly-flix.app.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val repo = ContentRepository()
    private var isSignup = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener { performAuth() }
        binding.btnSignup.setOnClickListener {
            isSignup = !isSignup
            binding.btnLogin.text = if (isSignup) "Créer un compte" else "Se connecter"
            binding.btnSignup.text = if (isSignup) "Déjà un compte ? Se connecter" else "Créer un compte"
        }
    }

    private fun performAuth() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch {
            val result = if (isSignup) repo.signup(email, password) else repo.login(email, password)
            result.onSuccess { auth ->
                ApiClient.setAuthToken(auth.access_token)
                getSharedPreferences("auth", MODE_PRIVATE).edit()
                    .putString("token", auth.access_token)
                    .apply()
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finish()
            }.onFailure {
                Toast.makeText(this@LoginActivity, "Erreur: ${it.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}
