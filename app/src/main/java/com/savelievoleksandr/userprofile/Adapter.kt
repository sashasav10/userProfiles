package com.savelievoleksandr.userprofile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.savelievoleksandr.userprofile.model.User

interface OnUserClick {
    fun onClick(userId: Int)
}

class Adapter(val userClick: OnUserClick) :
    ListAdapter<User, Adapter.ViewHolder>(object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = oldItem == newItem
    }) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.activity_user_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), holder.itemView.context)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.userName)
        private val lastSeenTextView: TextView = itemView.findViewById(R.id.lastSeen)
        private val userPhotoImageView: ImageView = itemView.findViewById(R.id.userPhoto)

        fun bind(item: User, context: Context) {
            nameTextView.text = item.name
            lastSeenTextView.text = item.lastSeen
            Glide.with(context)
                .load(item.photo)
                .error(R.drawable.user_emoji)
                .into(userPhotoImageView)
            itemView.setOnClickListener {
                userClick.onClick(item.id-1)
            }
        }
    }
}

