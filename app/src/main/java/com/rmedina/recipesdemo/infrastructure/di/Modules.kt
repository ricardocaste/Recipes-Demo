package com.rmedina.recipesdemo.infrastructure.di

import com.rmedina.data.repository.recipes.RecipeDataRepository
import com.rmedina.data.source.ApiClientGenerator
import com.rmedina.data.source.RetrofitApiClientGenerator
import com.rmedina.domain.repository.RecipeRepository
import com.rmedina.recipesdemo.presentation.main.presenter.RecipeListPresenter
import com.rmedina.usecases.recipes.RecipesListUseCase
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