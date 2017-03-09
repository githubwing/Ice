package net.androidwing.ice

import android.content.Context
import java.io.DataOutputStream
import java.io.File


/**
 * Created by wing on 3/9/17.
 */
object Ice {
  fun disable(packageName: String) {
    val cmd = "pm disable $packageName"

    runSuCmd(cmd)
  }

  fun enable(packageName: String) {
    val cmd = "pm enable $packageName"
    runSuCmd(cmd)
  }

  private fun runSuCmd(cmd: String) {
    var process: Process? = null
    var os: DataOutputStream? = null
    try {
      process = Runtime.getRuntime().exec("su") // 切换到root帐号
      os = DataOutputStream(process!!.outputStream)
      os.writeBytes(cmd + "\n")
      os.writeBytes("exit\n")
      os.flush()
      process.waitFor()
//      return true
    } catch (e: Exception) {
//      return false
    } finally {
      try {
        if (os != null) {
          os.close()
        }
        process!!.destroy()
      } catch (e: Exception) {
      }

    }

  }

  @Synchronized fun getRootAhth(): Boolean {
    var process: Process? = null
    var os: DataOutputStream? = null
    try {
      process = Runtime.getRuntime().exec("su")
      os = DataOutputStream(process!!.outputStream)
      os.writeBytes("exit\n")
      os.flush()
      val exitValue = process.waitFor()
      return exitValue == 0
    } catch (e: Exception) {
      return false
    } finally {
      try {
        if (os != null) {
          os.close()
        }
        process!!.destroy()
      } catch (e: Exception) {
        e.printStackTrace()
      }

    }
  }
}
