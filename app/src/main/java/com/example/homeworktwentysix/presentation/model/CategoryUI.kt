package com.example.homeworktwentysix.presentation.model

data class CategoryUI(
    var id: String?,
    var name: String?,
    var nameDe: String?,
    var createdAt: String?,
    var bglNumber: String?,
    var bglVariant: String?,
    var orderId: Int?,
    var main: String?,
    var parentCounter: Int = 0,
    var children: List<CategoryUI>?,
    var open: Boolean = false

)

