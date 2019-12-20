package e.mi.fotra.koin

import com.squareup.moshi.Moshi
import e.mi.fotra.api.TranslateService
import e.mi.fotra.api.WebProvider
import e.mi.fotra.api.ForumService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiModule {

    val module = module {
        single<Converter.Factory> { MoshiConverterFactory.create(get<Moshi>()) }
        single<OkHttpClient> { WebProvider.generateOkHttpClient() }
        single<Retrofit> { WebProvider.generateRetrofit(httpClient = get(), jsonConvertedFactory = get()) }
        single<TranslateService> { WebProvider.generateTranslateService(retrofit = get()) }
        single<ForumService> { WebProvider.generateForumService(retrofit = get())}
    }
}