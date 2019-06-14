package com.rmedina.usecases.recipes

import com.rmedina.recipesdemo.domain.Recipe
import com.rmedina.usecases.UseCase
import com.rmedina.usecases.UseCase.None
import com.rmedina.domain.repository.RecipeRepository

class RecipesListUseCase (private val repository: RecipeRepository) : UseCase<List<Recipe>, None>() {

    override fun runInBackground(params: None): List<Recipe> {
        return repository.getRecipeList()
    }
}