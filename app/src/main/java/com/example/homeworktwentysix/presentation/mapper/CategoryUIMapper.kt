package com.example.homeworktwentysix.presentation.mapper

import com.example.homeworktwentysix.domain.model.Category
import com.example.homeworktwentysix.presentation.model.CategoryUI

fun Category.toPresentation(counter: Int = 0): CategoryUI {

    if (!this.children.isNullOrEmpty()) {
        val res = CategoryUI(
            id = id,
            name = name,
            nameDe = nameDe,
            createdAt = createdAt,
            bglNumber = bglNumber,
            bglVariant = bglVariant,
            orderId = orderId,
            main = main,
            parentCounter = counter,
            children = children.map {
                it.toPresentation(counter+1)

            },


        )
        return res
    } else {

        return CategoryUI(
             id = id,
             name = name,
             nameDe = nameDe,
             createdAt = createdAt,
             bglNumber = bglNumber,
             bglVariant = bglVariant,
             orderId = orderId,
             main = main,
             children = listOf(),
            parentCounter = counter
         )


    }

}

