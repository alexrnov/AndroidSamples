package alexrnov.androidsamples.espresso

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.*
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

private const val LAUNCH_TIMEOUT = 5000L
private const val BASIC_SAMPLE_PACKAGE = "alexrnov.androidsamples"

/** Запуск приложения AndroidSamples с домашнего экрана */
@RunWith(AndroidJUnit4::class)
// аннотация проверяет, что используется API минимум 18 версии, как требует фреймворк Automator
@SdkSuppress(minSdkVersion = 18)
class AutomatorTest {
  private lateinit var device: UiDevice

  @Before
  fun startMainActivityFromHomeScreen() {
    // Initialize UiDevice instance
    device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
  }

  @Test
  fun launchApplication() {
    // Start from the home screen
    device.pressHome()

    // ждем запуска
    val launcherPackage: String = device.launcherPackageName
    assertThat(launcherPackage, notNullValue())
    device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
      LAUNCH_TIMEOUT)

    // Launch the app
    val context = ApplicationProvider.getApplicationContext<Context>()

    val intent = context.packageManager
      .getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE)?.apply {
        // Clear out any previous instances
        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
      }

    context.startActivity(intent)

    // получить объект кнопки. Если вы хотите получить доступ к определенному
    // компоненту пользовательского интерфейса в приложении, используйте класс
    // UiSelector. Этот класс представляет запрос для определенных элементов в
    // отображаемом в настоящее время пользовательском интерфейсе.
    val espressoButton: UiObject = device.findObject(UiSelector()
      .resourceId("$BASIC_SAMPLE_PACKAGE:id/button2"))
    // поиск по тексту не рекомендуется - лучше использовать идентификатор, как указано выше
    //val espressoButton: UiObject = device.findObject(UiSelector().text("Espresso").className("android.widget.Button"))
    //if(espressoButton.exists() && espressoButton.isEnabled) {
      espressoButton.click() // нажать на кнопку Espresso
    //}

    // Wait for the app to appear
    device.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
      LAUNCH_TIMEOUT)
  }
}