package Fun.bolly-flix.app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import Fun.bolly-flix.app.data.models.LiveChannel
import Fun.bolly-flix.app.databinding.ItemContentCardBinding

class LiveChannelAdapter(
    private val channels: List<LiveChannel>,
    private val onClick: (LiveChannel) -> Unit
) : RecyclerView.Adapter<LiveChannelAdapter.VH>() {

    inner class VH(val binding: ItemContentCardBinding) : RecyclerView.ViewHolder(binding.root) {
        init { binding.root.setOnClickListener { onClick(channels[adapterPosition]) } }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemContentCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        val channel = channels[position]
        holder.binding.tvTitle.text = channel.name
        channel.logo_url?.let { Glide.with(holder.itemView).load(it).into(holder.binding.ivThumbnail) }
        holder.binding.root.layoutParams = holder.binding.root.layoutParams.apply {
            width = ViewGroup.LayoutParams.MATCH_PARENT
        }
    }

    override fun getItemCount() = channels.size
}
