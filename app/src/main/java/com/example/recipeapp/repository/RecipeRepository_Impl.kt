package com.example.recipeapp.repository

import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.network.RecipeService
import com.example.recipeapp.network.model.RecipeDtoMapper

class RecipeRepository_Impl(
    val recipeService: RecipeService,
    val mapper: RecipeDtoMapper
) : RecipeRepository {

    override suspend fun search(token: String, page: Int, query: String): List<Recipe> {
        return mapper.toDomainList(recipeService.search(token, page, query).recipes)
    }

    override suspend fun get(token: String, id: Int): Recipe {
        return mapper.mapToDomainModel(recipeService.get(token, id))
    }

}
