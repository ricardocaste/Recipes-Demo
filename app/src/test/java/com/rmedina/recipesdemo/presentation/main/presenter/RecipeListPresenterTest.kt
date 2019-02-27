package com.rmedina.recipesdemo.presentation.main.presenter

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.rmedina.recipesdemo.domain.Recipe
import com.rmedina.recipesdemo.domain.exception.RepositoryException
import com.rmedina.recipesdemo.domain.interactor.recipes.RecipesListUseCase
import com.rmedina.recipesdemo.help.mockRecipe
import com.rmedina.recipesdemo.help.onAnswer
import com.rmedina.recipesdemo.presentation.main.view.RecipeListView
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RecipeListPresenterTest {

    @InjectMocks
    private lateinit var presenter: RecipeListPresenter

    @Mock
    private lateinit var view: RecipeListView

    @Mock
    private lateinit var userListUseCase: RecipesListUseCase

    @Before
    fun setUp() {
        presenter.onAttach(view)
    }

    @Test
    fun onAttach_whenLoadRecipeList_shouldLoadData() {
        Mockito.doAnswer {
            it.onAnswer { listOf(mockRecipe()) }
        }.whenever(userListUseCase).executeAsync(any(), any(), any())

        presenter.onAttach(view)

        verify(view).onLoadData(any())
    }

    @Test
    fun onAttach_whenLoadRecipeListEmpty_shouldEmptyView() {
        Mockito.doAnswer {
            it.onAnswer { stubEmptyList() }
        }.whenever(userListUseCase).executeAsync(any(), any(), any())

        presenter.onAttach(view)

        verify(view).showEmptyView()
    }

    @Test()
    fun onAttach_whenLoadUserRecipeFail_shouldErrorMessage() {
        Mockito.doAnswer {
            it.onAnswer { RepositoryException("Test Error") }
        }.whenever(userListUseCase).executeAsync(any(), any(), any())

        presenter.onAttach(view)

        verify(view).showMessage(any())
    }

    private fun stubEmptyList(): List<Recipe> = emptyList()

}