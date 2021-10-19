package com.sample.openweather

import com.sample.openweather.common.DefaultDispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher

class TestDispatcherProvider : DefaultDispatcherProvider() {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    override fun main(): CoroutineDispatcher = testCoroutineDispatcher

    override fun default(): CoroutineDispatcher = testCoroutineDispatcher

    override fun io(): CoroutineDispatcher = testCoroutineDispatcher

    override fun unconfined(): CoroutineDispatcher = testCoroutineDispatcher
}
