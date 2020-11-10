package com.example.myapplication.data

data class SimpleItemOwner(
        override val id: String = "",
        val passportSer: String = "",
        val passportNum: String = "",
        val firstName: String = "",
        val secondName: String = "",
        val lastName: String = "",
        override val address: String = "",
        override val phone: String = "",
        override val orders: List<Order> = listOf(),
        override val ownerType: Int = 1,


) : ItemOwner()
