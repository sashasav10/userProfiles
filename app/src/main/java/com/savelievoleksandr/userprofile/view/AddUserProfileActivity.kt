package com.savelievoleksandr.userprofile.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.savelievoleksandr.userprofile.databinding.ActivityAddProfileBinding
import com.savelievoleksandr.userprofile.model.User
import com.savelievoleksandr.userprofile.viewModel.AddUserProfileViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class AddUserProfileActivity : AppCompatActivity() {
    private lateinit var viewModel: AddUserProfileViewModel
    private lateinit var binding: ActivityAddProfileBinding
    private var index by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(AddUserProfileViewModel::class.java)
        val index = viewModel.size
        val nameEditText: EditText = binding.nameEditText
        val lastSeenEditText: EditText = binding.lastSeenEditText
        val postsEditText: EditText = binding.postsEditText
        val followersEditText: EditText = binding.followersEditText
        val followingEditText: EditText = binding.followingEditText
        val bioEditText: EditText = binding.bioEditText
        val phoneEditText: EditText = binding.phoneEditText
        val emailEditText: EditText = binding.emailEditText
        val photoEditText: EditText = binding.photoEditText

        val saveChangesBtn: Button = binding.saveChangesBtn

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