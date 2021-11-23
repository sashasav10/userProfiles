package com.savelievoleksandr.userprofile.view

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.savelievoleksandr.userprofile.R
import com.savelievoleksandr.userprofile.model.User
import com.savelievoleksandr.userprofile.viewModel.DetailedUserViewModel

class EditProfileActivity : AppCompatActivity() {
    private lateinit var viewModel: DetailedUserViewModel

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_edit_profile)
        viewModel = ViewModelProvider(this).get(DetailedUserViewModel::class.java)
        val arguments = intent.extras
        val index: Int = arguments?.getInt("id")!!.toInt()
        val nameEditText: EditText = findViewById(R.id.nameEditText)
        val lastSeenEditText: EditText = findViewById(R.id.lastSeenEditText)
        val postsEditText: EditText = findViewById(R.id.postsEditText)
        val followersEditText: EditText = findViewById(R.id.followersEditText)
        val followingEditText: EditText = findViewById(R.id.followingEditText)
        val bioEditText: EditText = findViewById(R.id.bioEditText)
        val phoneEditText: EditText = findViewById(R.id.phoneEditText)
        val emailEditText: EditText = findViewById(R.id.emailEditText)
        var photo: String = ""
        viewModel.loadUserDetailedData(index)

        viewModel.userDetailedLiveData.observe(this, Observer {
            nameEditText.setText(it.name)
            lastSeenEditText.setText(it.lastSeen)
            postsEditText.setText(it.posts)
            followersEditText.setText(it.followers)
            followingEditText.setText(it.following)
            bioEditText.setText(it.bio)
            phoneEditText.setText(it.phone)
            emailEditText.setText(it.email)
            photo = it.photo
        })
        val saveChangesBtn: Button = findViewById(R.id.saveChangesBtn)
        saveChangesBtn.setOnClickListener {
            val user: User = User(
                index,
                nameEditText.text.toString(),
                photo,
                lastSeenEditText.text.toString(),
                postsEditText.text.toString(),
                followersEditText.text.toString(),
                followingEditText.text.toString(),
                bioEditText.text.toString(),
                phoneEditText.text.toString(),
                emailEditText.text.toString()
            )
            viewModel.updateUserInfo(user)
            val intent = Intent(this, MainActivity::class.java)
            this.finish()
            startActivity(intent)
        }
    }
}