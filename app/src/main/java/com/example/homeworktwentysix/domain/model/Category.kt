package com.example.homeworktwentysix.domain.model



data class Category(
    val id: String?,
    val name: String?,
    val nameDe: String?,
    val createdAt: String?,
    val bglNumber: String?,
    val bglVariant: String?,
    val orderId: Int?,
    val main: String?,
    val children: List<Category>?,
    var counter: Int = 0
)

