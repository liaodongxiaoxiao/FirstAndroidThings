package com.karl.demo.athings

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.autoai.vehicleutils.adapter.PackageInfoAdapter
import com.google.android.material.snackbar.Snackbar
import com.ldxx.tv.entity.PackageInfoBean
import kotlinx.android.synthetic.main.activity_package_info.*

private val TAG = MainActivity::class.java.simpleName

class MainActivity : AppCompatActivity() {

    private lateinit var snackbar: Snackbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_package_info)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "应用列表"
            setDisplayHomeAsUpEnabled(true)
        }

        setUpData(getInstalledApps())
    }

    private fun setUpData(t: MutableList<PackageInfoBean>) {
        val adapter = PackageInfoAdapter(t)
        adapter.setOnPackageItemClickListener(object :
            PackageInfoAdapter.OnPackageItemClickListener {
            override fun onItemClick(packageName: String?, appName: String?) {
                toDetail(packageName, appName)

            }

        })
        rv_app_list.layoutManager = LinearLayoutManager(this)
        rv_app_list.adapter = adapter
    }

    private fun toDetail(packageName: String?, appName: String?) {
        if (packageName != null) {
            val pm: PackageManager = packageManager
            val launchIntent = pm.getLaunchIntentForPackage(packageName)
            startActivity(launchIntent)
        } else {
            Toast.makeText(this, "package name 为空", Toast.LENGTH_SHORT).show()
        }
    }


    private fun getInstalledApps(): MutableList<PackageInfoBean> {
        val res: MutableList<PackageInfoBean> = mutableListOf()
        val packs = packageManager.getInstalledPackages(0)
        for (i in packs.indices) {
            val p = packs[i]
            if (p.versionName == null) {
                continue
            }


            val newInfo = PackageInfoBean()
            newInfo.appName = p.applicationInfo.loadLabel(packageManager).toString()
            newInfo.packageName = p.packageName
            newInfo.versionName = p.versionName
            //newInfo.versionCode = p.versionCode
            newInfo.icon = p.applicationInfo.loadIcon(packageManager)
            res.add(newInfo)

        }
        return res
    }


    override fun onDestroy() {
        super.onDestroy()
    }

}
