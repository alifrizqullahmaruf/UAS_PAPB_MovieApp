package com.example.uas_papb

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.example.uas_papb.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var auth: FirebaseAuth
    lateinit var progressBar : ProgressBar

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(this@MainActivity, UserActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        progressBar = binding.progressBar

        with(binding){
            // Pergi ke page Register
            RegisterPortal2.setOnClickListener {
                val intent = Intent(this@MainActivity,SecondActivity::class.java)
                startActivity(intent)
            }

            // Icon insta
            instaIcon.setOnClickListener{
                val insta = "https://instagram.com/"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(insta))
                startActivity(intent)
            }
            // Icon insta
            instaIcon.setOnClickListener{
                val google = "https://www.google.com/"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(google))
                startActivity(intent)
            }
            // Icon insta
            instaIcon.setOnClickListener{
                val facebook = "https://id-id.facebook.com/"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(facebook))
                startActivity(intent)
            }

            loginBtn.setOnClickListener {
                progressBar.visibility = View.VISIBLE

                val email = binding.Email2.text.toString()
                val password = binding.password2.text.toString()

                if (email.isEmpty()) {
                    Toast.makeText(this@MainActivity, "Enter email", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (password.isEmpty()) {
                    Toast.makeText(this@MainActivity, "Enter password", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener() { task ->
                        progressBar.visibility = View.GONE
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(this@MainActivity, "Authentication Success.", Toast.LENGTH_SHORT,).show()
                            val intent = Intent(this@MainActivity, UserActivity::class.java)
                            startActivity(intent)
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(this@MainActivity, "Authentication failed.", Toast.LENGTH_SHORT,).show()
                        }
                    }
            }

        }

    }
}