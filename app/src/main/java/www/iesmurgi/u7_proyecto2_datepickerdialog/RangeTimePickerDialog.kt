package www.iesmurgi.u7_r2_notificaciones_citas

import android.app.TimePickerDialog
import android.content.Context
import android.widget.TimePicker
import java.text.DateFormat
import java.util.*


class RangeTimePickerDialog(
    context: Context?,
    callBack: OnTimeSetListener?,
    private var currentHour: Int,
    private var currentMinute: Int,
    is24HourView: Boolean
) :
    TimePickerDialog(context, callBack, currentHour, currentMinute, is24HourView) {
    private var minHour = -1                                                                //(1)
    private var minMinute = -1                                                              //(2)
    private var maxHour = 25                                                                //(3)
    private var maxMinute = 25                                                              //(4)
    private val calendar = Calendar.getInstance()                                           //(5)
    private val dateFormat: DateFormat                                                      //(6)

    init {                                                                                  //(1)
        dateFormat = DateFormat.getTimeInstance(DateFormat.SHORT)                           //(2)
        try {
            val superclass: Class<*> = javaClass.superclass                                 //(3)
            val mTimePickerField = superclass.getDeclaredField("mTimePicker")               //(4)
            mTimePickerField.isAccessible = true                                            //(5)
            val mTimePicker = mTimePickerField[this] as TimePicker                          //(6)
            mTimePicker.setOnTimeChangedListener(this)                                      //(7)
        } catch (e: NoSuchFieldException) {
        } catch (e: IllegalArgumentException) {
        } catch (e: IllegalAccessException) {
        }
    }

    fun setMin(hour: Int, minute: Int) {                                                    //(1)
        minHour = hour                                                                      //(2)
        minMinute = minute                                                                  //(3)
    }

    fun setMax(hour: Int, minute: Int) {                                                    //(4)
        maxHour = hour                                                                      //(5)
        maxMinute = minute                                                                  //(6)
    }

    override fun onTimeChanged(view: TimePicker, hourOfDay: Int, minute: Int) {             //(1)
        var validTime = true                                                                //(2)
        if (hourOfDay < minHour || hourOfDay == minHour && minute < minMinute) {            //(3)
            validTime = false                                                               //(4)
        }
        if (hourOfDay > maxHour || hourOfDay == maxHour && minute > maxMinute) {            //(5)
            validTime = false                                                               //(6)
        }
        if (validTime) {                                                                    //(7)
            currentHour = hourOfDay                                                         //(8)
            currentMinute = minute                                                          //(9)
        }
        updateTime(currentHour, currentMinute)                                              //(10)
        updateDialogTitle(view, currentHour, currentMinute)                                 //(11)
    }

    private fun updateDialogTitle(timePicker: TimePicker, hourOfDay: Int, minute: Int) {    //(1)
        calendar[Calendar.HOUR_OF_DAY] = hourOfDay                                          //(2)
        calendar[Calendar.MINUTE] = minute                                                  //(3)
        val title = dateFormat.format(calendar.time)                                        //(4)
        setTitle(title)                                                                     //(5)
    }
}