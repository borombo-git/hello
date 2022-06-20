package com.borombo.hello.form

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import com.borombo.hello.FormulaManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor() : ViewModel() {

    // Enum to know which position we modify
    enum class POSITION{
        ONE,
        TWO
    }

    // State to know if the form is valid
    private var _formIsValid = mutableStateOf(false)
    val formIsValid: State<Boolean>
        get() = _formIsValid

    // Variables of the form
    private var multiplierOne: Int = 0
    private var multiplierTwo: Int = 0
    private var limit: Long = 0
    private var textOne: String = ""
    private var textTwo: String = ""

    /**
     * @param position The position of the multiplier (One or Two)
     * @param value The value of the multiplier
     * This function simply save the value of the multiplier in the right variable and check the
     * form validity
     */
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

    /**
     * @param value The limit value
     * Simply save the limit value and check the form validity
     */
    fun setLimit(value: String){
        limit = if(value.isDigitsOnly()){
            value.toLong()
        } else{
            0
        }
        checkFormValidity()
    }

    /**
     * @param position The position of the multiplier (One or Two)
     * @param value The value of the text
     * This function simply save the value of the text in the right variable and check the form
     * validity
     */
    fun setText(position: POSITION, value: String){
        if(position == POSITION.ONE){
            textOne = value
        }else{
            textTwo = value
        }
        checkFormValidity()
    }

    /**
     * Check that none of the value of the form is null or empty
     */
    private fun checkFormValidity(){
        val multiplierOneIsValid = multiplierOne != 0
        val multiplierTwoIsValid = multiplierTwo != 0
        val limitIsValid = limit != 0L
        val textOneIsValid = textOne.isNotEmpty()
        val textTwoIsValid = textTwo.isNotEmpty()

        _formIsValid.value = (multiplierOneIsValid && multiplierTwoIsValid && limitIsValid && textOneIsValid && textTwoIsValid)
    }

    /**
     * Call the manager to generate the list with all the params needed from the form
     */
    fun generateList(){
        FormulaManager.saveValues(multiplierOne, multiplierTwo, limit, textOne, textTwo)
    }
}