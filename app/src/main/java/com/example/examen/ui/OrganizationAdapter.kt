package com.example.examen.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examen.R
import com.example.myapplication.data.Organization
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


var onClick: ((Organization?) -> Unit) = {}

class OrganizationAdapter : RecyclerView.Adapter<OrganizationViewHolder>() {
    lateinit var items: List<Organization>
    companion object {
        var onClick: ((Organization?) -> Unit) = {}
    }
    init {
        items = listOf()
        val listener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
            }

            override fun onDataChange(snapshot: DataSnapshot) {

                val list = mutableListOf<Organization>()
                for (childSnap in snapshot.children) {
                    list.add(childSnap.getValue(Organization::class.java)!!)
                }
                items = list
                notifyDataSetChanged()
            }

        }
        FirebaseDatabase.getInstance().getReference("orgs").addListenerForSingleValueEvent(listener)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrganizationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val root = inflater.inflate(R.layout.holder_organization, parent, false)
        return OrganizationViewHolder(root)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: OrganizationViewHolder, position: Int) {
        holder.bind(items[position])
    }
}

class OrganizationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.title)
    val name: TextView = itemView.findViewById(R.id.name)
    val phone: TextView = itemView.findViewById(R.id.phone)

    fun bind(org: Organization) {
        title.text = org.title
        name.text  = org.name
        phone.text = org.phone
        itemView.setOnClickListener {
            OrganizationAdapter.onClick(org)
        }
    }
}
