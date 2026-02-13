package Fun.bolly-flix.app.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import Fun.bolly-flix.app.R
import Fun.bolly-flix.app.data.models.ContentSection
import Fun.bolly-flix.app.data.models.ContentItem
import Fun.bolly-flix.app.data.models.ContentType
import Fun.bolly-flix.app.databinding.ItemSectionRowBinding
import Fun.bolly-flix.app.ui.DetailActivity

class HomeSectionAdapter(
    private val context: Context,
    private val sections: List<ContentSection>
) : RecyclerView.Adapter<HomeSectionAdapter.VH>() {

    inner class VH(val binding: ItemSectionRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemSectionRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        val section = sections[position]
        holder.binding.tvSectionTitle.text = section.title
        holder.binding.rvItems.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        holder.binding.rvItems.adapter = ContentCardAdapter(section.items) { item ->
            context.startActivity(Intent(context, DetailActivity::class.java).apply {
                putExtra("id", item.id)
                putExtra("type", item.type.name)
                putExtra("title", item.title)
                putExtra("banner_url", item.thumbnailUrl)
            })
        }
    }

    override fun getItemCount() = sections.size
}
