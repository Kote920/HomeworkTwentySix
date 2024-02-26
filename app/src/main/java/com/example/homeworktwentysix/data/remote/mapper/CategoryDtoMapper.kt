package com.example.homeworktwentysix.data.remote.mapper

import com.example.homeworktwentysix.data.remote.model.CategoryDto
import com.example.homeworktwentysix.domain.model.Category


fun CategoryDto.toDomain(): Category {
    if (!this.children.isNullOrEmpty()) {
        return Category(
            id = id,
            name = name,
            nameDe = nameDe,
            createdAt = createdAt,
            bglNumber = bglNumber,
            bglVariant = bglVariant,
            orderId = orderId,
            main = main,
            children = children.map {
                it.toDomain()
            }
        )
    } else {
        return Category(
            id = id,
            name = name,
            nameDe = nameDe,
            createdAt = createdAt,
            bglNumber = bglNumber,
            bglVariant = bglVariant,
            orderId = orderId,
            main = main,
            children = listOf()
        )


    }

}

