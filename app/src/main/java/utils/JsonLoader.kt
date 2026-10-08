package project.case_study2.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import project.case_study2.R
import project.case_study2.model.Recipe
import java.io.IOException

object JsonLoader {

    fun loadRecipes(context: Context): List<Recipe> {
        return try {
            val inputStream = context.resources.openRawResource(R.raw.recipes)
            val jsonString = inputStream.bufferedReader().use { it.readText() }

            val type = object : TypeToken<List<Recipe>>() {}.type
            Gson().fromJson(jsonString, type)
        } catch (e: IOException) {
            e.printStackTrace()
            emptyList()
        }
    }
}