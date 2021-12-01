package com.savelievoleksandr.userprofile.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModelProvider
import com.savelievoleksandr.userprofile.R
import com.savelievoleksandr.userprofile.viewModel.UserViewModel




class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val userLayoutList = listOf<LinearLayout>(
            findViewById(R.id.layout0),
            findViewById(R.id.layout1),
            findViewById(R.id.layout2),
            findViewById(R.id.layout3),
            findViewById(R.id.layout4),
            findViewById(R.id.layout5),
            findViewById(R.id.layout6)
        )
        val userPhotoList = listOf<ImageView>(
            findViewById(R.id.userPhoto1),
            findViewById(R.id.userPhoto2),
            findViewById(R.id.userPhoto3),
            findViewById(R.id.userPhoto4),
            findViewById(R.id.userPhoto5),
            findViewById(R.id.userPhoto6),
            findViewById(R.id.userPhoto7)
        )
        val userNameList = listOf<TextView>(
            findViewById(R.id.userName1),
            findViewById(R.id.userName2),
            findViewById(R.id.userName3),
            findViewById(R.id.userName4),
            findViewById(R.id.userName5),
            findViewById(R.id.userName6),
            findViewById(R.id.userName7)
        )
        val userLastSeenList = listOf<TextView>(
            findViewById(R.id.lastSeen1),
            findViewById(R.id.lastSeen2),
            findViewById(R.id.lastSeen3),
            findViewById(R.id.lastSeen4),
            findViewById(R.id.lastSeen5),
            findViewById(R.id.lastSeen6),
            findViewById(R.id.lastSeen7)
        )
        viewModel.insert()
        viewModel.loadUserData()
        viewModel.userLiveData.observe(this, Observer {
            for (id in userLayoutList.indices) {
                userLayoutList[id].setOnClickListener { onClick(id) }
                userNameList[id].text = it[id].name
                userLastSeenList[id].text = it[id].lastSeen
                userPhotoList[id].setImageDrawable(
                    getDrawable(
                        resources.getIdentifier(
                            it[id].photo,
                            null,
                            packageName
                        )
                    )
                )
            }
        })

    }

    private fun onClick(index: Int) {
        val intent = Intent(this, DetailedUserActivity::class.java)
        intent.putExtra("id", index)
        startActivity(intent)
    }
}