package alexrnov.androidsamples.material

import alexrnov.androidsamples.R
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import alexrnov.androidsamples.Initialization.TAG

class MaterialActivity: AppCompatActivity() {
  override fun onCreate(bundle: Bundle?) {
    super.onCreate(bundle)
    setContentView(R.layout.activity_material)
  }

  fun button1(view: View) {
    Log.i(TAG, "button1 click")
  }
}