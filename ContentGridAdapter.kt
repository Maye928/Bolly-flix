package Fun.bolly-flix.app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import Fun.bolly-flix.app.data.models.ContentType
import Fun.bolly-flix.app.databinding.ItemContentCardBinding

class ContentGridAdapter(
    private val items: List<GridItem>,
    private val onClick: (GridItem) -> Unit
) : RecyclerView.Adapter<ContentGridAdapter.VH>() {

    data class GridItem(
        val id: String,
        val title: String,
        val thumbnailUrl: String,
        val bannerUrl: String?,
        val description: String?,
        val videoUrl: String?,
        val year: Int?,
        val genre: String?,
        val type: ContentType
    )

    inner class VH(val binding: ItemContentCardBinding) : RecyclerView.ViewHolder(binding.root) {
        init { binding.root.setOnClickListener { onClick(items[adapterPosition]) } }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemContentCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        holder.binding.tvTitle.text = item.title
        Glide.with(holder.itemView).load(item.thumbnailUrl).into(holder.binding.ivThumbnail)
        holder.binding.root.layoutParams = holder.binding.root.layoutParams.apply {
            width = ViewGroup.LayoutParams.MATCH_PARENT
        }
    }

    override fun getItemCount() = items.size
}
