package com.example.examen.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examen.R
import com.example.myapplication.data.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity: AppCompatActivity() {
    lateinit var itemOwner: ItemOwner
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val newOrderBtn = findViewById<FloatingActionButton>(R.id.newOrder)
        newOrderBtn.setOnClickListener{
            val intent = Intent(this, NewOrderActivity::class.java)
            startActivity(intent)
        }
        FirebaseDatabase.getInstance().getReference("order").addValueEventListener(object
            : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<Order>()
                for (item in snapshot.children) {
                    list.add(item.getValue(Order::class.java)!!)
                }
                viewModel.OrderList.postValue(list)
            }
        })
        val list = findViewById<RecyclerView>(R.id.list)
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = OrderAdapter(this)

    }
}