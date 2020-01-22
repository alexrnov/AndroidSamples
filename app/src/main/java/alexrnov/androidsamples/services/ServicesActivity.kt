package alexrnov.androidsamples.services

import alexrnov.androidsamples.R
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import alexrnov.androidsamples.Initialization.TAG
import android.content.Intent

class ServicesActivity: AppCompatActivity() {

  override fun onCreate(bundle: Bundle?) {
    super.onCreate(bundle)
    setContentView(R.layout.services_activity)
  }

  fun service1Button(view: View) {
    Log.i(TAG, "service1Button")
    val intent = Intent(this, Service1::class.java)
    intent.putExtra("message", "text message")
    startService(intent)
  }
}