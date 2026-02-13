package Fun.bolly-flix.app.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import Fun.bolly-flix.app.R

class EpisodeAdapter(
    private val episodes: List<EpisodeItem>,
    private val onClick: (EpisodeItem) -> Unit
) : RecyclerView.Adapter<EpisodeAdapter.VH>() {

    data class EpisodeItem(val title: String, val meta: String, val videoUrl: String)

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(android.R.id.text1)
        val tvMeta: TextView = view.findViewById(android.R.id.text2)
        init { view.setOnClickListener { onClick(episodes[adapterPosition]) } }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val card = MaterialCardView(parent.context).apply {
            layoutParams = ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT).apply {
                bottomMargin = 8
            }
            setCardBackgroundColor(0xFF2A2A2A.toInt())
            radius = 8f
            val ll = android.widget.LinearLayout(context).apply {
                orientation = android.widget.LinearLayout.VERTICAL
                setPadding(32, 24, 32, 24)
                addView(TextView(context).apply { id = android.R.id.text1; textSize = 16f; setTextColor(0xFFFFFFFF.toInt()) })
                addView(TextView(context).apply { id = android.R.id.text2; textSize = 12f; setTextColor(0xFFB3B3B3.toInt()) })
            }
            addView(ll)
        }
        return VH(card)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.tvTitle.text = episodes[position].title
        holder.tvMeta.text = episodes[position].meta
    }

    override fun getItemCount() = episodes.size
}
