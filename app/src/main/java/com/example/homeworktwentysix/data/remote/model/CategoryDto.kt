package com.example.homeworktwentysix.data.remote.model

import com.example.homeworktwentysix.domain.model.Category
import com.squareup.moshi.Json

data class CategoryDto(
    @Json(name = "id")  val id: String?,
    @Json(name = "name") val name: String?,
    @Json(name = "name_de") val nameDe: String?,
    @Json(name = "createdAt") val createdAt: String?,
    @Json(name = "bgl_number") val bglNumber: String?,
    @Json(name = "bgl_variant") val bglVariant: String?,
    @Json(name = "order_id") val orderId: Int?,
    @Json(name = "main") val main: String?,
    @Json(name = "children") val children: List<CategoryDto>?
) {

}
