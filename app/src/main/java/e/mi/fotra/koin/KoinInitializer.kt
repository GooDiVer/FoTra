package e.mi.fotra.koin

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

object KoinInitializer {

    fun initKoinGraph(applicationContext: Context) {
        startKoin {
            androidContext(applicationContext)
            androidLogger()

            modules(
                listOf(
                    ViewModelModule.module,
                    ApiModule.module,
                    MoshiModule.module,
                    LanguageModule.module,
                    ForumModule.module
                )
            )
        }
    }
}