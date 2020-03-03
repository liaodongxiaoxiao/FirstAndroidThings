package com.karl.demo.athings.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.karl.demo.athings.R
import com.karl.demo.athings.listener.OnRecyclerViewItemClickListener

class ActivitiesAdapter(val data: MutableList<String>) :
    RecyclerView.Adapter<ActivitiesAdapter.ActivityViewHolder>() {

    private var listener: OnRecyclerViewItemClickListener<String>? = null

    fun setOnRecyclerViewItemClickListener(ls: OnRecyclerViewItemClickListener<String>) {
        listener = ls
    }


    /**
     *
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        return ActivityViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_activities,
                parent,
                false
            )
        )
    }

    /**
     *
     */
    override fun getItemCount(): Int = data.size

    /**
     *
     */
    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        holder.tvName.text = data[position]
        holder.itemView.setOnClickListener { listener?.onItemClick(data[position]) }
    }


    inner class ActivityViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val tvName: TextView = viewItem.findViewById(R.id.tv_name)
    }
}