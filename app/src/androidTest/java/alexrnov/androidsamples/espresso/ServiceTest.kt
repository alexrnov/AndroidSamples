package alexrnov.androidsamples.espresso

import alexrnov.androidsamples.services.Service1
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ServiceTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ServiceTest {

  @get:Rule
  val serviceRule = ServiceTestRule()

  @Test
  fun testWithBoundService() {
    val serviceIntent = Intent(ApplicationProvider.getApplicationContext<Context>(),
      Service1::class.java).apply {
      putExtra("message", "text message")
    }

    val binder: IBinder = serviceRule.bindService(serviceIntent)
  }
}