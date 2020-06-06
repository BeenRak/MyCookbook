package virabe.example.mycookbook.detail

//import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import org.koin.androidx.viewmodel.ext.android.viewModel
import virabe.example.mycookbook.R
import virabe.example.mycookbook.database.Recipe
import virabe.example.mycookbook.list.RecipeViewModel
import kotlin.coroutines.CoroutineContext

/**
 * Created by Beenal Patel on 05-06-2020.
 */
class DetailActivity : AppCompatActivity() {

    //    override fun getViewModelBindingVariable(): Int {
//        return NO_VIEW_MODEL_BINDING_VARIABLE
//    }
    private val viewModel: RecipeViewModel by viewModel()

//    fun getLayoutId(): Int {
//        return R.layout.activity_detail
//    }

    private lateinit var recipeId: String
    lateinit var recipe: Recipe

//    var recipeDatabase: RecipeDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recipeId = intent.getIntExtra("recipe_id", 0).toString()

        Log.v("Recipe ID", recipeId)
//        recipe = viewModel.getDataById(recipeId.toInt())

        viewModel.getDataById(recipeId.toInt()).observe(this, Observer {
            recipe = it!!
            displayRecipe(it)
        })

        viewModel.triggerFetchData(recipeId.toInt())

//        recipeDatabase = RecipeDatabase.getInstance(this)
    }

    private fun displayRecipe(recipe: Recipe) {
        tvTitle.text = "Title : ${recipe?.title}"
        tvServings.text = "Servings : ${recipe?.servings}"
        tvIngredient.text = "Ingredients : ${recipe?.ingredients}"
        tvDetails.text = "Details : ${recipe?.details}"
        tvTips.text = "Tips : ${recipe?.tips}"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

//    @SuppressLint("InflateParams")
//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        when(item?.itemId){
//            R.id.delete -> {
//                getViewModel().deleteData(student)
//                Toast.makeText(this, "Data  ${student.name} berhasil dihapus", Toast.LENGTH_LONG).show()
//                finish()
//            }
//            R.id.edit -> {
//                val dialogBuilder = AlertDialog.Builder(this)
//                val view = layoutInflater.inflate(R.layout.input_dialog, null)
//                dialogBuilder.setView(view)
//                dialogBuilder.setTitle("Masukkan data baru")
//                val et_name = view.ed_student_name
//                val et_nim = view.ed_student_id
//                val radioGroupGender = view.radio_group_gender
//                dialogBuilder.setPositiveButton("Tambahkan") { _: DialogInterface, _: Int ->
//                    val studentName = et_name.text
//                    val studentNim = et_nim.text
//                    val gender: String
//                    val selectedRadioButton = radioGroupGender.checkedRadioButtonId
//                    gender = when (selectedRadioButton) {
//                        R.id.radio_female -> "Perempuan"
//                        else -> "Laki-laki"
//                    }
//                    getViewModel().updateData(student.id, studentName.toString(), studentNim.toString(), gender)
//                    Toast.makeText(this, "Data berhasil diubah $studentName ", Toast.LENGTH_LONG).show()
//                }
//                dialogBuilder.setNegativeButton("Batal") { _: DialogInterface, _: Int ->
//                }
//                dialogBuilder.show()
//            }
//            android.R.id.home -> {
//                onBackPressed()
//            }
//        }
//        return true
//    }
}
