package com.borombo.hello.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.borombo.hello.FormulaManager

class ListViewModel: ViewModel() {

    private var _list = mutableStateOf(listOf<String>())
    val list: List<String>
        get() = _list.value


    init {
        _list.value = FormulaManager.getFakeList()
    }
}