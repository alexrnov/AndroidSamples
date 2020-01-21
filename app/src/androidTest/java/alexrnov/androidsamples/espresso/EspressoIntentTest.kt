package alexrnov.androidsamples.espresso

import alexrnov.androidsamples.R
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

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
    intended(allOf(
      hasComponent(hasShortClassName(".espresso.EspressoActivitySecond")),
      toPackage("alexrnov.androidsamples"),
      hasExtra("retrieve_text", "retrieve text")))
  }
}