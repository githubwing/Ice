package net.androidwing.ice.bean

import android.graphics.drawable.Drawable

/**
 * Created by wing on 3/8/17.
 */
data class AppInfo(
    val appName: String,
    val packageName: String,
    val mainClass:String,
    val appIcon: Drawable,
    var enable: Boolean
)