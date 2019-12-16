package e.mi.fotra

import android.app.Application
import e.mi.fotra.koin.KoinInitializer

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        KoinInitializer.initKoinGraph(this)
    }
}