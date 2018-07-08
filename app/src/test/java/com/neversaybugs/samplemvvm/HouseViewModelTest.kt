package com.neversaybugs.samplemvvm

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import samplemvvm.data.entities.response.HouseResponse
import samplemvvm.presentation.house.HouseViewModel
import samplemvvm.presentation.house.HouseViewModelImpl
import samplemvvm.usecase.GetHouseUseCase

class HouseViewModelTest {

    private lateinit var viewModel: HouseViewModel
    private val useCase: GetHouseUseCase = mock()

    @Before
    fun setup() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        viewModel = HouseViewModelImpl(useCase)
    }

    @After
    fun reset() {
        RxJavaPlugins.reset()
    }

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

        doReturn(Observable.just(response)).whenever(useCase).getListHouse()

        // you have to prepare this line before you assert
        // if not, when you call onNext it will immediately call to VM and you will miss the result
        val testObserver = viewModel.output.houseList.test()

        // when
        viewModel.input.loadData.onNext(Unit)

        // then
        testObserver.assertValue(listOf(data))
    }
}