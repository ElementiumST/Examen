package com.example.myapplication.data

import java.io.Serializable

abstract class ItemOwner : Serializable {
    abstract val id: String
    abstract val address: String
    abstract val phone: String
    abstract val orders: List<Order>
    abstract val ownerType: Int
}