package alexrnov.androidsamples.espresso

import alexrnov.androidsamples.Initialization.TAG
import alexrnov.androidsamples.MainActivity
import alexrnov.androidsamples.R
import android.app.Activity
import android.app.Instrumentation
import android.content.Context
import android.content.Intent
import android.provider.ContactsContract
import android.util.Log
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.*
import com.google.common.truth.Truth
import org.junit.Ignore

/**
 * Espresso Intents enables validation and stubbing of intents sent out by an app.
 * With Espresso Intents, you can test an app, activity, or service in isolation by
 * intercepting outgoing intents, stubbing the result, and sending it back to the
 * component under test.
 */
@RunWith(AndroidJUnit4::class)
class EspressoIntentTest {
  /*
   * The IntentsTestRule class initializes Espresso Intents before each test,
   * terminates the host activity, and releases Espresso Intents after each test.
   */
  @get:Rule
  var intentsRule: IntentsTestRule<EspressoActivityFirst>
          = IntentsTestRule(EspressoActivityFirst::class.java)

  @Test
  fun pass_extra_to_activity() {
    // Clicks a button to send the message to another activity through an explicit intent.
    onView(withId(R.id.espresso_button_start_activity)).perform(click())
    // Проверить, что активити EspressoActivitySecond получила намерение
    // с правильным именем пакета и сообщением extra.
    // allOf() - позволяет указать комбинацию свойст (для устранения неоднозначностей)
    intended(allOf(
      hasComponent(hasShortClassName(".espresso.EspressoActivitySecond")),
      toPackage("alexrnov.androidsamples"),
      hasExtra("retrieve_text", "retrieve text")))

    // проверить, что надпись в новом активити отображается
    onView(withId(R.id.espresso_second_text_view)).check(matches(isDisplayed()))
    // проверить, что текст надписи в новом активити изменился
    onView(withId(R.id.espresso_second_text_view)).check(matches(withText("retrieve text")))

    // проверить, что представление (кнопка) ушло из иерархии представлений
    // так как был выполнен переход к другому действию
    onView(withId(R.id.espresso_button_start_activity)).check(doesNotExist())

  }

  /** проверяет возвращенный номер из списка контактов */
  @Test
  fun activityResult_DisplaysContactPhoneNumber() {
    onView(withId(R.id.get_phone_number)).perform(click())

    val device:UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    // прокрутить список пока не будет найден контакт "Бабушка"
    val settingsItem = UiScrollable(UiSelector().className("android.widget.ListView"))
    val babuchka: UiObject = settingsItem.getChildByText(
      UiSelector().className("android.widget.LinearLayout"), "Бабушка")
    babuchka.click()

    device.wait(Until.hasObject(By.pkg("alexrnov.androidsamples").depth(0)), 20000L)
    onView(withId(R.id.espresso_text_view3)).check(matches(withText("8 914 295-98-01")))
  }

  /** демонстрация заглушки для Intent - не работает */
  @Test
  fun activityResult_DisplaysContactPhoneNumber2() {
    // Build the result to return when the activity is launched.
    val resultData = Intent()
    val phoneNumber = "123-345-6789"
    resultData.putExtra("phone", phoneNumber)
    val result = Instrumentation.ActivityResult(Activity.RESULT_OK, resultData)
    // Set up result stubbing when an intent sent to "contacts" is seen.
    //intending(toPackage("com.android.contacts")).respondWith(result)
    Log.i(TAG, "ACTION_PICK = ${Intent.ACTION_PICK}")
    //intending(toPackage(Intent.ACTION_PICK)).respondWith(result)
    intending(toPackage("android.intent.action.PICK")).respondWith(result)
    // User action that results in "contacts" activity being launched.
    // Launching activity expects phoneNumber to be returned and displayed.
    // по идее список контактов открываться не должен, т.к. используется
    // заглужка, но он почему-то открывается. Необходимо разобраться -
    // может быть на других интентах, возвращающий результат.
    onView(withId(R.id.get_phone_number)).perform(click())
    // Assert that the data we set up above is shown.
    //onView(withId(R.id.espresso_text_view3)).check(matches(withText(phoneNumber)))
  }
}