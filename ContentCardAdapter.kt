package Fun.bolly-flix.app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import Fun.bolly-flix.app.data.models.ContentItem
import Fun.bolly-flix.app.databinding.ItemContentCardBinding

class ContentCardAdapter(
    private val items: List<ContentItem>,
    private val onClick: (ContentItem) -> Unit
) : RecyclerView.Adapter<ContentCardAdapter.VH>() {

    inner class VH(val binding: ItemContentCardBinding) : RecyclerView.ViewHolder(binding.root) {
        init { binding.root.setOnClickListener { onClick(items[adapterPosition]) } }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemContentCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        holder.binding.tvTitle.text = item.title
        Glide.with(holder.itemView).load(item.thumbnailUrl).into(holder.binding.ivThumbnail)
    }

    override fun getItemCount() = items.size
}
