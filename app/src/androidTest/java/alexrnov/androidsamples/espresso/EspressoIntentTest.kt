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
import org.hamcrest.CoreMatchers.allOf
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EspressoIntentTest {

  @get:Rule
  var intentsRule: IntentsTestRule<EspressoActivity>
          = IntentsTestRule(EspressoActivity::class.java)

  @Test
  fun f() {
    onView(withId(R.id.espresso_button_start_activity)).perform(click())
    intended(allOf(
      hasComponent(hasShortClassName(".EspressoActivitySecond")),
      toPackage("alexrnov.androidsamples.espresso"),
      hasExtra("retrieve_text", "retrieve text")))
  }
}