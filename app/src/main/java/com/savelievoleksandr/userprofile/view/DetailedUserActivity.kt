package com.savelievoleksandr.userprofile.view


import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.savelievoleksandr.userprofile.R
import com.savelievoleksandr.userprofile.viewModel.DetailedUserViewModel

class DetailedUserActivity : AppCompatActivity() {
    private lateinit var viewModel: DetailedUserViewModel

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        viewModel = ViewModelProvider(this).get(DetailedUserViewModel::class.java)
        val userPhoto: ImageView = findViewById(R.id.profilePhotoImageView)
        val userName: TextView = findViewById(R.id.userNameTextView)
        val posts: TextView = findViewById(R.id.postsText)
        val followers: TextView = findViewById(R.id.followersText)
        val following: TextView = findViewById(R.id.followingText)
        val bio: TextView = findViewById(R.id.bioText)
        val phone: TextView = findViewById(R.id.phoneText)
        val email: TextView = findViewById(R.id.emailText)

        val arguments = intent.extras
        val index: Int = arguments?.getInt("id")!!.toInt()
        viewModel.loadUserDetailedData(index)
        viewModel.userDetailedLiveData.observe(this, Observer {
            userPhoto.setImageDrawable(
                getDrawable(resources.getIdentifier(it.photo, null, packageName))
            )
            userName.text = it.name
            posts.text = it.posts
            followers.text = it.followers
            following.text = it.following
            bio.text = it.bio
            phone.text = it.phone
            email.text = it.email
        })
    }
}