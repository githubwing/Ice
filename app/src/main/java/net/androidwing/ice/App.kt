package net.androidwing.ice

import android.app.Application
import kotlin.properties.Delegates

/**
 * Created by wing on 3/8/17.
 */
class App : Application() {

  override fun onCreate() {
    super.onCreate()
    instance = this
  }


  companion object {
    var instance: App by Delegates.notNull()
  }
}
