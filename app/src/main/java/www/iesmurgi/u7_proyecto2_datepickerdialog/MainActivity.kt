package www.iesmurgi.u7_proyecto2_datepickerdialog

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

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
            val dpd = DatePickerDialog(this, { view, year, monthOfYear, dayOfMonth ->
                c[Calendar.YEAR] = year
                c[Calendar.MONTH] = monthOfYear
                c[Calendar.DAY_OF_MONTH] = dayOfMonth
            }, c[Calendar.YEAR], c[Calendar.MONTH], c[Calendar.DAY_OF_MONTH])
            findViewById<TextView>(R.id.tvFecha).text
            //con esta linea no se puede seleccionar una fecha que sea anterior al dia de hoy
            dpd.datePicker.minDate = System.currentTimeMillis() + 1000
            dpd.show()
        }
    }
}