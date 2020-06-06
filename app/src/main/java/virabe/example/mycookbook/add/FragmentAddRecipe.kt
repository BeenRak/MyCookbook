package virabe.example.mycookbook.add

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_add_recipe.*
import virabe.example.mycookbook.R

/**
 * Created by Beenal Patel on 02-06-2020.
 */
class FragmentAddRecipe : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_recipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonSave.setOnClickListener {
            val title = editWordTitle.text.toString()
            val servings = editWordServings.text.toString()
            val ingredients = editWordIngredients.text.toString()
            val details = editWordDetails.text.toString()
            val tips = editWordTips.text.toString()
//val
            activity?.apply {
                when {
                    title.isBlank() -> setResult(Activity.RESULT_CANCELED, Intent())
                    else -> setResult(
                        Activity.RESULT_OK,
                        Intent().apply {
                            putExtra(TITLE, title);putExtra(
                            SERVINGS,
                            servings
                        );putExtra(INGREDIANTS, ingredients);putExtra(
                            DETAILS,
                            details
                        );putExtra(TIPS, tips)
                        })
                }
                finish()
            }
        }

//        buttonSave.setOnClickListener(
//            View.OnClickListener {
//
//            }
//        )
//        editWordDetails.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
//            val hasEnterOrGo =
//                keyCode == KeyEvent.KEYCODE_ENTER || keyCode == EditorInfo.IME_ACTION_GO
//            return@OnKeyListener when (event.action == KeyEvent.ACTION_DOWN && hasEnterOrGo) {
//                true -> buttonSave.callOnClick().let { true }
//                false -> false
//            }
//        })
    }

    companion object {
        const val TITLE = "title"
        const val SERVINGS = "servings"
        const val INGREDIANTS = "ingrediants"
        const val DETAILS = "details"
        const val TIPS = "tips"



    }
}
