package alexrnov.androidsamples.espresso

import alexrnov.androidsamples.R
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EspressoActivitySecond: AppCompatActivity() {

  private var textView: TextView? = null
  @Override
  override fun onCreate(bundle: Bundle?) {
    super.onCreate(bundle)
    setContentView(R.layout.activity_espresso_second)
    val s = intent.getStringExtra("retrieve_text")
    textView = findViewById(R.id.espresso_second_text_view)
    textView?.text = s
  }
}