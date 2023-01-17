package www.iesmurgi.u7_proyecto2_datepickerdialog

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val c = Calendar.getInstance()
        /*val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)*/


        findViewById<Button>(R.id.btnFecha).setOnClickListener {
            val dpd = DatePickerDialog(this, { _, year, monthOfYear, dayOfMonth ->
                c[Calendar.YEAR] = year
                c[Calendar.MONTH] = monthOfYear
                c[Calendar.DAY_OF_MONTH] = dayOfMonth
                findViewById<TextView>(R.id.tvFecha).text = "" + dayOfMonth + " / " + monthOfYear+1+ " / " + year
            }, c[Calendar.YEAR], c[Calendar.MONTH], c[Calendar.DAY_OF_MONTH])

            //con esta linea no se puede seleccionar una fecha que sea anterior al dia de hoy
            c.add(Calendar.DATE,14)
            dpd.datePicker.minDate = c.timeInMillis
            dpd.show()
        }

        val c2 = Calendar.getInstance()
        findViewById<Button>(R.id.btnFechaNac).setOnClickListener{
            val dpd = DatePickerDialog(this, { _, year, monthOfYear, dayOfMonth ->
                c[Calendar.YEAR] = year
                c[Calendar.MONTH] = monthOfYear
                c[Calendar.DAY_OF_MONTH] = dayOfMonth
                findViewById<TextView>(R.id.tvFecha).text = "" + dayOfMonth + " / " + monthOfYear+1+ " / " + year
            },c2[Calendar.YEAR], c2[Calendar.MONTH], c2[Calendar.DAY_OF_MONTH])

            dpd.datePicker.maxDate = System.currentTimeMillis()
            dpd.show()
        }

        val c3 = Calendar.getInstance()
        findViewById<Button>(R.id.button2).setOnClickListener {
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                c3.set(Calendar.HOUR_OF_DAY, hour)
                c3.set(Calendar.MINUTE, minute)

                findViewById<TextView>(R.id.tvFecha).text = "" + hour + " : " +minute
            }
            TimePickerDialog(this, timeSetListener, c2.get(Calendar.HOUR_OF_DAY), c2.get(Calendar.MINUTE), true).show()

        }
    }
}