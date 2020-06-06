package virabe.example.mycookbook.database

import androidx.room.*

/**
 * Created by Beenal Patel on 03-06-2020.
 */

//@Entity(tableName = "Recipe")
//data class Recipe (@PrimaryKey(autoGenerate = true)
//                   @ColumnInfo(name = "recipeId")
//                   val recipeId: Long = 0,
//                   @ColumnInfo(name = "recipeTitle")
//                   var recipeTitle: String)

@Entity(
    tableName = "recipe"
//    , indices = [(Index(value = ["recipeId"], name = "idx_recipes_recipe_id"))],
//    foreignKeys = [(ForeignKey(
//        entity = Tag::class,
//        parentColumns = ["tag"],
//        childColumns = ["tagId"],
//        onUpdate = ForeignKey.CASCADE,
//        onDelete = ForeignKey.CASCADE
//    ))]
)
data class Recipe(
    @ColumnInfo(name = "recipeTitle") val title: String,
    @ColumnInfo(name = "recipeIngredients") val ingredients: String,
    @ColumnInfo(name = "recipeDetails") val details: String,
    @ColumnInfo(name = "recipeTips") val tips: String,
    @ColumnInfo(name = "recipeServings") val servings: String,
    @ColumnInfo(name = "tagId") val tagId: Int
) {
    @ColumnInfo(name = "recipeId")
    @PrimaryKey(autoGenerate = true)
    var recipeId: Int = 0
}
//
//@PrimaryKey(autoGenerate = true)
//@ColumnInfo(name = "recipeId")
//val recipeId: Long,
//@ColumnInfo(name = "tagId")
//val tagId: Long,
//@ColumnInfo(name = "recipeTitle")
//val title: String,
//@ColumnInfo(name = "recipeIngredients")
//val ingredients: String,
//@ColumnInfo(name = "recipeDetails")
//val details: String,
//@ColumnInfo(name = "recipeTips")
//val tips: String,
//@ColumnInfo(name = "recipeServings")
//val servings: String
//)