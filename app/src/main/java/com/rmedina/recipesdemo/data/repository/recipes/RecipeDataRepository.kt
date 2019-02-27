package com.rmedina.recipesdemo.data.repository.recipes

import com.rmedina.recipesdemo.data.datasource.ApiClientGenerator
import com.rmedina.recipesdemo.data.datasource.recipe.RecipeApi
import com.rmedina.recipesdemo.data.repository.BaseRepository
import com.rmedina.recipesdemo.domain.Recipe
import com.rmedina.recipesdemo.domain.RecipeContainer
import com.rmedina.recipesdemo.domain.repository.RecipeRepository
import javax.inject.Inject

class RecipeDataRepository @Inject constructor(
    private val apiClientGenerator: ApiClientGenerator
) : BaseRepository(), RecipeRepository {

    override fun getRecipeList(): List<Recipe> {
        val api = apiClientGenerator.generateApi(RecipeApi::class.java)
        val call = api.getRecipes()

        return executeCall(call).results
    }

}