package com.rmedina.recipesdemo.data.repository.recipes

import com.rmedina.recipesdemo.data.datasource.recipe.model.RecipeResponse
import com.rmedina.recipesdemo.domain.Recipe
import com.rmedina.recipesdemo.domain.RecipeContainer

fun RecipeResponse.mapToDomain(): RecipeContainer = RecipeContainer(
    results
)