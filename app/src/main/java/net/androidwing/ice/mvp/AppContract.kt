package net.androidwing.ice.mvp

import net.androidwing.ice.bean.AppInfo

/**
 * Created by wing on 3/8/17.
 */
class AppContract {
  interface Model {

    fun getAppList(): List<AppInfo>
  }

  interface View {
    fun setAppList(apps:List<AppInfo>)
  }

  interface Presenter {

    fun setAppList()
  }
}