package com.savelievoleksandr.userprofile.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.savelievoleksandr.userprofile.databinding.ActivityEditProfileBinding
import com.savelievoleksandr.userprofile.model.User
import com.savelievoleksandr.userprofile.viewModel.EditProfileViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EditProfileActivity :
    GeneralBinding<ActivityEditProfileBinding>(ActivityEditProfileBinding::inflate) {
    private lateinit var viewModel: EditProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[EditProfileViewModel::class.java]
        val arguments = intent.extras
        val index: Int = arguments?.getInt("id")!!.toInt()
        val nameEditText: EditText = binding.nameEditText
        val lastSeenEditText: EditText = binding.lastSeenEditText
        val postsEditText: EditText = binding.postsEditText
        val followersEditText: EditText = binding.followersEditText
        val followingEditText: EditText = binding.followingEditText
        val bioEditText: EditText = binding.bioEditText
        val phoneEditText: EditText = binding.phoneEditText
        val emailEditText: EditText = binding.emailEditText
        val photoEditText: EditText = binding.photoEditText
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
            photoEditText.setText(it.photo)
        })
        val saveChangesBtn: Button = binding.saveChangesBtn

        saveChangesBtn.setOnClickListener {
            if (viewModel.doesListContainOnlyNotEmptyNumbersBiggerThanZero(
                    listOf(
                        postsEditText.text.toString(),
                        followersEditText.text.toString(),
                        followingEditText.text.toString()
                    )
                ) ||
                viewModel.DoesListContainNotNullValues(
                    listOf(
                        nameEditText.text.toString(),
                        lastSeenEditText.text.toString(),
                        bioEditText.text.toString(),
                        phoneEditText.text.toString(),
                        emailEditText.text.toString(),
                        photoEditText.text.toString()
                    )
                )
            ) {
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
                    viewModel.updateUserInfo(user)
                }
                val intent = Intent(this, MainActivity::class.java)
                this.finish()
                startActivity(intent)
            } else Toast.makeText(
                this,
                "Incorrect input. Check all string. Empty strings or values<0 are not allowed",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}