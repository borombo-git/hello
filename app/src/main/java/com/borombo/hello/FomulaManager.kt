package com.borombo.hello

object FormulaManager {

    var list: MutableList<String> = mutableListOf()

    fun generateList(
        multiplierOne: Int,
        multiplierTwo: Int,
        limit: Int,
        wordOne: String,
        wordTwo: String,
    ){
        for(i in 1..10){
            list.add(i.toString())
        }
    }

    fun getFakeList(): List<String>{
        return listOf("1","2","3","4","5")
    }
}