package virabe.example.mycookbook.list

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import virabe.example.mycookbook.R
import virabe.example.mycookbook.database.Recipe
import virabe.example.mycookbook.detail.DetailActivity

/**
 * Created by Beenal Patel on 03-06-2020.
 */
class RecipeListAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var recipes: List<Recipe>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return object :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.row_recipe, parent, false)) {}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder.itemView.findViewById(R.id.textView) as TextView).text =
            (recipes?.get(position)?.title ?: "No Recipes").toString()
        holder.itemView.setOnClickListener { v ->
            val intent = Intent(v.context, DetailActivity::class.java)
            intent.putExtra("recipe_id", recipes?.get(position)?.recipeId)
            v.context.startActivity(intent)
        }
    }

    fun setRecipe(recipes: List<Recipe>) {
        this.recipes = recipes
        notifyDataSetChanged()
    }

    override fun getItemCount() = recipes?.size ?: 0
}