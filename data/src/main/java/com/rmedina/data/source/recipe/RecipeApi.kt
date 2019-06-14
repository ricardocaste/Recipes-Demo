package com.rmedina.data.source.recipe

import com.rmedina.recipesdemo.domain.RecipeContainer
import retrofit2.Call
import retrofit2.http.GET

interface RecipeApi {

    @GET("api/")
    fun getRecipes(): Call<RecipeContainer>

}