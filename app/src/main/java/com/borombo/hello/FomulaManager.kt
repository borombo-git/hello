package com.borombo.hello

import androidx.lifecycle.MutableLiveData

object FormulaManager {

    private var _list: MutableList<String> = mutableListOf()
    val list: MutableLiveData<List<String>> = MutableLiveData()

    /**
     * @param multiplierOne The first multiplier
     * @param multiplierTwo The second multiplier
     * @param limit The limit for the list
     * @param wordOne The first word to replace the multiplier of the multiplierOne
     * @param wordTwo The second word to replace the multiplier of the multiplierTwo
     *
     * Generate a list which follows the principles of the Fizz-Buzz List and update the LiveData
     * list accordingly
     */
    fun generateList(
        multiplierOne: Int, multiplierTwo: Int,
        limit: Int,
        wordOne: String, wordTwo: String){
        for(i in 1..limit){
            when{
                isMultiple(i, multiplierOne) && isMultiple(i, multiplierTwo) -> _list.add("$wordOne$wordTwo")
                isMultiple(i, multiplierOne) -> _list.add(wordOne)
                isMultiple(i, multiplierTwo) -> _list.add(wordTwo)
                else -> _list.add("$i")
            }
        }
        list.postValue(_list)
    }


    /**
     * @param value The value to check is is multiple
     * @param multiplier the multiplier to check against
     * @return True if value is a multiple of multiplier, false otherwise
     */
    private fun isMultiple(value: Int, multiplier: Int): Boolean {
        return (value % multiplier == 0)
    }

}