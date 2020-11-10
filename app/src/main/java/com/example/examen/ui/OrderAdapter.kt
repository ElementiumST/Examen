package com.example.examen.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.examen.R
import com.example.myapplication.data.Order
import com.google.firebase.database.FirebaseDatabase

class OrderAdapter(val activity: MainActivity) : RecyclerView.Adapter<OrderViewHolder>() {
    lateinit var items: List<Order>
    init {
        items = listOf()
       activity.viewModel.OrderList.observe(activity, Observer<List<Order>> {
           items = it
           notifyDataSetChanged()
       })
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val root = inflater.inflate(R.layout.holder_order, parent, false)
        return OrderViewHolder(root)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(items[position])
    }

}

class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val orgName = itemView.findViewById<TextView>(R.id.orgTitle)
    val workerName = itemView.findViewById<TextView>(R.id.workerName)
    val comName = itemView.findViewById<TextView>(R.id.comName)
    val comType = itemView.findViewById<TextView>(R.id.comType)
    val dateStart = itemView.findViewById<TextView>(R.id.dateStart)
    val dateEnd = itemView.findViewById<TextView>(R.id.dateEnd)
    @SuppressLint("SetTextI18n")
    fun bind(order: Order) {
        orgName.text = order.organization.title
        workerName.text = "${order.worker.firstName} ${order.worker.firstName} ${order.worker.lastName}"
        comName.text = order.item.name
        comType.text = order.item.itemType.title
        dateStart.text = order.dateStartOfRepairing
        dateEnd.text = order.dateEndOfRepairing
    }
}