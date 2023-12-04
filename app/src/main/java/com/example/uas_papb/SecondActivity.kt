package com.example.uas_papb

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.uas_papb.databinding.ActivitySecondBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    lateinit var mAuth: FirebaseAuth
    lateinit var progressBar : ProgressBar

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.currentUser
        if (currentUser != null) {
            val intent = Intent(this@SecondActivity, UserActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySecondBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mAuth = FirebaseAuth.getInstance()
        progressBar = binding.progressBar

        with(binding){

            registerBtn.setOnClickListener {
                progressBar.visibility = View.VISIBLE

                val email = binding.Email2.text.toString()
                val password = binding.password2.text.toString()

                if (email.isEmpty()) {
                    Toast.makeText(this@SecondActivity, "Enter email", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (password.isEmpty()) {
                    Toast.makeText(this@SecondActivity, "Enter password", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(
                        OnCompleteListener<AuthResult?> { task ->
                            progressBar.visibility = View.GONE
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information4
                                Toast.makeText(this@SecondActivity, "Account Created", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@SecondActivity,MainActivity::class.java)
                                startActivity(intent)

                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(this@SecondActivity, "Authentication failed.", Toast.LENGTH_SHORT).show()
                            }
                        })
            }

            LoginPortal2.setOnClickListener{
                val intent = Intent(this@SecondActivity, MainActivity::class.java)
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
        }
    }
}