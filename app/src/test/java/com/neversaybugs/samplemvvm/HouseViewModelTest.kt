package com.neversaybugs.samplemvvm

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import samplemvvm.models.APIs.ApiProvider
import samplemvvm.models.entities.HouseResponse
import samplemvvm.presentations.house.HouseViewModel
import samplemvvm.presentations.house.HouseViewModelImpl

class HouseViewModelTest {

    private lateinit var viewModel: HouseViewModel
    private val api: ApiProvider = mock()

    @Before
    fun setup() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        viewModel = HouseViewModelImpl(api)
    }

    @After
    fun reset() { RxJavaPlugins.reset() }

    @Test
    fun testSearch_Success() {

        // stuff
        var data = HouseResponse.House().apply {
            name = "name"
            castleName = "castleName"
        }
        val response = HouseResponse()
        response.data = listOf(data)

        // given

        doReturn(Observable.just(response)).whenever(api).getData()

        // you have to prepare this line before you assert
        // if not, when you call onNext it will immediately call to VM and you will miss the result
        val testObserver = viewModel.output.houseList.test()

        // when
        viewModel.input.loadData.onNext(Unit)

        // then
        testObserver.assertValue(listOf(data))
    }
}