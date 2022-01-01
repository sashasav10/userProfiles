package com.savelievoleksandr.userprofile.view
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.savelievoleksandr.userprofile.Adapter
import com.savelievoleksandr.userprofile.OnUserClick
import com.savelievoleksandr.userprofile.R
import com.savelievoleksandr.userprofile.viewModel.UserViewModel


class MainActivity : AppCompatActivity(), OnUserClick {
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val adapter = Adapter(this as OnUserClick)
        recyclerView.adapter = adapter
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        viewModel.getAllLivaData().observe(this, {
            it?.let {
                adapter.submitList(it)
            }
        })

        val create: View = findViewById(R.id.createBtn)
        create.setOnClickListener {view->
            val intent1 = Intent(this, AddUserProfileActivity::class.java)
            startActivity(intent1)
        }
    }

    override fun onClick(index: Int) {
        val intent = Intent(this, DetailedUserActivity::class.java)
        intent.putExtra("id", index + 1)
        startActivity(intent)
    }
}