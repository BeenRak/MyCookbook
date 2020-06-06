package virabe.example.mycookbook.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import virabe.example.mycookbook.database.Recipe
import kotlin.coroutines.CoroutineContext
import virabe.example.mycookbook.database.RecipeRepository


/**
 * Created by Beenal Patel on 03-06-2020.
 */
class RecipeViewModel(private val repository: RecipeRepository) : ViewModel(), CoroutineScope {
    private val job: Job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job // By default child coroutines will run on the main thread.

    val allRecipes: LiveData<List<Recipe>>
        get() = repository.allRecipe

    private val mTriger: MutableLiveData<Int> = MutableLiveData()
    val recipe: LiveData<Recipe> = Transformations.switchMap(mTriger) {
        repository.getDataById(it)
    }

    fun triggerFetchData(id: Int) {
        mTriger.value = id
    }

    fun getDataById(id: Int) = recipe

    fun insert(
        title: String,
        ingredients: String,
        details: String,
        tips: String,
        servings: String, tagId: Int
    ) {
        launch {
            repository.insert(title, ingredients, details, tips, servings, tagId = 0)
        }
    }

    fun deleteAllRecipes() {
        launch {
            repository.deleteAllRecipes()
        }
    }

//     suspend fun getDataById(id: Int) {
//        launch {
//            repository.getDataById(id)
////            return recipe
////            repository.getDataById(id)
//        }
//    }

    override fun onCleared() {
        super.onCleared()
        job.cancel() // Parent Job cancels all child coroutines.
    }
}