package com.example.uts_zefanyadiegoforlandicco_505523

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.DatePicker
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val datePicker = findViewById<DatePicker>(R.id.datePicker)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        btnRegister.setOnClickListener {
            val username = etUsername.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            val selectedYear = datePicker.year
            val selectedMonth = datePicker.month
            val selectedDay = datePicker.dayOfMonth

            // Menghitung usia berdasarkan tanggal lahir yang dipilih
            val calendarToday = Calendar.getInstance()
            val calendarBirth = Calendar.getInstance()
            calendarBirth.set(selectedYear, selectedMonth, selectedDay)
            val age = calendarToday.get(Calendar.YEAR) - calendarBirth.get(Calendar.YEAR)

            data class User(val username: String, val email: String, val password: String)
            // Syarat: Minimal usia 15 tahun
            if (age >= 15) {

                // Melakukan register
                // Setelah berhasil mendaftar, Anda dapat mengalihkan pengguna ke halaman lain
                val loginIntent = Intent(this, LoginActivity::class.java)
                val sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("username", username)
                editor.putString("email", email)
                editor.putString("password", password)
                editor.apply() // Mengirim data pendaftaran ke LoginActivity
                startActivity(loginIntent)

            } else {
//                Jika usia kurang dari 15 tahun, tampilkan pesan kesalahan
            Toast.makeText(this, "Anda harus berusia minimal 15 tahun untuk mendaftar.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
