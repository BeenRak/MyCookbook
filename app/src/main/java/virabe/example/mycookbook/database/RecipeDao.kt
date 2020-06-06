package virabe.example.mycookbook.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * Created by Beenal Patel on 03-06-2020.
 */
@Dao
interface RecipeDao {
    @Insert
//    suspend
    fun insert(recipe: Recipe)

    @Query("SELECT * FROM recipe WHERE recipeId = :id ")
   fun getDataById(id: Int): LiveData<Recipe>

    @Query("DELETE FROM recipe")
//    suspend
    fun deleteAll()

    @Query("SELECT * from recipe ORDER BY recipeId ASC")
    fun getAllRecipe(): LiveData<List<Recipe>>
}