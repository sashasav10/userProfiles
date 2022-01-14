package com.savelievoleksandr.userprofile.view


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.savelievoleksandr.userprofile.R
import com.savelievoleksandr.userprofile.databinding.ProfileBinding
import com.savelievoleksandr.userprofile.viewModel.DetailedUserViewModel
import kotlin.properties.Delegates

class DetailedUserActivity : AppCompatActivity() {
    private lateinit var viewModel: DetailedUserViewModel
    private lateinit var binding: ProfileBinding
    private var index by Delegates.notNull<Int>()


    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(DetailedUserViewModel::class.java)

        val userPhoto: ImageView = binding.profilePhotoImageView
        val userName: TextView = binding.userNameTextView
        val posts: TextView = binding.postsText
        val followers: TextView = binding.followersText
        val following: TextView = binding.followingText
        val bio: TextView = binding.bioText
        val phone: TextView = binding.phoneText
        val email: TextView = binding.emailText

        val arguments = intent.extras
        index = arguments?.getInt("id")!!.toInt()
        viewModel.loadUserDetailedData(index)
        viewModel.userDetailedLiveData.observe(this, {

            Glide.with(this).load(it.photo).into(userPhoto)
            userName.text = it.name
            posts.text = it.posts
            followers.text = it.followers
            following.text = it.following
            bio.text = it.bio
            phone.text = it.phone
            email.text = it.email
        })

        val editProfileBtn: View = binding.editProfileBtn
        editProfileBtn.setOnClickListener { view ->
            val intent = Intent(this, EditProfileActivity::class.java)
            intent.putExtra("id", index)
            startActivity(intent)
        }
    }
}