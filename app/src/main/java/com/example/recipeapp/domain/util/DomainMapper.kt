package com.example.recipeapp.domain.util

interface DomainMapper <T, DomainModel> {
    fun mapToDomainModel(model: T): DomainModel
}