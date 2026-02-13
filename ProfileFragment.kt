package Fun.bolly-flix.app.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import Fun.bolly-flix.app.ui.LoginActivity

class ProfileFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val layout = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(48, 48, 48, 48)
        }
        layout.addView(TextView(requireContext()).apply {
            text = "Mon Profil"
            textSize = 24f
            setTextColor(0xFFFFFFFF.toInt())
        })
        layout.addView(Button(requireContext()).apply {
            text = "Se déconnecter"
            setOnClickListener {
                requireContext().getSharedPreferences("auth", 0).edit().clear().apply()
                startActivity(Intent(requireContext(), LoginActivity::class.java))
                requireActivity().finish()
            }
        })
        return layout
    }
}
