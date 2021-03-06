package net.androidwing.ice.mvp

import android.content.pm.ApplicationInfo
import android.util.Log
import net.androidwing.ice.App
import net.androidwing.ice.bean.AppInfo
import java.util.*
import android.content.Intent
import android.content.pm.PackageManager


/**
 * Created by wing on 3/8/17.
 */
class AppModel : AppContract.Model {
  override fun getAppList(): List<AppInfo> {
    val pm = App.instance.packageManager
    //已安装程序列表
    val packageList = pm.getInstalledPackages(PackageManager.GET_ACTIVITIES)

    val appList = ArrayList<AppInfo>()
    val mainIntent = Intent(Intent.ACTION_MAIN, null)
    mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)




    //用户安装应用
    packageList.filterNot { (it.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM) > 0 }
        .mapTo(appList) {

          AppInfo(it.applicationInfo.loadLabel(pm) as String,
              it.packageName, it.activities[0].name,it.applicationInfo.loadIcon(pm), it.applicationInfo.enabled)
        }

    return appList
  }
}