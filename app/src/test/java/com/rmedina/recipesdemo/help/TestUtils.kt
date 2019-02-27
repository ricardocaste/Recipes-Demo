package com.rmedina.recipesdemo.help

import com.rmedina.recipesdemo.domain.Recipe
import org.mockito.invocation.InvocationOnMock

@Suppress("UNCHECKED_CAST")
fun <T> InvocationOnMock.onAnswer(supplier: () -> T) {
    this.arguments?.let { args ->
        val onResult = args[1] as (T) -> Unit
        val onError = args[2] as (Throwable) -> Unit

        val result = supplier()

        when (result) {
            is Throwable -> onError(result)
            else -> onResult(result)
        }
    }
}

fun mockRecipe(): Recipe = Recipe(
    "tit",
    "link",
    "ingred",
    "image"
)