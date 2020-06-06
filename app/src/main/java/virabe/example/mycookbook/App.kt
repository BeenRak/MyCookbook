package virabe.example.mycookbook

import android.app.Application
import org.koin.android.ext.android.startKoin
//import org.koin.core.context.startKoin
import virabe.example.mycookbook.database.recipeModule

class App : Application() {
  override fun onCreate() {
    super.onCreate()
//    startKoin {  }
    startKoin(this, listOf(recipeModule))
  }
}