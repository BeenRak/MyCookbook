package virabe.example.mycookbook.database

import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.experimental.builder.viewModel
//import org.koin.androidx.viewmodel.dsl.viewModel
//import org.koin.dsl.module
//import org.koin.dsl.module.module
import virabe.example.mycookbook.list.RecipeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
/**
 * Created by Beenal Patel on 04-06-2020.
 */

val recipeModule = module {
    factory { RecipeDatabase.getDatabase(androidApplication()).recipeDao() }
    factory { RecipeRepository(get()) }
    viewModel { RecipeViewModel(get()) }
}