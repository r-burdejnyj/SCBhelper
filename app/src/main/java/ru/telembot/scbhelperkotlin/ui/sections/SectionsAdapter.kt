package ru.telembot.scbhelperkotlin.ui.sections

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.telembot.scbhelperkotlin.R
import ru.telembot.scbhelperkotlin.`interface`.ItemClickListener
import ru.telembot.scbhelperkotlin.data.entity.Section
import ru.telembot.scbhelperkotlin.databinding.ItemSectionsBinding

class SectionsAdapter : RecyclerView.Adapter<SectionViewHolder>() {
    private val items = ArrayList<Section>()
    private lateinit var mCallback: ItemClickListener<Section>

    fun setItems(items: ArrayList<Section>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : SectionViewHolder {
        val binding: ItemSectionsBinding =
            ItemSectionsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        return SectionViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        holder.bind(items[position])

        holder.itemView.setOnClickListener {
            mCallback.onItemClick(holder.itemView, position)
        }
    }

    fun attachCallback(callback: ItemClickListener<Section>) {
        this.mCallback = callback
    }
}

class SectionViewHolder(private val itemBinding: ItemSectionsBinding) : RecyclerView.ViewHolder(itemBinding.root) {
    private lateinit var section: Section

    fun bind(item: Section) {
        this.section = item
        itemBinding.txtTitleMain.text = item.title
        itemBinding.txtDescMain.text = item.subtitle
        Glide.with(itemBinding.root)
            .load(item.icon)
            .into(itemBinding.imgIconMain)
        itemBinding.imgArrow.setImageResource(R.drawable.ic_chevron)
    }
}




