package com.savelievoleksandr.userprofile.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.savelievoleksandr.userprofile.Adapter
import com.savelievoleksandr.userprofile.OnUserClick
import com.savelievoleksandr.userprofile.databinding.ActivityMainBinding
import com.savelievoleksandr.userprofile.viewModel.UserViewModel


class MainActivity : GeneralBinding<ActivityMainBinding>(ActivityMainBinding::inflate) , OnUserClick {
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val recyclerView: RecyclerView = binding.recyclerView
        val adapter = Adapter(this as OnUserClick)
        recyclerView.adapter = adapter
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        viewModel.getAllLivaData().observe(this) {
            it?.let {
                adapter.submitList(it)
            }
        }

        val create: View = binding.createBtn
        create.setOnClickListener { view ->
            val intent1 = Intent(this, AddUserProfileActivity::class.java)
            startActivity(intent1)
        }
    }

    override fun onClick(userId: Int) {
        val intent = Intent(this, DetailedUserActivity::class.java)
        intent.putExtra("id", userId + 1)
        startActivity(intent)
    }
}