package www.iesmurgi.u7_proyecto2_datepickerdialog

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.app.NotificationCompat
import www.iesmurgi.u7_r2_notificaciones_citas.RangeTimePickerDialog
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val c = Calendar.getInstance()                                                                              //(1)
        findViewById<Button>(R.id.btnFecha).setOnClickListener {                                                    //(2)
            val dpd = DatePickerDialog(this, { _, year, monthOfYear, dayOfMonth ->                           //(3)
                c[Calendar.YEAR] = year                                                                             //(4)
                c[Calendar.MONTH] = monthOfYear                                                                     //(5)
                c[Calendar.DAY_OF_MONTH] = dayOfMonth                                                               //(6)
                findViewById<TextView>(R.id.tvFecha).text = "" + dayOfMonth + " / " + monthOfYear+1+ " / " + year   //(7)
            }, c[Calendar.YEAR], c[Calendar.MONTH], c[Calendar.DAY_OF_MONTH])                                       //(8)

            //con esta linea no se puede seleccionar una fecha que sea anterior al dia de hoy
            c.add(Calendar.WEEK_OF_MONTH,2)                                                                                 //(9)
            dpd.datePicker.minDate = c.timeInMillis                                                                 //(10)
            dpd.show()}                                                                                             //(11)

        val c2 = Calendar.getInstance()                                                                             //(1)
        findViewById<Button>(R.id.btnFechaNac).setOnClickListener{                                                  //(2)
            val dpd = DatePickerDialog(this, { _ , year , monthOfYear , dayOfMonth ->                           //(3)
                c[Calendar.YEAR] = year                                                                             //(4)
                c[Calendar.MONTH] = monthOfYear                                                                     //(5)
                c[Calendar.DAY_OF_MONTH] = dayOfMonth                                                               //(6)
                findViewById<TextView>(R.id.tvFecha).text = "" + dayOfMonth + " / " + monthOfYear+1+ " / " + year   //(7)
            },c2[Calendar.YEAR], c2[Calendar.MONTH], c2[Calendar.DAY_OF_MONTH])                                     //(8)
                                                                                                                    //(9)
            dpd.datePicker.maxDate = System.currentTimeMillis()                                                     //(10)
            dpd.show()}                                                                                             //(11)

        val c3 = Calendar.getInstance()
        findViewById<Button>(R.id.button2).setOnClickListener {                                                     //(1)
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->                  //(2)
                c3.set(Calendar.HOUR_OF_DAY, hour)                                                                  //(3)
                c3.set(Calendar.MINUTE, minute)                                                                     //(4)

                findViewById<TextView>(R.id.tvFecha).text = SimpleDateFormat("HH:mm").format(c3.time)        //(5)
            }
            val rangeTimePickerDialog =                                                                             //(6)
                RangeTimePickerDialog(
                    this,
                    timeSetListener,
                    c3.get(Calendar.HOUR_OF_DAY),
                    c3.get(Calendar.MINUTE),
                    true)
            rangeTimePickerDialog.setMin(c3.get(Calendar.HOUR_OF_DAY),c3.get(Calendar.MINUTE))                      //(7)
            rangeTimePickerDialog.show()                                                                            //(8)
        }

        findViewById<Button>(R.id.btnNotificacion).setOnClickListener {                                                 //(1)
            val intent = Intent(this, MainActivity::class.java).apply {                                    //(2)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK                                //(3)
            }

            val channelId = "channelId"                                                                                 //(5)
            val notificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager        //(6)
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)                    //(4)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(channelId, "Channel name", NotificationManager.IMPORTANCE_HIGH) //(7)
                channel.description = "Channel description"                                                             //(8)
                channel.enableLights(true)                                                                        //(9)
                channel.lightColor = Color.RED                                                                          //(10)
                channel.enableVibration(true)                                                                   //(11)
                notificationManager.createNotificationChannel(channel)                                                  //(12)
            }

            val builder = NotificationCompat.Builder(this, channelId)                                           //(13)
                .setContentTitle(title)                                                                                //(14)
                .setSmallIcon(R.drawable.ic_launcher_background)                                                       //(15)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher_foreground))         //(16)
                .setContentText("Notificacion de prueba ")                                                             //(17)
                .setPriority(NotificationCompat.PRIORITY_HIGH)                                                         //(18)
                .setContentIntent(pendingIntent)                                                                       //(19)
                .setAutoCancel(true)                                                                                   //(20)
            notificationManager.notify(0, builder.build())                                                          //(21)
        }
    }
}