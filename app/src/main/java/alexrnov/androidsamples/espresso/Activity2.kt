package alexrnov.androidsamples.espresso

import alexrnov.androidsamples.R
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Activity2: AppCompatActivity() {
  private var textView: TextView? = null

  override fun onCreate(bundle: Bundle?) {
    super.onCreate(bundle)
    setContentView(R.layout.activity2)
    textView = findViewById(R.id.activity2_text_view)
    val s = intent.getStringExtra("send")
    textView?.text = s
  }
}