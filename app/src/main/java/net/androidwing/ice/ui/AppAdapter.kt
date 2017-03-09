package net.androidwing.ice.ui

import android.graphics.Paint
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
class AppAdapter(private var mList: List<AppInfo>) : RecyclerView.Adapter<AppAdapter.ViewHolder>() {
  private var mListener: ((view: View, pos: Int) -> Unit)? = null
  private var mLongClickListener:((view: View, pos: Int) -> Unit)? = null
  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {

    return ViewHolder(
        LayoutInflater.from(parent?.context).inflate(R.layout.item_app, parent, false))
  }

  override fun getItemCount(): Int {
    return mList.size
  }

  override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
    val appInfo = mList[position]
    holder?.nameTxt?.text = appInfo.appName
    holder?.iconImg?.setImageDrawable(appInfo.appIcon)

    if(appInfo.enable){
      holder?.nameTxt?.paint?.flags = Paint.ANTI_ALIAS_FLAG
    }else{

      holder?.nameTxt?.paint?.flags = Paint.STRIKE_THRU_TEXT_FLAG
    }

    holder?.nameTxt?.paint?.isAntiAlias = true
    holder?.itemView?.setOnClickListener {
      if (mListener != null) {
        mListener?.invoke(holder.itemView!!, position)
      }
    }

    holder?.itemView?.setOnLongClickListener {
      if(mLongClickListener != null){
        mLongClickListener?.invoke(holder.itemView!!,position)

      }
      true
    }

  }

  class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var nameTxt = view.findViewById(R.id.tv_app_name) as TextView
    var iconImg = view.findViewById(R.id.iv_app_icon) as ImageView
  }

  fun setOnItemClickListener(listener: ((view: View, pos: Int) -> Unit)) {
    mListener = listener
  }


  fun setOnLongItemClickListener(listener: ((view: View, pos: Int) -> Unit)) {
    mLongClickListener = listener
  }
}