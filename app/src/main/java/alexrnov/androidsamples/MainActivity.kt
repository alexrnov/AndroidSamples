package alexrnov.androidsamples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import alexrnov.androidsamples.espresso.EspressoActivityFirst
import alexrnov.androidsamples.parcelable.ParcelableFirstActivity
import android.content.Intent
import android.util.Log
import alexrnov.androidsamples.Initialization.TAG
import alexrnov.androidsamples.material.MaterialActivity
import alexrnov.androidsamples.services.ServicesActivity

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  /**
  * Parcelable is an Android only interface which is used to serialize a class so its properties
  * can be transferred from one activity to another.
  */
  fun parcelableButton(view: View) {
    val intent = Intent(this, ParcelableFirstActivity::class.java)
    startActivity(intent)
  }

  /** Демо использования тестов Espresso */
  fun espessoButton(view: View) {
    val intent = Intent(this, EspressoActivityFirst::class.java)
    //startActivityForResult(Intent(android.provider.Settings.ACTION_SETTINGS), 0);
    startActivity(intent)
  }

  fun servicesButton(view: View) {
    val intent = Intent(this, ServicesActivity::class.java)
    startActivity(intent)
  }

  fun materialDesignButton(view: View) {
    val intent = Intent(this, MaterialActivity::class.java)
    startActivity(intent)
  }
}
