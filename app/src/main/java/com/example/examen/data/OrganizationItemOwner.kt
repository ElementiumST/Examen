package com.example.myapplication.data

data class OrganizationItemOwner
(
        override val id: String = "",
        val name: String = "",
        override val address: String = "",
        override val phone: String = "",
        override val orders: List<Order> = listOf(),
        override val ownerType: Int = 0,
        ): ItemOwner()