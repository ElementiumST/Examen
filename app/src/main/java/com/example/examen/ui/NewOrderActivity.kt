package com.example.examen.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.examen.R
import com.example.myapplication.data.*
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class NewOrderActivity : AppCompatActivity() {
    lateinit var org: Organization
    lateinit var worker: Worker
    lateinit var itemType: CommunicationItemType

    var activeFragment: Fragment? = null
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_order)
        val root: View = findViewById(R.id.root)
        val chooseOrg = findViewById<Button>(R.id.chooseOrg)
        val chooseWorker = findViewById<Button>(R.id.chooseWorker)
        val chooseItemType = findViewById<Button>(R.id.chooseItemType)
        val create = findViewById<Button>(R.id.create)

        chooseOrg.setOnClickListener {
            val listFragment = ListFragment<OrganizationViewHolder, OrganizationAdapter>()
            listFragment.setAdapter(OrganizationAdapter())
            supportFragmentManager.beginTransaction()
                    .add(root.id, listFragment)
                    .commit()
            activeFragment = listFragment
            OrganizationAdapter.onClick = {
                if(it != null){
                    org = it
                    chooseOrg.text = it.title
                    onBackPressed()
                }

            }
        }
        chooseWorker.setOnClickListener {
            val listFragment = ListFragment<WorkerViewHolder, WorkerAdapter>()
            listFragment.setAdapter(WorkerAdapter())
            supportFragmentManager.beginTransaction()
                    .add(root.id, listFragment)
                    .commit()
            activeFragment = listFragment
            WorkerAdapter.onClick = {
                if(it != null){
                    worker = it
                    chooseWorker.text = "${it.secondName} ${it.firstName}"
                    onBackPressed()
                }
            }
        }
        chooseItemType.setOnClickListener {
            val listFragment = ListFragment<ItemTypeViewHolder, ItemTypeAdapter>()
            listFragment.setAdapter(ItemTypeAdapter())
            supportFragmentManager.beginTransaction()
                    .add(root.id, listFragment)
                    .commit()
            activeFragment = listFragment
            ItemTypeAdapter.onClick = {
                if(it != null){
                    itemType = it
                    chooseItemType.text = it.title
                    onBackPressed()
                }

            }
        }
        val itemName = findViewById<EditText>(R.id.itemName)
        val itemDate = findViewById<EditText>(R.id.itemDate)
        val calendar = Calendar.getInstance()
        create.setOnClickListener {
            val ref = FirebaseDatabase.getInstance().getReference("order").push()
            val order = Order(
                    org,
                    "-MLlePa8rSe80hi6x9xZ",
                    ref.key!!,
                    "0${calendar.get(Calendar.DAY_OF_MONTH)}.${calendar.get(Calendar.MONTH)}.${calendar.get(Calendar.YEAR)}",
                    "${calendar.get(Calendar.DAY_OF_MONTH+2)}.${calendar.get(Calendar.MONTH)}.${calendar.get(Calendar.YEAR)}",
                    "${calendar.get(Calendar.DAY_OF_MONTH+7)}.${calendar.get(Calendar.MONTH)}.${calendar.get(Calendar.YEAR)}",
                    worker,
                    CommunicationItem(
                            itemName.text.toString(),
                            itemDate.text.toString(),
                            itemType
                    )
            )
            ref.setValue(order)
            finish()
            Toast.makeText(this, "Заказ добавлен на обработку", Toast.LENGTH_LONG).show()
        }

    }

    override fun onBackPressed() {
        if(activeFragment == null) {
            super.onBackPressed()
        } else {
            supportFragmentManager.beginTransaction().remove(activeFragment!!).commit()
            activeFragment = null
        }
    }
}