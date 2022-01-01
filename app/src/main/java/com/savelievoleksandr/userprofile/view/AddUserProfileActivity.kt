package com.savelievoleksandr.userprofile.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.savelievoleksandr.userprofile.R
import com.savelievoleksandr.userprofile.model.User
import com.savelievoleksandr.userprofile.viewModel.AddUserProfileViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class AddUserProfileActivity: AppCompatActivity() {
    private lateinit var viewModel: AddUserProfileViewModel
    private var index by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_profile)
        viewModel = ViewModelProvider(this).get(AddUserProfileViewModel::class.java)
        val index = viewModel.size
        val nameEditText: EditText = findViewById(R.id.nameEditText)
        val lastSeenEditText: EditText = findViewById(R.id.lastSeenEditText)
        val postsEditText: EditText = findViewById(R.id.postsEditText)
        val followersEditText: EditText = findViewById(R.id.followersEditText)
        val followingEditText: EditText = findViewById(R.id.followingEditText)
        val bioEditText: EditText = findViewById(R.id.bioEditText)
        val phoneEditText: EditText = findViewById(R.id.phoneEditText)
        val emailEditText: EditText = findViewById(R.id.emailEditText)
        val photoEditText: EditText =findViewById(R.id.photoEditText)

        val saveChangesBtn: Button = findViewById(R.id.saveChangesBtn)

        saveChangesBtn.setOnClickListener {
            val user = User(
                index,
                nameEditText.text.toString(),
                photoEditText.text.toString(),
                lastSeenEditText.text.toString(),
                postsEditText.text.toString(),
                followersEditText.text.toString(),
                followingEditText.text.toString(),
                bioEditText.text.toString(),
                phoneEditText.text.toString(),
                emailEditText.text.toString()
            )
            GlobalScope.launch(Dispatchers.IO) {
                viewModel.insertUser(user)
            }
            val intent = Intent(this, MainActivity::class.java)
            this.finish()
            startActivity(intent)
        }
    }
}