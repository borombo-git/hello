package com.borombo.hello

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object FormulaManager {

    private var multiplierOne: Int = 0
    private var multiplierTwo: Int = 0
    private var limit: Long = 0
    private var wordOne: String = ""
    private var wordTwo: String = ""

    /**
     * @param multiplierOne The first multiplier
     * @param multiplierTwo The second multiplier
     * @param limit The limit for the list
     * @param wordOne The first word to replace the multiplier of the multiplierOne
     * @param wordTwo The second word to replace the multiplier of the multiplierTwo
     *
     * Save the value of the form to generate the list
     */
    fun saveValues(
        multiplierOne: Int, multiplierTwo: Int,
        limit: Long,
        wordOne: String, wordTwo: String){
        this.multiplierOne = multiplierOne
        this.multiplierTwo = multiplierTwo
        this.limit = limit
        this.wordOne = wordOne
        this.wordTwo = wordTwo
    }

    /**
     * Generate a list which follows the principles of the Fizz-Buzz List and update the LiveData
     * list accordingly
     */
    fun generateItems(start: Long, threshold: Long) : Flow<String> = flow {
        // Cap the end to the limit if needed
        val end = if(threshold > limit) limit else threshold
        for(i in start..end){
            when{
                isMultiple(i, multiplierOne) && isMultiple(i, multiplierTwo) -> emit("$wordOne$wordTwo")
                isMultiple(i, multiplierOne) -> emit(wordOne)
                isMultiple(i, multiplierTwo) -> emit(wordTwo)
                else -> emit("$i")
            }
        }
    }

    /**
     * @param value The value to check is is multiple
     * @param multiplier the multiplier to check against
     * @return True if value is a multiple of multiplier, false otherwise
     */
    private fun isMultiple(value: Long, multiplier: Int): Boolean {
        return ((value % multiplier).toInt() == 0)
    }

}