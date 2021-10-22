package com.savelievoleksandr.userprofile.view


import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.savelievoleksandr.userprofile.R
import com.savelievoleksandr.userprofile.viewModel.UserViewModel

class DetailedUserActivity : AppCompatActivity() {
    private lateinit var viewModel: UserViewModel

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val userPhoto: ImageView = findViewById(R.id.profilePhotoImageView)
        val userName: TextView = findViewById(R.id.userNameTextView)
        val posts: TextView = findViewById(R.id.postsText)
        val followers: TextView = findViewById(R.id.followersText)
        val following: TextView = findViewById(R.id.followingText)
        val bio: TextView = findViewById(R.id.bioText)
        val phone: TextView = findViewById(R.id.phoneText)
        val email: TextView = findViewById(R.id.emailText)

        val arguments = intent.extras
        val index: Int = (arguments?.getInt("id") ?: viewModel.loadUserData()) as Int
        viewModel.loadUserData()
        viewModel.userLiveData.observe(this, {
            userPhoto.setImageDrawable(
                getDrawable(resources.getIdentifier(it.userList[index].photo, null, packageName))
            )
            userName.text = it.userList[index].name
            posts.text = it.userList[index].posts
            followers.text = it.userList[index].followers
            following.text = it.userList[index].following
            bio.text = it.userList[index].bio
            phone.text = it.userList[index].phone
            email.text = it.userList[index].email
        })
    }
}