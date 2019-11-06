package e.mi.FoTra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var router: MainRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        setSupportActionBar(toolbar)

        router = if (resources.getBoolean(R.bool.isTablet)) {
            MainRouterTablet(supportFragmentManager)
        } else {
            MainRouterPhone(supportFragmentManager)
        }

        router.openTranslator()

        setBottomNavigation()
    }

    private fun setBottomNavigation() {
        main_navigation.setOnNavigationItemSelectedListener { menuItem ->
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
