package net.androidwing.ice.mvp

/**
 * Created by wing on 3/8/17.
 */
class AppPresenter(
    private var mModel: AppContract.Model,
    private var mView: AppContract.View) : AppContract.Presenter {
  override fun setAppList() {
    mView.setAppList(mModel.getAppList())
  }
}