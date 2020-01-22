package alexrnov.androidsamples.services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import static alexrnov.androidsamples.Initialization.TAG;

/**
 * Класс службы. Если необходимо, чтобы служба поддерживала многопоточность,
 * следует наследовать класс Service для обработки каждого намерения.
 * Таким образом, обработка запросов запуска через рабочую очередь здесь
 * не используется. В отличие от методов обратного вызова жизненного цикла
 * активити-класса, вызывать реализацию суперкласса при переопределении
 * методов обратного вызова для служб не требуется.
 */
public class Service1 extends Service {
  private Looper mServiceLooper;
  private ServiceHandler mServiceHandler;

  //обработчик, который сохраняет сообщение от потока
  private final class ServiceHandler extends Handler {
    public ServiceHandler(Looper looper) {
      super(looper);
    }

    @Override
    public void handleMessage(@NotNull Message msg) {
      //основная работа потока
      int i = 0;
      while (i < 100) {
        synchronized (this) {
          i++;
          System.out.println("i = " + i);
        }
      }
      //остановить службу, используя startId, с тем чтобы мы не останавливали
      //службу в середине обработки другой работы
      stopSelf(msg.arg1);
    }
  }

  /*
   * Система вызывает этот метод при первом создании службы для выполнения
   * однократных процедур настройки (перед вызовом OnStartCommand() или
   * onBind()). Если служба уже запущена, этот метод не вызывается.
   */
  @Override
  public void onCreate() {
    /*
     * запустить поток используя службу. Мы создаем отдельный поток потому, что
     * служба нормально запускается в процессе главного потока, который мы не
     * хотим блокировать. Мы также совершаем это в приоритете фонового режима,
     * чтобы в случае перегруженности ЦП, не разрушался наш пользовательский
     * интерфейс.
     */
    HandlerThread thread = new HandlerThread("ServiceStartArguments",
            Process.THREAD_PRIORITY_BACKGROUND);
    thread.start();
    //получить потоковый петлитель(HandlerThread's Looper) и использовать это
    //с нашим обработчиком
    mServiceLooper = thread.getLooper();
    mServiceHandler = new ServiceHandler(mServiceLooper);
  }

  /* Метод обратного вызова. Система вызывает этот метод, когда другой
   * компонент, например активити-класс, запрашивает запуск этой службы,
   * вызывая метод. Поскольку здесь обрабатывается каждый вызов, можно
   * выполнять сразу несколько запросов одновременно. Т.е. можно создавать
   * новые потоки для каждого запроса и сразу запускать их(не ожидая
   * завершения предыдущего запроса).
   */
  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    // сообщение, переданное сервису
    String message = intent.getStringExtra("message");
    Toast.makeText(this, "Служба запущена: " + message, Toast.LENGTH_SHORT).show();
    //Для каждого запущенного запроса отправить сообщение для старта работы
    //и передачи идентификатора старта, так мы узнаем какой запрос мы
    //останавливаем, когда завершаем работу
    Message msg = mServiceHandler.obtainMessage();
    msg.arg1 = startId;
    mServiceHandler.sendMessage(msg);
    //если система уничтожает службу после возвращения из метода
    //повторно создать службу и вызвать onStartCommand(), но не передавать
    //последнее намерение повторно. Если нет ожидающих намерений для запуска
    //службы, передать намерение со значением null
    return START_STICKY;
  }

  /*
   * Система вызывает этот метод когда другой компонент хочет выполнить
   * привязку к службе.
   */
  @Override
  public IBinder onBind(Intent intent) {
    //мы не обеспечиваем связывание, поэтому возващаем null
    return null;
  }

  /*
   * метод обратного вызова, вызывается системой, когда служба больше
   * не используется и выполняется ее уничтожение. Используется для
   * отчистки ресурсов, таких как потоки, зарегистрированные приемники,
   * ресивиры. Это последний вызов, который получает служба.
   */
  @Override
  public void onDestroy() {
    Toast.makeText(this, "служба завершена", Toast.LENGTH_SHORT).show();
  }
}
