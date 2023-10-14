package com.example.uts_zefanyadiegoforlandicco_505523

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {

    private lateinit var calendarView: CalendarView
    private lateinit var tanggalTextView: TextView
    private lateinit var asalTextView: TextView
    private lateinit var tujuanTextView: TextView
    private lateinit var kelasKeretaTextView: TextView
    private lateinit var totalHargaTextView: TextView
    private lateinit var paketPerjalananTextView: TextView
    private lateinit var openTujuanActivityButton: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Inisialisasi komponen UI
        calendarView = findViewById(R.id.calendarView)
        tanggalTextView = findViewById(R.id.tanggal)
        asalTextView = findViewById(R.id.asalTextView)
        tujuanTextView = findViewById(R.id.tujuanTextView)
        kelasKeretaTextView = findViewById(R.id.kelasKeretaTextView)
        totalHargaTextView = findViewById(R.id.totalHargaTextView)
        paketPerjalananTextView = findViewById(R.id.paketPerjalananTextView)
        openTujuanActivityButton = findViewById(R.id.openTujuanActivityButton)

        // Inisialisasi SharedPreferences
        sharedPreferences = getSharedPreferences("TravelData", Context.MODE_PRIVATE)

        // Mendapatkan data terakhir dari SharedPreferences
        val intent = intent
        val tanggal = intent.getStringExtra("tanggal")
        val asal = intent.getStringExtra("asal")
        val tujuan = intent.getStringExtra("tujuan")
        val kelasKereta = intent.getStringExtra("kelas_kereta")
        val totalHarga = intent.getStringExtra("total_pembayaran")
        val paketPerjalanan = intent.getStringExtra("paket")

        // Menampilkan data terakhir pada masing-masing TextView
        tanggalTextView.text = "Tanggal: $tanggal"
        asalTextView.text = "Asal: $asal"
        tujuanTextView.text = "Tujuan: $tujuan"
        kelasKeretaTextView.text = "Kelas Kereta: $kelasKereta"
        totalHargaTextView.text = "Harga: $totalHarga"
        paketPerjalananTextView.text = "Paket: $paketPerjalanan"

        // Menampilkan Toast jika tanggal di klik
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = "$year-${month + 1}-$dayOfMonth"
            if (selectedDate == tanggal) {
                Toast.makeText(this, "Ada Rencana Perjalanan pada tanggal yang sama!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Tidak Ada Rencana Perjalanan pada tanggal ini", Toast.LENGTH_SHORT).show()
            }
        }

        // Membuka TujuanActivity saat tombol diklik
        openTujuanActivityButton.setOnClickListener {
            val intent = Intent(this, TujuanActivity::class.java)
            startActivity(intent)
        }
    }
}
