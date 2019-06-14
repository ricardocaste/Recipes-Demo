package com.rmedina.domain.repository

import com.rmedina.recipesdemo.domain.Recipe

interface RecipeRepository {

    fun getRecipeList(): List<Recipe>

}