package alexrnov.androidsamples.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {

  private String name;
  private Integer age;
  private Integer rollno;

  protected Student(Parcel in) {
    age = in.readInt();
    name = in.readString();
    rollno = in.readInt();
  }

  public Student(String name, Integer age, Integer rollno) {
    this.name = name;
    this.age = age;
    this.rollno = rollno;
  }

  // This is the method which is used to bind everything together.
  public static final Creator<Student> CREATOR = new Creator<Student>() {
    @Override
    public Student createFromParcel(Parcel in) {
      return new Student(in);
    }

    @Override
    public Student[] newArray(int size) {
      return new Student[size];
    }
  };

  //This method doesn’t do much.
  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int flags) {
    parcel.writeInt(age);
    parcel.writeString(name);
    parcel.writeInt(rollno);
  }
}
