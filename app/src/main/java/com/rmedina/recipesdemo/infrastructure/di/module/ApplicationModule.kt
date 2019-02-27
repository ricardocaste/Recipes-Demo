package com.rmedina.recipesdemo.infrastructure.di.module

import com.rmedina.recipesdemo.data.datasource.ApiClientGenerator
import com.rmedina.recipesdemo.data.datasource.RetrofitApiClientGenerator
import com.rmedina.recipesdemo.data.repository.recipes.RecipeDataRepository
import com.rmedina.recipesdemo.domain.repository.RecipeRepository
import com.rmedina.recipesdemo.infrastructure.AndroidApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: AndroidApplication) {

    @Provides
    @Singleton
    fun providesApplicationContext(): AndroidApplication {
        return application
    }

    @Provides
    @Singleton
    internal fun provideApiClientGenerator(apiClientGenerator: RetrofitApiClientGenerator): ApiClientGenerator {
        return apiClientGenerator
    }

    @Provides
    @Singleton
    fun providesRecipeRepository(recipeDataRepository: RecipeDataRepository): RecipeRepository = recipeDataRepository

}