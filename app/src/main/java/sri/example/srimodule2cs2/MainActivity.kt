package project.case_study2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import project.case_study2.adapter.RecipeAdapter
import project.case_study2.utils.JsonLoader

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val recipes = JsonLoader.loadRecipes(this)
        adapter = RecipeAdapter(recipes) { recipe ->
            val intent = Intent(this, RecipeDetailActivity::class.java)
            intent.putExtra("recipe", recipe)
            startActivity(intent)
        }
        recyclerView.adapter = adapter
    }
}