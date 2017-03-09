package net.androidwing.ice.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import net.androidwing.ice.Ice
import net.androidwing.ice.R
import net.androidwing.ice.bean.AppInfo
import net.androidwing.ice.mvp.AppContract
import net.androidwing.ice.mvp.AppModel
import net.androidwing.ice.mvp.AppPresenter
import android.content.Intent
import android.content.ComponentName


class MainActivity : AppCompatActivity(), AppContract.View {
  private lateinit var mPresenter: AppContract.Presenter
  private lateinit var mRecyclerView: RecyclerView
  private lateinit var mAdapter: AppAdapter

  override fun setAppList(apps: List<AppInfo>) {
    mAdapter = AppAdapter(apps)
    mRecyclerView.adapter = mAdapter
    mRecyclerView.layoutManager = GridLayoutManager(this, 4)
    mAdapter.setOnItemClickListener { view, pos ->

      val appInfo = apps[pos]

      //如果是App本身
      if (appInfo.packageName == "net.androidwing.ice") {
        Toast.makeText(this, "老大，冻了我你怎么给他们解冻啊~~", 0).show()
        return@setOnItemClickListener
      }

      //进行对应冰冻&解除操作
      if (appInfo.enable) {
        Ice.disable(appInfo.packageName)

      } else {
        Ice.enable(appInfo.packageName)
      }

      if (Ice.getRootAhth().not()) {
        Toast.makeText(this, "获取root权限失败", 0).show()
        return@setOnItemClickListener
      }

      appInfo.enable = appInfo.enable.not()
      mAdapter.notifyDataSetChanged()
    }

    mAdapter.setOnLongItemClickListener { view, pos ->
      val appInfo = apps[pos]
      //如果没有权限 则先开启再转跳
      if (appInfo.enable.not()) {
        Ice.enable(appInfo.packageName)
      }

      if (Ice.getRootAhth().not()) {
        Toast.makeText(this, "获取root权限失败", 0).show()
        return@setOnLongItemClickListener
      }
      startApp(appInfo)


      appInfo.enable = true
      mAdapter.notifyDataSetChanged()

    }
  }

  /**
   * 转跳至app
   */
  private fun startApp(appInfo: AppInfo) {
    val intent = Intent(Intent.ACTION_MAIN)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    intent.component = ComponentName(appInfo.packageName, appInfo.mainClass)
    intent.addCategory(Intent.CATEGORY_LAUNCHER)
    startActivity(intent)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    initView()
    mPresenter = AppPresenter(AppModel(), this)
    mPresenter.setAppList()


  }

  private fun initView() {
    mRecyclerView = findViewById(R.id.recyclerView) as RecyclerView
  }
}
