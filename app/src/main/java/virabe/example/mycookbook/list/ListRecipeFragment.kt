package virabe.example.mycookbook.list

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_recipelist.*
import virabe.example.mycookbook.R
import virabe.example.mycookbook.add.AddRecipe
import virabe.example.mycookbook.add.FragmentAddRecipe
import virabe.example.mycookbook.database.Recipe
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Beenal Patel on 03-06-2020.
 */
class ListRecipeFragment : Fragment() {

    private val viewModel: RecipeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_recipelist, container, false)
    }

    override fun onViewCreated(view: View, savedState: Bundle?) {
        super.onViewCreated(view, savedState)
        val adapter = RecipeListAdapter(view.context)

        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                super.onChanged()
                emptyView.visibility = if (adapter.itemCount == 0) View.VISIBLE else View.INVISIBLE
            }
        })

        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(activity)

        viewModel.allRecipes.observe(this, Observer<List<Recipe>> { recipe ->
            adapter.setRecipe(recipe)
        })
    }

    fun onFabClick() {
        val intent = Intent(activity, AddRecipe::class.java)
        startActivityForResult(intent, NEW_RECIPE_REQUEST_CODE)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.delete_all -> viewModel.deleteAllRecipes().let { true }
            else -> super.onOptionsItemSelected(item)
        }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_RECIPE_REQUEST_CODE && resultCode == RESULT_OK) {
            data?.getStringExtra(FragmentAddRecipe.TITLE)?.apply {
                viewModel.insert(
                    title = data.getStringExtra(FragmentAddRecipe.TITLE),
                    ingredients = data.getStringExtra(FragmentAddRecipe.INGREDIANTS),
                    details = data.getStringExtra(FragmentAddRecipe.DETAILS),
                    tips = data.getStringExtra(FragmentAddRecipe.TIPS),
                    servings = data.getStringExtra(FragmentAddRecipe.SERVINGS), tagId = 0
                )
            }
        }
    }

    companion object {
        const val NEW_RECIPE_REQUEST_CODE = 1
    }
}