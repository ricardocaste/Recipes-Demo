package com.rmedina.data.repository.recipes

import com.rmedina.data.source.ApiClientGenerator
import com.rmedina.data.source.recipe.RecipeApi
import com.rmedina.recipesdemo.data.repository.BaseRepository
import com.rmedina.recipesdemo.domain.Recipe
import com.rmedina.domain.repository.RecipeRepository

class RecipeDataRepository(private val apiClientGenerator: ApiClientGenerator) : BaseRepository(), RecipeRepository {

    override fun getRecipeList(): List<Recipe> {
        val api = apiClientGenerator.generateApi(RecipeApi::class.java)
        val call = api.getRecipes()

        return executeCall(call).results
    }

}