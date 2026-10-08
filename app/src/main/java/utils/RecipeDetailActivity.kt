package project.case_study2

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.appbar.MaterialToolbar
import project.case_study2.adapter.IngredientAdapter
import project.case_study2.model.Recipe

class RecipeDetailActivity : AppCompatActivity() {

    private lateinit var toolbar: MaterialToolbar
    private lateinit var recipeImage: ImageView
    private lateinit var recipeName: TextView
    private lateinit var recipeDescription: TextView
    private lateinit var prepTime: TextView
    private lateinit var cookTime: TextView
    private lateinit var servings: TextView
    private lateinit var stepsTextView: TextView
    private lateinit var ingredientsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        toolbar = findViewById(R.id.toolbar)
        recipeImage = findViewById(R.id.recipeImage)
        recipeName = findViewById(R.id.recipeName)
        recipeDescription = findViewById(R.id.recipeDescription)
        prepTime = findViewById(R.id.prepTime)
        cookTime = findViewById(R.id.cookTime)
        servings = findViewById(R.id.servings)
        stepsTextView = findViewById(R.id.stepsTextView)
        ingredientsRecyclerView = findViewById(R.id.ingredientsRecyclerView)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        val recipe = intent.getSerializableExtra("recipe") as? Recipe
        if (recipe != null) {
            displayRecipeDetails(recipe)
        }
    }

    private fun displayRecipeDetails(recipe: Recipe) {
        recipeName.text = recipe.name
        recipeDescription.text = recipe.description
        prepTime.text = "Prep: ${recipe.prepTime}"
        cookTime.text = "Cook: ${recipe.cookTime}"
        servings.text = "Serves: ${recipe.servings}"

        // Build steps text
        val stepsText = StringBuilder()
        recipe.steps.forEachIndexed { index, step ->
            stepsText.append("${index + 1}. $step\n\n")
        }
        stepsTextView.text = stepsText.toString()

        // Setup ingredients RecyclerView
        ingredientsRecyclerView.layoutManager = LinearLayoutManager(this)
        ingredientsRecyclerView.adapter = IngredientAdapter(recipe.ingredients)

        // Load image
        Glide.with(this)
            .load(recipe.imageUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_placeholder)
            .into(recipeImage)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}