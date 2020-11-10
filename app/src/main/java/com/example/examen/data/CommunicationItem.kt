package com.example.myapplication.data

data class CommunicationItem (
        val name: String = "",
        val dateOfCreate: String = "",
        val itemType: CommunicationItemType = CommunicationItemType()
)