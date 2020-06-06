package virabe.example.mycookbook.database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Beenal Patel on 03-06-2020.
 */

class RecipeRepository(private val recipeDao: RecipeDao) {

    val allRecipe: LiveData<List<Recipe>> = recipeDao.getAllRecipe()

//        fun getDataById(id: Int):LiveData<Recipe> = recipeDao.getDataById(id)
//    {
//        recipeDao.
//        return LiveDataReactiveStreams.fromPublisher(recipeDao.getById(id)
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.computation()))
//    }
//    fun getDataById(id: String): LiveData<Recipe> {
//        return LiveDataReactiveStreams.fromPublisher(
//            recipeDao.getById(id)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.computation())
//        )
//    }


    @WorkerThread
    fun getDataById(id: Int): LiveData<Recipe> {
        return recipeDao.getDataById(id)
//        recipeDao.getDataById(id)
    }

    @WorkerThread
    suspend fun insert(
        title: String,
        ingredients: String,
        details: String,
        tips: String,
        servings: String,
        tagId: Int
    ) = withContext(Dispatchers.IO) {
        recipeDao.insert(Recipe(title, ingredients, details, tips, servings, tagId))
    }

    @WorkerThread
    suspend fun deleteAllRecipes() = withContext(Dispatchers.IO) {
        recipeDao.deleteAll()
    }

}