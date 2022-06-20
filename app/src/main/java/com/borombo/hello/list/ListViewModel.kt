package com.borombo.hello.list

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.borombo.hello.FormulaManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(): ViewModel() {

    // List of elements shown
    val valueList = mutableStateListOf<String>()
    // Job to create the new items
    private var formulaJob: Job? = null

    // Size of the item
    private val loadingSize: Long = 200

    // The offset of the list at which we start to load more items
    private val loadMoreOffset = 50

    // Save the number of "page" loaded
    private var pageLoaded = 1

    // Size of the currently loaded items
    private val itemThreshold: Long
        get() = pageLoaded * loadingSize

    // Threshold to start loading more items
    val loadMoreThreshold: Long
        get() = itemThreshold - loadMoreOffset

    init {
        // Launch first calculation on init
        launchFormulaCalculation()
    }

    /**
     * Start the job to load the new items
     */
    private fun launchFormulaCalculation(){
        // Calculate the start of the list based on page loaded (+1 to not start at 0)
        val start = (loadingSize * (pageLoaded-1)) +1
        formulaJob = viewModelScope.launch{
            FormulaManager.generateItems(start, itemThreshold)
                .flowOn(Dispatchers.Default)
                .cancellable()
                .collect(valueList::add)
        }
    }

    /**
     * Load more item in the list
     */
    fun loadMore(){
        pageLoaded++
        launchFormulaCalculation()
    }

    /**
     * Cancel the job if the VM is cleared
     */
    override fun onCleared() {
        formulaJob?.cancel()
        super.onCleared()
    }

}