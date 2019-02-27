package com.rmedina.recipesdemo.data.repository.recipe

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.rmedina.recipesdemo.help.CallFake
import com.rmedina.recipesdemo.data.datasource.ApiClientGenerator
import com.rmedina.recipesdemo.data.datasource.recipe.RecipeApi
import com.rmedina.recipesdemo.data.repository.recipes.RecipeDataRepository
import com.rmedina.recipesdemo.domain.Recipe
import com.rmedina.recipesdemo.domain.RecipeContainer
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RecipeDataRepositoryTest {

    @InjectMocks
    private lateinit var repository: RecipeDataRepository

    @Mock
    private lateinit var clientGenerator: ApiClientGenerator

    @Mock
    private lateinit var recipeApi: RecipeApi

    @Before
    fun setUp() {
        whenever(clientGenerator.generateApi(RecipeApi::class.java)).thenReturn(recipeApi)
    }

    @Test
    fun getRecipeList_whenCallRecipeListApi_shouldMapDataCorrectly() {
        whenever(recipeApi.getRecipes()).thenReturn(CallFake.buildSuccess(RecipeContainer(buildRecipeResponse())))

        val recipeList = repository.getRecipeList()

        Assert.assertThat(recipeList.size, CoreMatchers.`is`(1))
        Assert.assertThat(recipeList, CoreMatchers.hasItem(Matchers.hasProperty("title", CoreMatchers.`is`("tit"))))
        Assert.assertThat(recipeList, CoreMatchers.hasItem(Matchers.hasProperty("href", CoreMatchers.`is`("link"))))
        Assert.assertThat(
            recipeList,
            CoreMatchers.hasItem(Matchers.hasProperty("ingredients", CoreMatchers.`is`("ingred")))
        )
        Assert.assertThat(
            recipeList,
            CoreMatchers.hasItem(Matchers.hasProperty("thumbnail", CoreMatchers.`is`("image")))
        )
    }

    @Test
    fun getRecipeList_whenCallToRepository_shouldCallToApi() {
        whenever(recipeApi.getRecipes()).thenReturn(CallFake.buildSuccess(RecipeContainer(buildRecipeResponse())))

        repository.getRecipeList()

        verify(recipeApi).getRecipes()
    }

    private fun buildRecipeResponse(): List<Recipe> {
        return listOf(
            Recipe(
                "tit",
                "link",
                "ingred",
                "image"
            )
        )
    }

}