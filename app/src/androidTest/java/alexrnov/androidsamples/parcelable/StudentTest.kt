package alexrnov.androidsamples.parcelable

import alexrnov.androidsamples.Initialization.TAG
import android.os.Parcel
import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat

import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Инструментальный тест проверяет, что интерфейс Parcelable правильно
 * реализован для класса Student
 */
@RunWith(AndroidJUnit4::class)
@SmallTest
class StudentTest {
  private lateinit var student: Student

  @Before
  fun createStudent() {
    // Set up the Parcelable object to send and receive.
    student = Student("alexr", 4, 5)
  }

  @Test
  fun parcelable_test() {
    // write the data
    val parcel = Parcel.obtain()
    student.writeToParcel(parcel, student.describeContents())

    // after you're done with writing, you need to reset the parcel for reading.
    parcel.setDataPosition(0)

    // read the data.
    val studentFromParcel = Student.CREATOR.createFromParcel(parcel)
    Log.i(TAG, "name = ${studentFromParcel.name}, age = ${studentFromParcel.age}, rollno = ${studentFromParcel.rollno}")

    // verify that the received data is correct
    assertThat(studentFromParcel.name).isEqualTo("alexr")
    assertThat(studentFromParcel.age).isEqualTo(4)
    assertThat(studentFromParcel.rollno).isEqualTo(5)
  }
}