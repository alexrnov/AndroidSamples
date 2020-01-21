package alexrnov.androidsamples.espresso

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

private const val LAUNCH_TIMEOUT = 5000L
private const val BASIC_SAMPLE_PACKAGE = "alexrnov.androidsamples"

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class AutomatorTest {
  private lateinit var device: UiDevice

  @Before
  fun startMainActivityFromHomeScreen() {
    device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    device.pressHome()
    val launcherPackage: String = device.launcherPackageName
    assertThat(launcherPackage, notNullValue())
    device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
      LAUNCH_TIMEOUT)

    val context = ApplicationProvider.getApplicationContext<Context>()
    val intent = context.packageManager
      .getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE)?.apply {
        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
    }

    context.startActivity(intent)

    device.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
      LAUNCH_TIMEOUT)
  }

  @Test
  fun f() {

  }
}