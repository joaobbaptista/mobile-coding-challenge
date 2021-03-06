package com.example.joao.photoscodechallenge.home



import com.example.joao.photoscodechallenge.AcceptanceTest
import com.example.joao.photoscodechallenge.RequestInterceptorMock
import com.example.joao.photoscodechallenge.di.Injector
import com.example.joao.photoscodechallenge.robots.robot
import com.example.joao.photoscodechallenge.ui.MainActivity
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.provider
import okhttp3.Interceptor
import org.junit.Test

/**
 * Created by Joao Alvares Neto on 07/05/2018.
 */
class HomeActivityGenericErrorTest : AcceptanceTest<MainActivity>(MainActivity::class.java) {

    @Test
    fun testWithGenericErrorState() {

        startActivity()

        robot {

        } withGenericError {
            errorHasBeenShown()
        }
    }

    override val testDependencies = Kodein.Module(allowSilentOverride = true) {
        bind<Interceptor>(tag = Injector.REQUEST_INTERCEPTOR,overrides = true) with provider {
            RequestInterceptorMock(Exception())
        }
    }
}