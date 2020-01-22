package alexrnov.androidsamples.services

import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ServiceTestRule
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.any
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat
import java.util.concurrent.TimeoutException

/** Тестирование привязанной службы */
@RunWith(AndroidJUnit4::class)
class ServiceTest {

  @get:Rule
  val serviceRule = ServiceTestRule()

  @Test
  @Throws(TimeoutException::class)
  fun testWithBoundService() {
    // Create the service Intent.
    val serviceIntent = Intent(ApplicationProvider.getApplicationContext<Context>(),
      Service2::class.java).apply {
      // Data can be passed to the service via the Intent.
      putExtra("message", "message text to service2")
    }
    // Bind the service and grab a reference to the binder.
    val binder: IBinder = serviceRule.bindService(serviceIntent)
    // Get the reference to the service, or you can call
    // public methods on the binder directly.
    val service: Service2 = (binder as Service2.LocalBinder).service
    // Verify that the service is working correctly.
    // проверить что сервис вернул правильные данные через свои публичные
    // методы (случайное число и сообщение для toast)
    assertThat(service.randomNumber, `is`(any(Int::class.java)))
    assertThat(service.message).isEqualTo("message text to service2")
  }
}