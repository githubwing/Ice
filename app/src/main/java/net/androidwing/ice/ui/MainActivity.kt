package net.androidwing.ice.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import net.androidwing.ice.R
import net.androidwing.ice.bean.AppInfo
import net.androidwing.ice.mvp.AppContract
import net.androidwing.ice.mvp.AppModel
import net.androidwing.ice.mvp.AppPresenter

class MainActivity : AppCompatActivity(), AppContract.View {
  private lateinit var mPresenter: AppContract.Presenter

  override fun setAppList(apps: List<AppInfo>) {
    for ((appName, packageName) in apps) {
      Log.e("wing", appName + " " + packageName)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    mPresenter = AppPresenter(AppModel(), this)
    mPresenter.setAppList()
  }
}
