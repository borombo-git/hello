package com.borombo.hello.form

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.borombo.hello.R
import com.borombo.hello.ui.theme.HelloTheme
import com.borombo.hello.ui.theme.KeyboardNumberNext

@Composable
fun FormulaFormScreen(navController: NavController, formViewModel: FormViewModel = hiltViewModel()){
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)) {
        FormTitle()
        MultipliersForm({
            formViewModel.setMultiplier(FormViewModel.POSITION.ONE ,it)
        },{
            formViewModel.setMultiplier(FormViewModel.POSITION.TWO ,it)
        })
        LimitForm{formViewModel.setLimit(it)}
        TextsForm({
            formViewModel.setText(FormViewModel.POSITION.ONE, it)
        }, {
            formViewModel.setText(FormViewModel.POSITION.TWO, it)
        })
        Spacer(modifier = Modifier.weight(1.0f))
        FormButton(formViewModel.formIsValid){
            formViewModel.generateList()
            navController.navigate("list")
        }
    }
}

@Composable
fun FormButton(formIsValid: State<Boolean>, onClick: () -> Unit){
    Button(
        onClick = onClick,
        enabled = formIsValid.value,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Text(text = "Submit")
    }
}

@Composable
fun FormTitle(){
    Text(
        text = stringResource(id = R.string.formula_title),
        fontSize = 48.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun MultipliersForm(
    onMultiplierOneChanged: (String) -> Unit,
    onMultiplierTwoChanged: (String) -> Unit,
){
    var multiplierOne by rememberSaveable { mutableStateOf(TextFieldValue()) }
    var multiplierTwo by rememberSaveable { mutableStateOf(TextFieldValue()) }

    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = stringResource(id = R.string.multipliers))
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier) {
            TextField(
                value = multiplierOne,
                onValueChange = { value ->
                    multiplierOne = value.copy(text = value.text.filter { it.isDigit() })
                    if(value.text.isNotEmpty())
                        onMultiplierOneChanged(value.text)
                },
                placeholder = {
                    Text(stringResource(id = R.string.hint_multiplier_1))
                },
                keyboardOptions = KeyboardNumberNext,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            TextField(
                value = multiplierTwo,
                onValueChange = { value ->
                    multiplierTwo = value.copy(text = value.text.filter { it.isDigit() })
                    if(value.text.isNotEmpty())
                        onMultiplierTwoChanged(value.text)
                },
                placeholder = {
                    Text(stringResource(id = R.string.hint_multiplier_2))
                },
                keyboardOptions = KeyboardNumberNext,
                modifier = Modifier.weight(1f)
            )
        }

    }
}

@Composable
fun LimitForm(
    onLimitChanged: (String) -> Unit,
){
    var limit by rememberSaveable { mutableStateOf(TextFieldValue()) }

    Column(modifier = Modifier
        .padding(8.dp)
        .wrapContentHeight()) {
        Text(text = stringResource(id = R.string.limit))
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = limit,
            onValueChange = { value ->
                limit = value.copy(text = value.text.filter { it.isDigit() })
                if(value.text.isNotEmpty())
                    onLimitChanged(value.text)
            },
            placeholder = {
                Text(stringResource(id = R.string.hint_limit))
            },
            keyboardOptions = KeyboardNumberNext,
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextsForm(
    onTextOneChanged: (String) -> Unit,
    onTextTwoChanged: (String) -> Unit,
){
    var textOne by rememberSaveable { mutableStateOf(TextFieldValue()) }
    var textTwo by rememberSaveable { mutableStateOf(TextFieldValue()) }
    val keyboardController = LocalSoftwareKeyboardController.current


    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = stringResource(id = R.string.texts))
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier) {
            TextField(
                value = textOne,
                onValueChange = { value ->
                    textOne = value
                    if(value.text.isNotEmpty())
                        onTextOneChanged(value.text)
                },
                placeholder = {
                    Text(stringResource(id = R.string.hint_word_1))
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            TextField(
                value = textTwo,
                onValueChange = { value ->
                    textTwo = value
                    if(value.text.isNotEmpty())
                        onTextTwoChanged(value.text)
                },
                placeholder = {
                    Text(stringResource(id = R.string.hint_word_2))
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {keyboardController?.hide()}),
                modifier = Modifier.weight(1f)
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HelloTheme {
        FormulaFormScreen(rememberNavController())
    }
}