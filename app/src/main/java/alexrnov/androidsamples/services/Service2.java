package alexrnov.androidsamples.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

/**
 * Привязанная служба, на основе использования расширенного класса Binder
 */
public class Service2 extends Service {
  //Binder, предоставляемый клиенту
  private final IBinder mBinder = new LocalBinder();

  private final Random r = new Random();

  /*
   * Класс используется для связи с клиентом. Так как служба всегда
   * запускается в том же самом процессе, что и клиент, нам не нужно
   * иметь дело с межпроцессорным взаимодействием.
   */
  public class LocalBinder extends Binder {
    Service2 getService() {
      /*
       * Возвращает экземпляр данной службы клиенту, который может
       * вызвать его public-методы
       */
      return Service2.this;
    }
  }

  @Override
  public IBinder onBind(Intent intent) {
    return mBinder;
  }

  //метод, который может вызвать клиент
  public Integer getRandomNumber() {
    return r.nextInt();
  }
}
