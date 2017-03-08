package net.androidwing.ice.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import net.androidwing.ice.R
import net.androidwing.ice.bean.AppInfo
import net.androidwing.ice.mvp.AppContract
import net.androidwing.ice.mvp.AppModel
import net.androidwing.ice.mvp.AppPresenter

class MainActivity : AppCompatActivity(), AppContract.View {
  private lateinit var mPresenter: AppContract.Presenter
  private lateinit var  mRecyclerView: RecyclerView


  override fun setAppList(apps: List<AppInfo>) {
    mRecyclerView.adapter = AppAdapter(apps)
    mRecyclerView.layoutManager = GridLayoutManager(this,5)
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
