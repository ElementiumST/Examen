package com.example.examen.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examen.R
import com.example.myapplication.data.Worker
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener



class WorkerAdapter : RecyclerView.Adapter<WorkerViewHolder>() {
    companion object {
        var onClick: ((Worker?) -> Unit) = {}
    }
    lateinit var items: List<Worker>
    init {
        items = listOf()
        val listener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
            }

            override fun onDataChange(snapshot: DataSnapshot) {

                val list = mutableListOf<Worker>()
                for (childSnap in snapshot.children) {
                    list.add(childSnap.getValue(Worker::class.java)!!)
                }
                items = list
                notifyDataSetChanged()
            }

        }
        FirebaseDatabase.getInstance().getReference("workers").addListenerForSingleValueEvent(listener)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val root = inflater.inflate(R.layout.holder_worker, parent, false)
        return WorkerViewHolder(root)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: WorkerViewHolder, position: Int) {
        holder.bind(items[position])
    }
}

class WorkerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val fname: TextView = itemView.findViewById(R.id.fname)
    val sname: TextView = itemView.findViewById(R.id.sname)
    val lname: TextView = itemView.findViewById(R.id.lname)
    val status: TextView = itemView.findViewById(R.id.status)

    fun bind(worker: Worker) {
        fname.text = worker.firstName
        sname.text  = worker.secondName
        lname.text = worker.lastName
        status.text = worker.status
        itemView.setOnClickListener {
            WorkerAdapter.onClick(worker)
        }
    }
}
