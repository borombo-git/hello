package com.borombo.hello.form

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import com.borombo.hello.FormulaManager
import kotlinx.coroutines.flow.StateFlow

class FormViewModel: ViewModel() {

    enum class POSITION{
        ONE,
        TWO
    }

    private var _formIsValid = mutableStateOf(false)
    val formIsValid: State<Boolean>
        get() = _formIsValid

    private var multiplierOne: Int = 0
    private var multiplierTwo: Int = 0
    private var limit: Int = 0
    private var textOne: String = ""
    private var textTwo: String = ""

    fun setMultiplier(position: POSITION, value: String){
        if(value.isDigitsOnly()){
            if(position == POSITION.ONE){
                multiplierOne = value.toInt()
            }else{
                multiplierTwo = value.toInt()
            }
        }else{
            if(position == POSITION.ONE){
                multiplierOne = 0
            }else{
                multiplierTwo = 0
            }
        }
        checkFormValidity()
    }

    fun setLimit(value: String){
        limit = if(value.isDigitsOnly()){
            value.toInt()
        } else{
            0
        }
        checkFormValidity()
    }

    fun setText(position: POSITION, value: String){
        if(position == POSITION.ONE){
            textOne = value
        }else{
            textTwo = value
        }
        checkFormValidity()
    }

    private fun checkFormValidity(){
        val multiplierOneIsValid = multiplierOne != 0
        val multiplierTwoIsValid = multiplierTwo != 0
        val limitIsValid = limit != 0
        val textOneIsValid = textOne.isNotEmpty()
        val textTwoIsValid = textTwo.isNotEmpty()

        _formIsValid.value = (multiplierOneIsValid && multiplierTwoIsValid && limitIsValid && textOneIsValid && textTwoIsValid)
    }

    fun generateList(){
        FormulaManager.generateList(multiplierOne, multiplierTwo, limit, textOne, textTwo)
    }
}