package com.borombo.hello.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.borombo.hello.FormulaManager

class ListViewModel: ViewModel() {

    val list: LiveData<List<String>>
        get() = FormulaManager.list

}