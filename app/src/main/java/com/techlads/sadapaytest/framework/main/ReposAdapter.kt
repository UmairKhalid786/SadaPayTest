package com.techlads.sadapaytest.framework.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.techlads.sadapaytest.R
import com.techlads.sadapaytest.data.entities.LatestRepositoryResponse
import com.techlads.sadapaytest.utils.inflate
import com.techlads.sadapaytest.utils.loadImage
import com.techlads.sadapaytest.utils.visibility
import kotlinx.android.synthetic.main.item_list_photo.view.*


/**
 *
 * Kotlin
 *
 * @author Umair Khalid (umair.khalid786@outlook.com)
 * @package com.techlads.sadapaytest.framework.main
 */


class ReposAdapter(
        var values: MutableList<LatestRepositoryResponse.Repo>,
        inline val getFirstVisiblePostion: () -> Int,
        inline val getLastVisiblePostion: () -> Int
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val ITEM: Int       = 1
    private val LOADING: Int    = 0

    private val LOADING_ITEM = "loading_item"

    var showingDetailsFor = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder
        when(viewType) {
            LOADING ->
                viewHolder = getLoadingViewHolder(parent)
            else ->
                viewHolder = getItemViewHolder(parent)
        }

        return viewHolder
    }

    private fun getItemViewHolder(parent: ViewGroup): ItemViewHolder {
        return ItemViewHolder(parent.inflate(R.layout.item_list_photo))
    }

    private fun getLoadingViewHolder(parent: ViewGroup): LoadingViewHolder {
        return LoadingViewHolder(parent.inflate(R.layout.item_progress))
    }

    override fun onBindViewHolder(holderItem: RecyclerView.ViewHolder, position: Int) {
        if(holderItem is ItemViewHolder) {
            holderItem.update(values[position])
        }
    }

    fun addLoadingFooter() {
        values.add(LatestRepositoryResponse.Repo(LOADING_ITEM,"","","", -1, LatestRepositoryResponse.Repo.Owner("","")));
        notifyItemInserted(values.size - 1);
    }

    fun removeLoadingFooter() {
        val position: Int = values.size - 1
        if (position == -1)
            return

        val item = values.get(position)
        if (item != null) {
            values.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount() = values.size

    fun update(vals: List<LatestRepositoryResponse.Repo>) {
        val lastSize =  this.values.size
        this.values.addAll(vals)
        notifyItemRangeChanged(lastSize, this.values.size)
    }

    override fun getItemViewType(position: Int): Int {

        return if (position == values.size - 1 && values.get(position).name.equals(LOADING_ITEM)) LOADING else ITEM
    }

    /*************************************** ViewHolders ******************************************/
    /*____________________________________________________________________________________________*/

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            itemView.setOnClickListener {
                showingDetailsFor = adapterPosition
                //range from first to last element will update only ;)
                notifyItemRangeChanged(getFirstVisiblePostion(), getLastVisiblePostion())
            }
        }

        fun update(item: LatestRepositoryResponse.Repo) {
            itemView.avatarIv.loadImage(item.owner.avatar_url)
            itemView.nameTv.text = item.owner.login
            itemView.descTv.text = item.description
            itemView.repoTv.text = item.name
            itemView.lanTv.text = item.language
            itemView.starsTv.text = item.stargazers_count.toString()

            val showDetails = showingDetailsFor == adapterPosition

            itemView.tagsLl.visibility(showDetails)
            itemView.descTv.visibility(showDetails)
        }
    }

    inner class LoadingViewHolder(view: View) : RecyclerView.ViewHolder(view)
}