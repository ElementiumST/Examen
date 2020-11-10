package com.example.examen.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.Order

class MainViewModel : ViewModel(){
    val OrderList = MutableLiveData<List<Order>>()

}
