package alexrnov.androidsamples.espresso

import alexrnov.androidsamples.R
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import alexrnov.androidsamples.Initialization.TAG
import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.widget.EditText
import android.widget.TextView
import android.provider.ContactsContract
const val PICK_CONTACT: Int = 1

class EspressoActivityFirst: AppCompatActivity() {
  private var editText: EditText? = null
  private var textView: TextView? = null
  private var textView2: TextView? = null
  private var textView3: TextView? = null

  override fun onCreate(saveInstanceState: Bundle?) {
    super.onCreate(saveInstanceState)
    setContentView(R.layout.activity_espresso)
    editText = findViewById(R.id.espresso_edit_text)
    textView = findViewById(R.id.espresso_text_view)
    textView2 = findViewById(R.id.espresso_text_view2)
    textView3 = findViewById(R.id.espresso_text_view3)
  }

  /**
   * Демонстрация теста espresso для автоматического взаимодействия
   * с пользовательским интерфейсом
   */
  fun espressoChangeTextButton(view: View) {
    Log.i(TAG, "espresso")
    val inputText = editText?.text.toString()
    textView?.text = when (inputText) {
      "" -> "empty string"
      "w" -> "W"
      else -> inputText
    }
  }

  // это действие проверяется в тесте EspressoIntentTest на отправку данных через putExtra
  fun toOtherActivityButton(view: View) {
    val intent = Intent(this, EspressoActivitySecond::class.java)
    intent.putExtra("retrieve_text", "retrieve text")
    startActivity(intent)
  }

  fun hideTextButton(view: View) {
    textView2?.visibility = View.INVISIBLE // скрыть текст
  }

  /** выбрать контакт из списка контактов */
  fun getPhoneNumberButton(view: View) {
    val intent = Intent(Intent.ACTION_PICK,
      ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
    startActivityForResult(intent, 1)
  }

  public override fun onActivityResult(reqCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(reqCode, resultCode, data)
    // извлечь имя и номер выбранного контакта
    if (reqCode == PICK_CONTACT) {
      if (resultCode == Activity.RESULT_OK) {
        val contactData = data?.getData()
        println("contactData = $contactData")

        val projection = arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER,
          ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
        val content: ContentResolver = applicationContext.contentResolver
        // необходимо разрешение в манифесте android.permission.READ_CONTACTS
        val cursor = content.query(contactData!!, projection, null, null, null)
        cursor!!.moveToFirst()

        val numberColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
        val number = cursor.getString(numberColumnIndex)
        println("number = $number")

        val nameColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
        val name = cursor.getString(nameColumnIndex)
        println("name = $name")
        cursor.close()

        textView3?.text = number
      }
    }
  }
}