package com.example.uts_zefanyadiegoforlandicco_505523


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import com.example.uts_zefanyadiegoforlandicco_505523.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Mengatur opsi untuk menampilkan atau menyembunyikan password saat centang "Show Password" diubah
        binding.cbShowPassword.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                binding.etLoginPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                binding.etLoginPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }

        // ...
        binding.btnLogin.setOnClickListener {
            val email = binding.etLoginEmail.text.toString()
            val password = binding.etLoginPassword.text.toString()

            // Mengakses data pengguna dari SharedPreferences
            val sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)
            val registeredEmail = sharedPreferences.getString("email", "")
            val registeredPassword = sharedPreferences.getString("password", "")

            if (email == registeredEmail && password == registeredPassword) {
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Login gagal. Email atau password tidak valid.", Toast.LENGTH_SHORT).show()
            }
        }
        // ...
    }
}
