package com.rmedina.recipesdemo.domain

import java.io.Serializable

class RecipeContainer(val results: List<Recipe>) : Serializable

class Recipe(
    val title: String?,
    val href: String?,
    val ingredients: String?,
    val thumbnail: String?
) : Serializable