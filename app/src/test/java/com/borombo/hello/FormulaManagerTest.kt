@file:OptIn(ExperimentalCoroutinesApi::class)

package com.borombo.hello

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.borombo.hello.utils.MainCoroutineScopeRule
import com.borombo.hello.utils.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FormulaManagerTest {

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineScopeRule()

    private val multiplierOne = 3
    private val multiplierTwo = 5
    private val limit = 100L
    private val wordOne = "Fizz"
    private val wordTwo = "Buzz"

    private val items = mutableListOf<String>()

    @Before
    fun setup() {
        FormulaManager.saveValues(multiplierOne,multiplierTwo,limit,wordOne,wordTwo)
    }

    @Test
    fun generateItems_sizeIsCorrect() {
        mainCoroutineRule.runBlockingTest {
            FormulaManager.generateItems(1, 100)
                .collect(items::add)
            Assert.assertEquals(100, items.size)
        }
    }

    @Test
    fun generateItems_valuesAreCorrect() {
        mainCoroutineRule.runBlockingTest {
            FormulaManager.generateItems(1, 100)
                .collect(items::add)

            // We get the first FizzBuzz to check if it's a multiple of the two other values
            val index = items.indexOfFirst { value -> value == "$wordOne$wordTwo" }
            val firstDoubleMultiple = index + 1

            val multipleOfMultiplierOne = firstDoubleMultiple % multiplierOne == 0
            val multipleOfMultiplierTwo = firstDoubleMultiple % multiplierTwo == 0

            // -1 is used to get the right position based on the index (starting from 0)
            Assert.assertEquals(items[multiplierOne -1], wordOne)
            Assert.assertEquals(items[multiplierTwo -1], wordTwo)

            Assert.assertEquals(true, multipleOfMultiplierOne && multipleOfMultiplierTwo )
        }
    }
}