package net.androidwing.ice.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import net.androidwing.ice.R
import net.androidwing.ice.bean.AppInfo

/**
 * Created by wing on 2017/3/8.
 */
class AppAdapter(private var mList :List<AppInfo>) : RecyclerView.Adapter<AppAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_app,parent,false))
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val appInfo = mList[position]
        holder?.nameTxt?.text = appInfo.appName
        holder?.iconImg?.setImageDrawable(appInfo.appIcon)
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var nameTxt = view.findViewById(R.id.tv_app_name) as TextView
        var iconImg = view.findViewById(R.id.iv_app_icon) as ImageView
    }
}