package alexrnov.androidsamples.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

import static alexrnov.androidsamples.Initialization.TAG;

/**
 * Привязанная служба, на основе использования расширенного класса Binder
 */
public class Service2 extends Service {
  //Binder, предоставляемый клиенту
  private final IBinder mBinder = new LocalBinder();

  private final Random r = new Random();

  private String sendMessage;
  /*
   * Класс используется для связи с клиентом. Так как служба всегда
   * запускается в том же самом процессе, что и клиент, нам не нужно
   * иметь дело с межпроцессорным взаимодействием.
   */
  public class LocalBinder extends Binder {
    public Service2 getService() {
      /*
       * Возвращает экземпляр данной службы клиенту, который может
       * вызвать его public-методы
       */
      return Service2.this;
    }
  }

  @Override
  public IBinder onBind(Intent intent) {
    sendMessage = intent.getStringExtra("message");
    Log.i(TAG, "sendMessage = " + sendMessage);
    return mBinder;
  }

  //метод, который может вызвать клиент
  public Integer getRandomNumber() {
    return r.nextInt();
  }

  public String getMessage() {
    return sendMessage;
  }
}
