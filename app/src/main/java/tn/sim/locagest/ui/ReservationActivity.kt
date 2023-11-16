package tn.sim.locagest.ui


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import tn.sim.locagest.Model.Historique
import tn.sim.locagest.R
import tn.sim.locagest.viewmodel.HistoriqueViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class ReservationActivity : AppCompatActivity() {

    //For Subjects ViwModel
    lateinit var historiqueViewModel: HistoriqueViewModel

    private lateinit var btnStartDate: Button
    private lateinit var btnRes: Button
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
        setTheme(R.style.Theme_LocaGest) // Appliquer le thème personnalisé
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
        btnRes = findViewById(R.id.btn_reserve)


        // Remplir les champs TextView avec des valeurs statiques
        tvStartDate.text = "2023-11-16T13:45:00.000Z"
        tvStartTime.text = "13:45:00.000Z"
        tvEndDate.text = "2023-11-17T14:30:00.000Z"
        tvEndTime.text = "14:30:00.000Z"


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
                    // Gérer la sélection du tarif horaire
                }
                R.id.radio_daily_rate -> {
                    // Gérer la sélection du tarif journalier
                }
            }
        }

        btnRes.setOnClickListener {
            historiqueViewModel = ViewModelProvider(this).get(HistoriqueViewModel::class.java)

//            var res = Reservation("null", Date(), Date(), "21", "23", StatutRes.Reservee, 21F)
            var his = Historique(2222, Date(), Date(), 21F)
            historiqueViewModel.getHistoriqueList()
        }
    }

    private fun showDatePickerDialog(view: View, textView: TextView) {
        val builder = MaterialDatePicker.Builder.datePicker()
        val picker = builder.build()

        picker.addOnPositiveButtonClickListener { selection ->
            Log.w("Selected val", selection.toString())
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = selection

            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val date = dateFormat.format(calendar.time)
            textView.text = "Date de début sélectionnée : $date" // Mettre à jour le TextView avec la date sélectionnée

            Log.d("DatePicker", "Date sélectionnée : $date")
        }

        picker.show(supportFragmentManager, picker.toString())
    }

    private fun showTimePickerDialog(view: View, textView: TextView) {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setHour(hour)
            .setMinute(minute)
            .setTitleText("Sélectionnez l'heure")
            .build()

        picker.addOnPositiveButtonClickListener {
            val hourOfDay = picker.hour
            val minute = picker.minute

            val formattedTime = "%02d:%02d".format(hourOfDay, minute)
            textView.text = "Heure de début sélectionnée : $formattedTime" // Mettre à jour le TextView avec l'heure sélectionnée

            Log.d("TimePicker", "Heure sélectionnée : $formattedTime")
        }

        picker.show(supportFragmentManager, picker.toString())
    }


}