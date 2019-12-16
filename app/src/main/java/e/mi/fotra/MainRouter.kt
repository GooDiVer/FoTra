package e.mi.fotra

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import e.mi.fotra.navigationFragments.ForumFragment
import e.mi.fotra.navigationFragments.ProfileFragment
import e.mi.fotra.navigationFragments.TranslatorFragment

open class MainRouterPhone(
    private val fragmentManager: FragmentManager
) : MainRouter {

    override fun openTranslator() {
        open(TranslatorFragment.getInstance("Translator"))
    }

    override fun openForum() {
        open(ForumFragment.getInstance("Forum"))
    }

    override fun openProfile() {
        open(ProfileFragment.getInstance("Profile"))
    }

    override fun openSettings() {
        open(TranslatorFragment.getInstance("Settings"))
    }

    private fun open(fragment: Fragment) {
        fragmentManager.beginTransaction().replace(R.id.main_container, fragment).commit()
    }
}

class MainRouterTablet(
    fragmentManager: FragmentManager
) : MainRouterPhone(fragmentManager)

interface MainRouter {
    fun openTranslator()

    fun openForum()

    fun openProfile()

    fun openSettings()
}