package edu.cnm.deepdive.skywatch.app.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import dagger.hilt.android.qualifiers.ActivityContext
import edu.cnm.deepdive.skywatch.app.databinding.ItemSearchResultBinding
import edu.cnm.deepdive.skywatch.core.model.SearchResultImage
import jakarta.inject.Inject

class SearchResultsAdapter @Inject constructor(@ActivityContext context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: MutableList<SearchResultImage> = mutableListOf()
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder =
        SearchItemHolder(ItemSearchResultBinding.inflate(inflater, parent, false))

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        (holder as SearchItemHolder).bind(position, items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    fun addAll(newItems: List<SearchResultImage>) {
        val oldSize = items.size
        items.addAll(newItems)
        notifyItemRangeInserted(oldSize, newItems.size)
    }

    private class SearchItemHolder(private val binding: ItemSearchResultBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int, item: SearchResultImage) {
            binding.description.text = item.description
            binding.thumbnail.load(item.thumbnail)
        }

    }

}