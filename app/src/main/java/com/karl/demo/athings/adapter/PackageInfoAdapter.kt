package com.autoai.vehicleutils.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.karl.demo.athings.R


import com.ldxx.tv.entity.PackageInfoBean

class PackageInfoAdapter(val data: MutableList<PackageInfoBean>) : RecyclerView.Adapter<PackageInfoAdapter.PackageInfoViewHolder>() {

    //private val data: MutableList<PackageInfoBean> = mutableListOf()
    private var listener: OnPackageItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PackageInfoViewHolder {
        return PackageInfoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_package_info, parent, false))
    }

    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: PackageInfoViewHolder, position: Int) {
        val entity = data[position]
        holder.apply {
            ivIcon.setImageDrawable(entity.icon)
            tvName.text = entity.appName
            tvPackageName.text = entity.packageName
            tvVersion.text = entity.versionName

            itemView.setOnClickListener {
                listener?.onItemClick(entity.packageName,entity.appName)
            }
        }
    }

    interface OnPackageItemClickListener {
        fun onItemClick(packageName: String?,appName:String?)
    }

    fun setOnPackageItemClickListener(listener: OnPackageItemClickListener) {
        this.listener = listener
    }

    inner class PackageInfoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tv_name)
        val tvPackageName: TextView = view.findViewById(R.id.tv_package_name)
        val tvVersion: TextView = view.findViewById(R.id.tv_version)
        val ivIcon: ImageView = view.findViewById(R.id.iv_icon)
    }
}