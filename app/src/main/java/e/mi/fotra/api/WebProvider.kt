package e.mi.fotra.api

import okhttp3.*
import retrofit2.Converter
import retrofit2.Retrofit
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object WebProvider {

    private var baseUrl = "https://fotra-server-app.herokuapp.com"
    private val token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJnb29keSIsInJvbGVzIjoiVVNFUiIsImlhdCI6MTU3OTYwMjQxNSwiZXhwIjoxNTgyMTk0NDE1fQ.ki6XW3CcINqmuUEuisA0ZAA3hJIq0G_sYz2swcVOAd8"

    fun generateOkHttpClient(): OkHttpClient {

        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {

            override fun checkClientTrusted(
                chain: Array<java.security.cert.X509Certificate>,
                authType: String
            ) {
            }

            override fun checkServerTrusted(
                chain: Array<java.security.cert.X509Certificate>,
                authType: String
            ) {
            }

            override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                return arrayOf()
            }
        })

        // Install the all-trusting trust manager
        var sslContext: SSLContext? = null

        try {
            sslContext = SSLContext.getInstance("SSL")
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        try {
            sslContext!!.init(null, trustAllCerts, java.security.SecureRandom())
        } catch (e: KeyManagementException) {
            e.printStackTrace()
        }

        // Create an ssl socket factory with our all-trusting manager
        val sslSocketFactory = sslContext!!.socketFactory

        val okHttpBuilder = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)

        val headerAuthorizationInterceptor = Interceptor { chain ->
            var request = chain.request()
            val headers: Headers =
                request.headers().newBuilder().add("Authorization", "Bearer_" + token)
                    .add("Content-Type", "application/json")
                    .build()
            request = request.newBuilder().headers(headers).build()
            chain.proceed(request)
        }


        okHttpBuilder.addInterceptor(headerAuthorizationInterceptor)
        okHttpBuilder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
        okHttpBuilder.hostnameVerifier { _, _ -> true }

        return okHttpBuilder.build()
    }

    fun generateRetrofit(
        httpClient: OkHttpClient,
        jsonConvertedFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(jsonConvertedFactory)
            .build()
    }

    fun generateTranslateService(retrofit: Retrofit): TranslateService {
        return retrofit.create(TranslateService::class.java)
    }

    fun generateForumService(retrofit: Retrofit): ForumService {
        return retrofit.create(ForumService::class.java)
    }
}