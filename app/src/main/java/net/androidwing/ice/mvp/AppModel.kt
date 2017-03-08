package net.androidwing.ice.mvp

import net.androidwing.ice.App
import net.androidwing.ice.bean.AppInfo

/**
 * Created by wing on 3/8/17.
 */
class AppModel : AppContract.Model {
  override fun getAppList(): List<AppInfo> {
    val pm = App.instance.packageManager
    //已安装程序列表
    val packageList = pm.getInstalledPackages(0)
    val appList = ArrayList<AppInfo>()
    packageList.mapTo(appList) {
      AppInfo(it.applicationInfo.loadLabel(pm) as String,
          it.packageName, it.applicationInfo.loadIcon(pm))
    }

    return appList
  }
}