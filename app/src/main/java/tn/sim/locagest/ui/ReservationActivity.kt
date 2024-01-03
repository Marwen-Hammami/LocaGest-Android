package tn.sim.locagest.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import tn.sim.locagest.MainActivityReservation
import tn.sim.locagest.models.Reservation
import tn.sim.locagest.R
import tn.sim.locagest.databinding.ActivityReservationBinding
import tn.sim.locagest.viewmodel.HistoriqueViewModel
import tn.sim.locagest.viewmodel.ReservationViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ReservationActivity : Fragment() {

    //For Subjects ViwModel
    lateinit var historiqueViewModel: HistoriqueViewModel

    private lateinit var binding: ActivityReservationBinding

    private lateinit var btnStartDate: Button
    private lateinit var btnRes: Button
    private lateinit var btnStartTime: Button
    private lateinit var btnEndDate: Button
    private lateinit var btnEndTime: Button
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioHourlyRate: RadioButton
    private lateinit var radioDailyRate: RadioButton
    lateinit var  startDate:String
    lateinit var  startTime:String
    lateinit var  endDate:String
    lateinit var  endTime:String

    lateinit var reservationViewModel: ReservationViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityReservationBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        historiqueViewModel = ViewModelProvider(this)[HistoriqueViewModel::class.java]
        reservationViewModel = ViewModelProvider(this)[ReservationViewModel::class.java]

        btnStartDate = binding.btnStartDate
        btnStartTime = binding.btnStartTime
        btnEndDate = binding.btnEndDate
        btnEndTime = binding.btnEndTime
        radioGroup = binding.radioGroup
        radioHourlyRate = binding.radioHourlyRate
        radioDailyRate = binding.radioDailyRate
        btnRes = binding.btnReserve


        // Remplir les champs TextView avec des valeurs statiques
//        tvStartDate.text = "2023-11-16T13:45:00.000Z"
//        tvStartTime.text = "13:45:00.000Z"
//        tvEndDate.text = "2023-11-17T14:30:00.000Z"
//        tvEndTime.text = "14:30:00.000Z"




        btnStartDate.setOnClickListener { view ->
            showDatePickerDialog(btnStartDate)
        }

        btnStartTime.setOnClickListener { view ->
            showTimePickerDialog(btnStartTime)
        }

        btnEndDate.setOnClickListener { view ->
            showDatePickerDialog(btnEndDate)
        }

        btnEndTime.setOnClickListener { view ->
            showTimePickerDialog(btnEndTime)
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
            val res = Reservation(
                "jou12345678",
                startDate,
                endDate,
                startTime,
                endTime,
                30F
            )
            reservationViewModel.createReservation(res)
            Toast.makeText(requireContext(),"the reservation has been added" ,Toast.LENGTH_LONG).show()
            gotToHistoryListView()
        }
    }

    private fun gotToHistoryListView(){
        (activity as MainActivityReservation).binding.bottomNavigationView.selectedItemId = R.id.action_historique
    }

    private fun showDatePickerDialog(selectedButton: Button) {
        val builder = MaterialDatePicker.Builder.datePicker()
        val picker = builder.build()

        picker.addOnPositiveButtonClickListener { selection ->
            Log.w("Selected val", selection.toString())
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = selection

          //  val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date = dateFormat.format(calendar.time)

            if(selectedButton == btnStartDate){
                startDate = date
                btnStartDate.text =
                    "Date de début sélectionnée : $date" // Mettre à jour le TextView avec la date sélectionnée
                Log.d("toto", "showDatePickerDialog startDate: $startDate")
            }
            if(selectedButton == btnEndDate){
                endDate = date
                btnEndDate.text =
                    "Date de fin sélectionnée : $date" // Mettre à jour le TextView avec la date sélectionnée
                Log.d("toto", "showDatePickerDialog endDate: $endDate")
            }

            Log.d("DatePicker", "Date sélectionnée : $date")
        }

        fragmentManager?.let { picker.show(it, picker.toString()) }
    }

    private fun showTimePickerDialog(selectedButton: Button) {
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

            if(selectedButton ==btnStartTime){
                startTime = formattedTime
                btnStartTime.text =
                    "Heure de début sélectionnée : $formattedTime" // Mettre à jour le TextView avec l'heure sélectionnée
                Log.d("toto", "showTimePickerDialog: startTime $startTime")
            }
            if(selectedButton == btnEndTime){
                endTime = formattedTime
                btnEndTime.text =
                    "Heure de fin sélectionnée : $formattedTime" // Mettre à jour le TextView avec l'heure sélectionnée
                Log.d("toto", "showTimePickerDialog: endTime $endTime")

            }
            Log.d("TimePicker", "Heure sélectionnée : $formattedTime")
        }

        fragmentManager?.let { picker.show(it, picker.toString()) }
    }

}