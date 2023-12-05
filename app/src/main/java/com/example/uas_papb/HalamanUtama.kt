package com.example.uas_papb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.uas_papb.databinding.ActivityHalamanUtamaBinding

class HalamanUtama : AppCompatActivity() {
    private lateinit var binding: ActivityHalamanUtamaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHalamanUtamaBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        with(binding){
            bottomNavigation.setOnItemSelectedListener {
                when(it.itemId){
                    R.id.home -> replaceFragment(HomeFragment())
                    R.id.bookmark -> replaceFragment(BookmarkFragment())
                    R.id.profile -> replaceFragment(ProfileFragment())

                    else->{

                    }
                }
                true
            }
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }

}