package alexrnov.androidsamples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import alexrnov.androidsamples.Initialization.TAG
import alexrnov.androidsamples.parcelable.ParcelableActivity
import android.content.Intent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun parcelableButton(view: View) {
      Log.i(TAG, "click")
      val intent = Intent(this, ParcelableActivity::class.java)
      startActivity(intent)
    }
}
