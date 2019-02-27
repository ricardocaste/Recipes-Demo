package com.rmedina.recipesdemo.domain.interactor.recipes

import com.rmedina.recipesdemo.domain.Recipe
import com.rmedina.recipesdemo.domain.interactor.UseCase
import com.rmedina.recipesdemo.domain.interactor.UseCase.None
import com.rmedina.recipesdemo.domain.repository.RecipeRepository
import javax.inject.Inject

class RecipesListUseCase @Inject constructor(
    private val repository: RecipeRepository
) : UseCase<List<Recipe>, None>() {

    override fun runInBackground(params: None): List<Recipe> {
        return repository.getRecipeList()
    }
}