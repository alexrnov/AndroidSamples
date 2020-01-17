package alexrnov.androidsamples.parcelable

import alexrnov.androidsamples.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import alexrnov.androidsamples.Initialization.TAG
class ParcelableSecondActivity: AppCompatActivity() {

  private var nameLabel: TextView? = null
  private var ageLabel: TextView? = null
  private var rollnoLabel: TextView? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_parcelable_second)
    nameLabel = findViewById(R.id.parcel_second_name_textView)
    ageLabel = findViewById(R.id.parcel_second_age_textView)
    rollnoLabel = findViewById(R.id.parcel_second_rollno_textView)
    val student: Student? = intent.getParcelableExtra("st")
    student?.let {
      nameLabel?.text = it.name
      ageLabel?.text = it.age.toString()
      rollnoLabel?.text = it.rollno.toString()
    }
  }

}