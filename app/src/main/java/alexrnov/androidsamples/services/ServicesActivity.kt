package alexrnov.androidsamples.services

import alexrnov.androidsamples.R
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import alexrnov.androidsamples.Initialization.TAG
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.widget.Toast

class ServicesActivity: AppCompatActivity() {

  private var service2: Service2? = null
  private var mBound = false

  override fun onCreate(bundle: Bundle?) {
    super.onCreate(bundle)
    setContentView(R.layout.services_activity)
  }

  override fun onStart() {
    super.onStart()
    // связать со службой Service2
    val intent = Intent(this, Service2::class.java)
    //BIND_AUTO_CREATE - параметр привязки: создать службу
    // если она еще не выполняется
    bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
  }

  override fun onStop() {
    super.onStop()
    if (mBound) { // открепиться от сервиса
      unbindService(mConnection)
      mBound = false
    }
  }

  fun service1Button(view: View) {
    Log.i(TAG, "service1Button")
    val intent = Intent(this, Service1::class.java)
    intent.putExtra("message", "text message")
    startService(intent)
  }

  // вызвать связанную службу, служба возвращает клиенту случайное число
  fun service2Button(view: View) {
    Log.i(TAG, "service2Button")
    if (mBound) {
      Log.i(TAG, "mBound = true")
      //вызывается public-метод связанной службы. Однако если c этим вызовом
      //было что-то, что могло привести к зависанию(длительной работы метода),
      //тогда этот запрос должен происходить в отдельном потоке, чтобы избежать
      //снижения производительности активити-класса
      val i = service2?.randomNumber
      Toast.makeText(this, "number = $i", Toast.LENGTH_SHORT).show()
    } else {
      Log.i(TAG, "mBound = false")
    }
  }

  //определяет обратный вызов для связанной службы, передаваемый в bindService()
  private val mConnection = object : ServiceConnection {

    /*
     * Система вызывает этот метод, чтобы выдать объект IBinder, возвращенный
     * методом onBind() службы.
     */
    override fun onServiceConnected(className: ComponentName, service: IBinder) {
      //мы получаем связь с Service3, преобразуем интерфейс IBinder в LocalBinder
      //и получаем экземпляр Service3
      val binder = service as Service2.LocalBinder
      service2 = binder.service
      mBound = true
    }

    /*
     * Система Android вызывает этот метод в случае непредвиденной потери
     * подключения к службе, например при сбое в работе службы или в случае
     * ее завершения. Этот метод не вызывается, когда клиент отменяет привязку
     */
    override fun onServiceDisconnected(arg0: ComponentName) {
      mBound = false
    }
  }

}