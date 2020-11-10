package com.example.examen.ui


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examen.R
import com.example.myapplication.data.CommunicationItemType
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class ItemTypeAdapter : RecyclerView.Adapter<ItemTypeViewHolder>() {
    var items: List<CommunicationItemType>
    companion object {
        var onClick: ((CommunicationItemType?) -> Unit) = {}
    }
    init {
        items = listOf()
        val listener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
            }

            override fun onDataChange(snapshot: DataSnapshot) {

                val list = mutableListOf<CommunicationItemType>()
                for (childSnap in snapshot.children) {
                    list.add(childSnap.getValue(CommunicationItemType::class.java)!!)
                }
                items = list
                notifyDataSetChanged()
            }

        }
        FirebaseDatabase.getInstance().getReference("itemType").addListenerForSingleValueEvent(listener)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTypeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val root = inflater.inflate(R.layout.holder_item_type, parent, false)
        return ItemTypeViewHolder(root)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemTypeViewHolder, position: Int) {
        holder.bind(items[position])
    }
}

class ItemTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.title)
    val name: TextView = itemView.findViewById(R.id.name)

    fun bind(cit: CommunicationItemType) {
        title.text = cit.title
        name.text  = cit.name
        itemView.setOnClickListener {
            ItemTypeAdapter.onClick(cit)
        }
    }
}
