package alexrnov.androidsamples.espresso

import alexrnov.androidsamples.R
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class EspressoActivityTest {

  private lateinit  var stringToBetyped: String

  /**
   * By using ActivityTestRule, the testing framework launches the activity under
   * test before each test method annotated with @Test and before any method
   * annotated with @Before. The framework handles shutting down the activity
   * after the test finishes and all methods annotated with @After are run.
   */
  @get:Rule
  var activityRule: ActivityTestRule<EspressoActivity>
          = ActivityTestRule(EspressoActivity::class.java)

  @Before
  fun initValidString() {
    stringToBetyped = "Espresso"
  }

  /**
   * Проверить значение текста для text_view после ввода любого текста
   * в поле edit_text и нажатия кнопки
   */
  @Test
  fun f() {
    // type text and then press the button
    onView(withId(R.id.espresso_edit_text)) // onView() - найти представление
      .perform(typeText(stringToBetyped), closeSoftKeyboard()) // perform() - иммитация взаимодействия пользователя с компонентом
    // click on the button
    onView(withId(R.id.espresso_button)).perform(click())
    // check that the text was changed
    onView(withId(R.id.espresso_text_view)).check(matches(withText(stringToBetyped)))
  }

  /**
   * Проверить значение текста для text_view после ввода текста "w"
   * в поле edit_text и нажатия кнопки.
   */
  @Test
  fun f2() {
    onView(withId(R.id.espresso_edit_text))
      .perform(typeText("w"), closeSoftKeyboard())
    onView(withId(R.id.espresso_button)).perform(click())
    onView(withId(R.id.espresso_text_view)).check(matches(withText("W")))
    /*
     * проверить значение текста для text_view после ввода пустого текста
     * в поле edit_text и нажатия кнопки.
     */
    onView(withId(R.id.espresso_edit_text))
      .perform(clearText()) // отчистить поле ввода
    onView(withId(R.id.espresso_button)).perform(click())
    onView(withId(R.id.espresso_text_view)).check(matches(withText("empty string")))
  }
}