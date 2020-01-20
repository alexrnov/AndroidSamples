package alexrnov.androidsamples.espresso

import alexrnov.androidsamples.R
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import alexrnov.androidsamples.Initialization.TAG
import android.content.Intent
import android.widget.EditText
import android.widget.TextView

class EspressoActivity: AppCompatActivity() {
  private var editText: EditText? = null
  private var textView: TextView? = null

  override fun onCreate(saveInstanceState: Bundle?) {
    super.onCreate(saveInstanceState)
    setContentView(R.layout.activity_espresso)
    editText = findViewById(R.id.espresso_edit_text)
    textView = findViewById(R.id.espresso_text_view)
  }

  /**
   * Демонстрация теста espresso для автоматического взаимодействия
   * с пользовательским интерфейсом
   */
  fun espressoChangeTextButton(view: View) {
    Log.i(TAG, "espresso")
    val inputText = editText?.text.toString()
    textView?.text = when (inputText) {
      "" -> "empty string"
      "w" -> "W"
      else -> inputText
    }
  }

  fun toOtherActivityButton(view: View) {
    val intent = Intent(this, EspressoActivitySecond::class.java)
    intent.putExtra("retrieve_text", "retrieve text")
    startActivity(intent)
  }
}