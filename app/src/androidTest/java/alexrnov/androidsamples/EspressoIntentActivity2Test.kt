package alexrnov.androidsamples

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EspressoIntentActivity2Test {

  @get:Rule
  var intentsRule: IntentsTestRule<MainActivity>
          = IntentsTestRule(MainActivity::class.java)

  @Test
  fun f() {
    onView(withId(R.id.button3)).perform(click())
    Intents.intended(
      CoreMatchers.allOf(
        hasComponent(hasShortClassName(".espresso.Activity2")),
        toPackage("alexrnov.androidsamples"),
        hasExtra("send", "send text")
      )
    )
  }
}