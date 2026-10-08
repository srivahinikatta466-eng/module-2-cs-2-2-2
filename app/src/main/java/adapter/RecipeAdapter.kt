package project.case_study2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import project.case_study2.R
import project.case_study2.model.Recipe

class RecipeAdapter(
    private val recipes: List<Recipe>,
    private val onItemClick: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount() = recipes.size

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recipeImage: ImageView = itemView.findViewById(R.id.recipeImage)
        private val recipeName: TextView = itemView.findViewById(R.id.recipeName)
        private val recipeDescription: TextView = itemView.findViewById(R.id.recipeDescription)
        private val timeInfo: TextView = itemView.findViewById(R.id.timeInfo)

        fun bind(recipe: Recipe) {
            recipeName.text = recipe.name
            recipeDescription.text = recipe.description
            timeInfo.text = "${recipe.prepTime} prep | ${recipe.cookTime} cook"

            Glide.with(itemView.context)
                .load(recipe.imageUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .into(recipeImage)

            itemView.setOnClickListener {
                onItemClick(recipe)
            }
        }
    }
}