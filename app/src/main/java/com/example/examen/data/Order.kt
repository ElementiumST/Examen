package com.example.myapplication.data

data class Order(
        val organization: Organization = Organization(),
        val itemOwnerId: String = "",
        val OrderId: String = "",
        val dateIndexing: String = "",
        val dateStartOfRepairing: String = "",
        val dateEndOfRepairing: String = "",
        val worker: Worker = Worker(),
        val item: CommunicationItem = CommunicationItem()

)