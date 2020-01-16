package alexrnov.androidsamples;

import android.app.Application;
// Объект класса создается при запуске приложения.
public class Initialization extends Application {
  public static final String TAG = "Application";
  @Override
  public void onCreate() {
    System.out.println("INITIALIZATION");
    super.onCreate();
  }
}
