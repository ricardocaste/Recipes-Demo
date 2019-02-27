package com.rmedina.recipesdemo.presentation.main.presenter

import com.rmedina.recipesdemo.domain.Recipe
import com.rmedina.recipesdemo.domain.interactor.UseCase
import com.rmedina.recipesdemo.domain.interactor.recipes.RecipesListUseCase
import com.rmedina.recipesdemo.presentation.base.BasePresenter
import com.rmedina.recipesdemo.presentation.main.view.RecipeListView
import javax.inject.Inject

class RecipeListPresenter @Inject constructor(
    private val recipeListUseCase: RecipesListUseCase
) : BasePresenter<RecipeListView>() {
    private var recipeList: MutableList<Recipe> = mutableListOf()

    override fun onAttach(view: RecipeListView) {
        super.onAttach(view)
        recipeListUseCase.executeAsync(UseCase.None(), ::onUserListLoaded, ::onUseCaseError)
    }

    private fun onUserListLoaded(recipeList: List<Recipe>) {
        this.recipeList = recipeList.toMutableList()
        if (recipeList.isEmpty()) view.showEmptyView()
        else view.onLoadData(recipeList)
    }

    private fun onUseCaseError(t: Throwable) {
        view.showMessage(t.message ?: "An error has ocurred")
    }

    fun onClickItem(recipe: Recipe) {
        view.onItemClicked(recipe)
    }

    fun filterRecipeList(text: String) {
        val filteredList = recipeList.filter { user ->
            containsText(user, text.toLowerCase())
        }
        view.onFilterRecipes(filteredList)
    }

    private fun containsText(recipe: Recipe, text: String): Boolean {
        return recipe.title?.toLowerCase()?.contains(text) == true
    }

}