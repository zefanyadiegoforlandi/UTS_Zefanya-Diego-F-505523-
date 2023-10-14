package com.example.uts_zefanyadiegoforlandicco_505523


import android.content.Intent

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import com.example.uts_zefanyadiegoforlandicco_505523.databinding.ActivityTujuanBinding

class TujuanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTujuanBinding
    private var hargaAwal = 0
    private val toggleButtonListeners = mutableMapOf<ToggleButton, Boolean>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTujuanBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val toggleButtons = listOf(
            binding.toggleButton1, binding.toggleButton2, binding.toggleButton3,
            binding.toggleButton4, binding.toggleButton5, binding.toggleButton6,
            binding.toggleButton7
        )

        for (toggleButton in toggleButtons) {
            toggleButton.setOnCheckedChangeListener { buttonView, isChecked ->
                toggleButtonListeners[toggleButton] = isChecked
                updateHargaTotal()
            }
        }

        // Inisialisasi spinner stasiun asal
        val stasiunAsalOptions = arrayOf("Stasiun Jatinegara(Jakarta)", "Stasiun Solo Balapan(Solo)", "Stasiun Tugu(Yogyakarta)")
        val adapterStasiunAsal = ArrayAdapter(this, android.R.layout.simple_spinner_item, stasiunAsalOptions)
        adapterStasiunAsal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerStasiunAsal.adapter = adapterStasiunAsal

        // Inisialisasi spinner stasiun tujuan
        val stasiunTujuanOptions = arrayOf("Stasiun Solo Balapan(Solo)", "Stasiun Jatinegara(Jakarta)", "Stasiun Tugu(Yogyakarta)")
        val adapterStasiunTujuan = ArrayAdapter(this, android.R.layout.simple_spinner_item, stasiunTujuanOptions)
        adapterStasiunTujuan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerStasiunTujuan.adapter = adapterStasiunTujuan

        // Inisialisasi spinner kelas kereta
        val kelasKeretaOptions = arrayOf("Ekonomi", "Bisnis")
        val adapterKelas = ArrayAdapter(this, android.R.layout.simple_spinner_item, kelasKeretaOptions)
        adapterKelas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerKelas.adapter = adapterKelas


        // Membuat listener untuk Spinner
        val spinners = listOf(binding.spinnerStasiunAsal, binding.spinnerStasiunTujuan, binding.spinnerKelas)
        for (spinner in spinners) {
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    updateHargaTotal() // Panggil updateHargaTotal() di sini
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        }

        // Inisialisasi button pesan

    }

    // Fungsi untuk menghitung harga berdasarkan stasiun asal, stasiun tujuan, dan kelas
    private fun calculateHarga(): Int {

        val selectedAsal = binding.spinnerStasiunAsal.selectedItem.toString()
        val selectedTujuan = binding.spinnerStasiunTujuan.selectedItem.toString()
        val selectedKelas = binding.spinnerKelas.selectedItem.toString()

        var hargaSpinner = 0
        var hargaToggle = 0

        // Logika perhitungan harga Spinner (sesuaikan dengan kebutuhan Anda)
        if (selectedAsal == "Stasiun Jatinegara(Jakarta)" && selectedTujuan == "Stasiun Solo Balapan(Solo)") {
            if (selectedKelas == "Ekonomi") {
                hargaSpinner = 90000
            } else if (selectedKelas == "Bisnis") {
                hargaSpinner = 150000
            }
        } else if (selectedAsal == "Stasiun Jatinegara(Jakarta)" && selectedTujuan == "Stasiun Tugu(Yogyakarta)") {
            if (selectedKelas == "Ekonomi") {
                hargaSpinner = 120000
            } else if (selectedKelas == "Bisnis") {
                hargaSpinner = 170000
            }
        }
        if (selectedAsal == "Stasiun Solo Balapan(Solo)" && selectedTujuan == "Stasiun Jatinegara(Jakarta)") {
            if (selectedKelas == "Ekonomi") {
                hargaSpinner = 90000
            } else if (selectedKelas == "Bisnis") {
                hargaSpinner = 150000
            }
        } else if (selectedAsal == "Stasiun Solo Balapan(Solo)" && selectedTujuan == "Stasiun Tugu(Yogyakarta)") {
            if (selectedKelas == "Ekonomi") {
                hargaSpinner = 50000
            } else if (selectedKelas == "Bisnis") {
                hargaSpinner = 75000
            }
        }
        if (selectedAsal == "Stasiun Tugu(Yogyakarta)" && selectedTujuan == "Stasiun Solo Balapan(Solo)") {
            if (selectedKelas == "Ekonomi") {
                hargaSpinner = 50000
            } else if (selectedKelas == "Bisnis") {
                hargaSpinner = 75000
            }
        } else if (selectedAsal == "Stasiun Tugu(Yogyakarta)" && selectedTujuan == "Stasiun Jatinegara(Jakarta)") {
            if (selectedKelas == "Ekonomi") {
                hargaSpinner = 120000
            } else if (selectedKelas == "Bisnis") {
                hargaSpinner = 170000
            }
        }
        // Tambahkan lebih banyak logika perhitungan harga sesuai kebutuhan Anda
        return hargaSpinner
    }



    // Fungsi untuk memperbarui harga total
    // Fungsi untuk memperbarui harga total
    // Fungsi untuk memperbarui harga total
    fun updateHargaTotal() {
        // Membuat listener untuk toggle buttons
        val toggleButtons = listOf(
            binding.toggleButton1, binding.toggleButton2, binding.toggleButton3,
            binding.toggleButton4, binding.toggleButton5, binding.toggleButton6,
            binding.toggleButton7
        )

        for (toggleButton in toggleButtons) {
            toggleButton.setOnCheckedChangeListener { buttonView, isChecked ->
                toggleButtonListeners[toggleButton] = isChecked
                updateHargaTotal()
            }
        }

        // Hitung harga dari spinner
        val hargaSpinner = calculateHarga()

        // Hitung harga dari toggle buttons yang dipilih
        val hargaToggle = toggleButtons
            .filter { toggleButtonListeners[it] == true }
            .sumBy { hitungHargaToggle(it) }

        // Menggabungkan harga dari spinner dan toggle buttons
        val totalHarga = hargaSpinner + hargaToggle

        // Menampilkan total harga
        binding.textViewTotalHarga.text = "Total Harga: Rp $totalHarga"
        val selectedAsal = binding.spinnerStasiunAsal.selectedItem.toString()
        val selectedTujuan = binding.spinnerStasiunTujuan.selectedItem.toString()


        binding.btnPesan.setOnClickListener {
            if (selectedAsal == selectedTujuan) {
                // Stasiun asal dan tujuan sama, tampilkan pesan kesalahan dengan Toast
                showStasiunSamaError()
            } else {
                val datePicker = findViewById<DatePicker>(R.id.datePickertujuan)
                val year = datePicker.year
                val month = datePicker.month
                val dayOfMonth = datePicker.dayOfMonth

                val intent = Intent(this, DashboardActivity::class.java)
                intent.putExtra("tanggal", "$year-${month + 1}-$dayOfMonth")
                intent.putExtra("asal", binding.spinnerStasiunAsal.selectedItem.toString())
                intent.putExtra("tujuan", binding.spinnerStasiunTujuan.selectedItem.toString())
                intent.putExtra("kelas_kereta", binding.spinnerKelas.selectedItem.toString())
                intent.putExtra("total_pembayaran", "Rp ${totalHarga}")


                val selectedPaketString = toggleButtons
                    .filter { toggleButtonListeners[it] == true }
                    .joinToString(", ") { it.text.toString() }
                intent.putExtra("paket", selectedPaketString)
                startActivity(intent)

            }
        }
    }
    fun showStasiunSamaError() {
        Toast.makeText(this, "Stasiun asal dan tujuan tidak boleh sama!", Toast.LENGTH_SHORT).show()
    }

    // Fungsi untuk menghitung harga dari toggle button tertentu
    private fun hitungHargaToggle(toggleButton: ToggleButton): Int {
        // Gantilah dengan logika perhitungan harga untuk setiap toggle button
        return when (toggleButton.id) {
            R.id.toggleButton1 -> if (toggleButton.isChecked) 50000 else 0 // Harga Paket Hotel
            R.id.toggleButton2 -> if (toggleButton.isChecked) 25000 else 0  // Harga Paket Jasa Porter
            R.id.toggleButton3 -> if (toggleButton.isChecked) 20000 else 0  // Harga Paket Extra Bagasi
            R.id.toggleButton4 -> if (toggleButton.isChecked) 5000 else 0  // Harga Paket Minum
            R.id.toggleButton5 -> if (toggleButton.isChecked) 10000 else 0  // Harga Paket Makan 1x
            R.id.toggleButton6 -> if (toggleButton.isChecked) 15000 else 0  // Harga Paket Makan 2x
            R.id.toggleButton7 -> if (toggleButton.isChecked) 5000 else 0  // Harga Paket Snack
            // Tambahkan harga untuk toggle buttons lainnya
            else -> 0 // Harga default jika toggle button tidak dikenali
        }
    }


}