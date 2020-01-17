package alexrnov.androidsamples.parcelable

import alexrnov.androidsamples.R
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

import alexrnov.androidsamples.Initialization.TAG
import android.content.Intent

class ParcelableFirstActivity: AppCompatActivity() {
  private var student: Student? = null
  private var nameEditText: EditText? = null
  private var ageEditText: EditText? = null
  private var rollnoEditText: EditText? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_parcelable_first)
    nameEditText = findViewById(R.id.parcel_name_textview)
    ageEditText = findViewById(R.id.parcel_age_textview)
    rollnoEditText = findViewById(R.id.parcel_pollno_textview)
  }

  fun sendParcelButton(view: View) {
    val age = Integer.parseInt(ageEditText?.text.toString())
    val rollno = Integer.parseInt(rollnoEditText?.text.toString())
    student = Student(nameEditText?.text.toString(), age, rollno)

    val intent = Intent(this, ParcelableSecondActivity::class.java)
    intent.putExtra("st", student)
    startActivity(intent)
  }
}