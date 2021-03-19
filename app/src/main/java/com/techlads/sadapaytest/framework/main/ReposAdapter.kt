package com.techlads.sadapaytest.framework.main

import android.util.Log
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
    private var values: MutableList<LatestRepositoryResponse.Repo>,
    inline val getFirstVisiblePostion : () -> Int,
    inline val getLastVisiblePostion : () -> Int
) : RecyclerView.Adapter<ReposAdapter.ViewHolder>() {

    var showingDetailsFor = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_list_photo))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.update(values[position])
    }

    override fun getItemCount() = values.size

    fun update(vals: List<LatestRepositoryResponse.Repo>) {
        val lastSize =  this.values.size
        this.values.addAll(vals)
        notifyItemRangeChanged(lastSize, this.values.size)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
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
}