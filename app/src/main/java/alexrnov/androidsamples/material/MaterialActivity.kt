package alexrnov.androidsamples.material

import alexrnov.androidsamples.R
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import alexrnov.androidsamples.Initialization.TAG
import android.widget.Button

class MaterialActivity: AppCompatActivity() {
  private var button5: Button? = null

  override fun onCreate(bundle: Bundle?) {
    super.onCreate(bundle)
    setContentView(R.layout.activity_material)
    button5 = findViewById(R.id.material_button_5)

    /*
     * java: button5.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
      }
    });
     *
     */
    button5?.setOnClickListener {
      Log.i(TAG, "button5 click")
    }
  }

  fun button1(view: View) {
    Log.i(TAG, "button1 click")
  }

  fun button2(view: View) {
    Log.i(TAG, "button2 click")
  }
}