package alexrnov.androidsamples.material

import alexrnov.androidsamples.R
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import alexrnov.androidsamples.Initialization.TAG
import android.content.Intent
import android.content.SharedPreferences
import android.widget.Button

/**
 * Для кнопок material design следует переопределять метод onClick программно,
 * поскольку метод onClick определенный в лэйауте может не работать
 */
class MaterialActivity2: AppCompatActivity() {

  private var b1: Button? = null
  private var b2: Button? = null
  private var b3: Button? = null
  private var b4: Button? = null
  private var b5: Button? = null
  private var b6: Button? = null

  private var sp: SharedPreferences? = null

  override fun onCreate(bundle: Bundle?) {
    val packageName = this.applicationContext.packageName
    sp = getSharedPreferences(packageName, MODE_PRIVATE)
    val b = sp?.getBoolean("changeTheme", false)
    // поменять тему для всего приложения
    if (b == true) setTheme(R.style.AppTheme2) else setTheme(R.style.AppTheme)
    super.onCreate(bundle)
    setContentView(R.layout.activity_material2)
    b1 = findViewById(R.id.m2_b1)
    b2 = findViewById(R.id.m2_b2)
    b3 = findViewById(R.id.m2_b3)
    b4 = findViewById(R.id.m2_b4)
    b5 = findViewById(R.id.m2_b5)
    b6 = findViewById(R.id.m2_b6) // кнопка с собственным стилем

    b1?.setOnClickListener { Log.i(TAG, "button click b1") }
    b2?.setOnClickListener { Log.i(TAG, "button click b2") }
    b3?.setOnClickListener { Log.i(TAG, "button click b3") }
    // поменять тему, записав sharedPreferences и перезапустив activity
    b4?.setOnClickListener { Log.i(TAG, "button click b4")
      if (sp != null) {
        val editor: SharedPreferences.Editor = sp!!.edit()
        editor.putBoolean("changeTheme", false)
        editor.apply()
      }
      val intent = Intent(this, MaterialActivity2::class.java)
      startActivity(intent)
    }
    // поменять тему, записав sharedPreferences и перезапустив activity
    b5?.setOnClickListener {
      Log.i(TAG, "button click b5")
      if (sp != null) {
        val editor: SharedPreferences.Editor = sp!!.edit()
        editor.putBoolean("changeTheme", true)
        editor.apply()
      }
      val intent = Intent(this, MaterialActivity2::class.java)
      startActivity(intent)
      //val theme = super.getTheme()
      //theme.applyStyle(R.style.AppTheme2, true)
    }
    b6?.setOnClickListener { Log.i(TAG, "button click b6") }
  }
}