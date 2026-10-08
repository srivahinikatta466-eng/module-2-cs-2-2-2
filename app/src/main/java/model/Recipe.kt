package project.case_study2.model

import java.io.Serializable

data class Recipe(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val prepTime: String,
    val cookTime: String,
    val servings: Int,
    val ingredients: List<Ingredient>,
    val steps: List<String>
) : Serializable

data class Ingredient(
    val name: String,
    val quantity: String,
    val unit: String
) : Serializable