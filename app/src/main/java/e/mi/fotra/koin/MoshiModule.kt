package e.mi.fotra.koin

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module

object MoshiModule {
    val module = module {
        single<Moshi> {
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        }
    }
}