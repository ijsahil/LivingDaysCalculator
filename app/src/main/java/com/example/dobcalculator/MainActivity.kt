package com.example.dobcalculator

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private var displayDate: TextView? = null
    private var displayDay: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dateBtn: Button = findViewById(R.id.button1)
        displayDate = findViewById(R.id.tvDisplayDate)
        displayDay = findViewById(R.id.tvDisplayDay)
        dateBtn.setOnClickListener {
            datePicker()
        }
    }

    private fun datePicker() {
        val myCalender = Calendar.getInstance()
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        val month = myCalender.get(Calendar.MONTH)
        val year = myCalender.get(Calendar.YEAR)
        val dFD = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, year, month, day ->
            val selectedDATE = "$day/${month + 1}/$year"
            displayDate?.text = selectedDATE
            Toast.makeText(this, "Live happily", Toast.LENGTH_LONG).show()
            val sdf = SimpleDateFormat("dd/MM/yy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDATE)
            theDate?.let {
                val daysTillSelected = theDate.time / 86400000
                val totalTime = sdf.parse(sdf.format(System.currentTimeMillis()))
                totalTime?.let {
                    val totalDays = totalTime.time / 86400000
                    val daysSpend = totalDays - daysTillSelected
                    displayDay?.text = "$daysSpend days"
                }
            }
        }, day, month, year)
        dFD.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dFD.show()
    }
}