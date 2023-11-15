package tn.sim.locagest.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import tn.sim.locagest.R
import java.util.Calendar

class ReservationActivity : AppCompatActivity() {

    private lateinit var btnStartDate: Button
    private lateinit var btnStartTime: Button
    private lateinit var btnEndDate: Button
    private lateinit var btnEndTime: Button
    private lateinit var tvStartDate: TextView
    private lateinit var tvStartTime: TextView
    private lateinit var tvEndDate: TextView
    private lateinit var tvEndTime: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioHourlyRate: RadioButton
    private lateinit var radioDailyRate: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        btnStartDate = findViewById(R.id.btn_start_date)
        btnStartTime = findViewById(R.id.btn_start_time)
        btnEndDate = findViewById(R.id.btn_end_date)
        btnEndTime = findViewById(R.id.btn_end_time)
        tvStartDate = findViewById(R.id.tv_start_date)
        tvStartTime = findViewById(R.id.tv_start_time)
        tvEndDate = findViewById(R.id.tv_end_date)
        tvEndTime = findViewById(R.id.tv_end_time)
        radioGroup = findViewById(R.id.radio_group)
        radioHourlyRate = findViewById(R.id.radio_hourly_rate)
        radioDailyRate = findViewById(R.id.radio_daily_rate)

        btnStartDate.setOnClickListener { view ->
            showDatePickerDialog(view, tvStartDate)
        }

        btnStartTime.setOnClickListener { view ->
            showTimePickerDialog(view, tvStartTime)
        }

        btnEndDate.setOnClickListener { view ->
            showDatePickerDialog(view, tvEndDate)
        }

        btnEndTime.setOnClickListener { view ->
            showTimePickerDialog(view, tvEndTime)
        }

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_hourly_rate -> {
                    // Handle hourly rate selected
                }
                R.id.radio_daily_rate -> {
                    // Handle daily rate selected
                }
            }
        }
    }

    private fun showDatePickerDialog(view: View, textView: TextView) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, { view, year, monthOfYear, dayOfMonth ->
            val date = "$dayOfMonth/${monthOfYear + 1}/$year"
            textView.text = date
            Log.d("DatePicker", "Date sélectionnée : $date") // Ajoutez cette ligne
        }, year, month, day)

        dpd.show()
    }

    private fun showTimePickerDialog(view: View, textView: TextView) {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val tpd = TimePickerDialog(this, { view, hourOfDay, minute ->
            val time = "$hourOfDay:$minute"
            textView.text = time
            Log.d("TimePicker", "Heure sélectionnée : $time") // Ajoutez cette ligne
        }, hour, minute, true)

        tpd.show()
    }
}
