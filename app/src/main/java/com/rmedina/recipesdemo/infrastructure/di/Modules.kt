package com.rmedina.recipesdemo.infrastructure.di

import com.rmedina.recipesdemo.data.datasource.ApiClientGenerator
import com.rmedina.recipesdemo.data.datasource.RetrofitApiClientGenerator
import com.rmedina.recipesdemo.data.repository.recipes.RecipeDataRepository
import com.rmedina.recipesdemo.domain.interactor.recipes.RecipesListUseCase
import com.rmedina.recipesdemo.domain.repository.RecipeRepository
import com.rmedina.recipesdemo.presentation.main.presenter.RecipeListPresenter
import org.koin.dsl.module

val repositoryModule = module {
    factory<RecipeRepository> { RecipeDataRepository(get()) }
}

val apiClientGeneratorModule = module {
    factory<ApiClientGenerator> { RetrofitApiClientGenerator() }
}

val useCaseModule = module {
    factory { RecipesListUseCase(get()) }
}

val presenterModule = module {
    factory { RecipeListPresenter(get()) }
}