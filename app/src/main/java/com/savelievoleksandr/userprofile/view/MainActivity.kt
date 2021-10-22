package com.savelievoleksandr.userprofile.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import com.savelievoleksandr.userprofile.R
import com.savelievoleksandr.userprofile.viewModel.UserViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val user0: LinearLayout = findViewById(R.id.layout0)
        val user1: LinearLayout = findViewById(R.id.layout1)
        val user2: LinearLayout = findViewById(R.id.layout2)
        val user3: LinearLayout = findViewById(R.id.layout3)
        val user4: LinearLayout = findViewById(R.id.layout4)
        val user5: LinearLayout = findViewById(R.id.layout5)
        val user6: LinearLayout = findViewById(R.id.layout6)
        viewModel.loadUserData()

        user0.setOnClickListener { onClick(0) }
        user1.setOnClickListener { onClick(1) }
        user2.setOnClickListener { onClick(2) }
        user3.setOnClickListener { onClick(3) }
        user4.setOnClickListener { onClick(4) }
        user5.setOnClickListener { onClick(5) }
        user6.setOnClickListener { onClick(6) }
    }

    private fun onClick(index: Int) {
        val intent = Intent(this, DetailedUserActivity::class.java)
        intent.putExtra("id", index)
        startActivity(intent)
    }
}