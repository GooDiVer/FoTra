package e.mi.fotra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openTranslator()

        setBottomNavigation()

    }

    private fun setBottomNavigation() {
        bottom_navigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.translator -> {
                    openTranslator()
                    true
                }
                R.id.forum -> {
                    openForum()
                    true
                }

                R.id.profile -> {
                    openProfile()
                    true
                }
                else -> false
            }

        }
    }

    private fun openTranslator() {
        open(TranslatorFragment.getInstance("Translator"))
    }
    private fun openForum() {
        
    }
    private fun openProfile() {}

    private fun open(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment).commit()
    }
}
