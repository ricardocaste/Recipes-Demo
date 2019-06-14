package com.rmedina.recipesdemo.presentation.main.view

import com.rmedina.recipesdemo.domain.Recipe
import com.rmedina.recipesdemo.presentation.base.BaseView

interface RecipeListView : BaseView {

    fun onLoadData(recipeList: List<Recipe>)

    fun onItemClicked(recipe: Recipe)

    fun showEmptyView()

    fun onFilterRecipes(recipeList: List<Recipe>)
}