package e.mi.fotra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import e.mi.fotra.viewmodel.TranslateViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var router: MainRouter
    private val model: TranslateViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        setSupportActionBar(toolbar)

        router = if (resources.getBoolean(R.bool.isTablet)) {
            MainRouterTablet(supportFragmentManager)
        } else {
            MainRouterPhone(supportFragmentManager)
        }

        if (savedInstanceState == null) {
            router.openTranslator()
        }


        setBottomNavigation()

    }

    private fun setBottomNavigation() {

        main_navigation.setOnNavigationItemSelectedListener { menuItem ->

            model.setTranslatedText("")

            when (menuItem.itemId) {
                R.id.menu_translator -> {
                    router.openTranslator()
                    true
                }
                R.id.menu_forum -> {
                    router.openForum()
                    true
                }
                R.id.menu_profile -> {
                    router.openProfile()
                    true
                }
                else -> false
            }
        }
    }

}
