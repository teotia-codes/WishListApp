package com.example.wishlistapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wishlistapp.Data.Wish
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailView(
    id: Long,
   viewModel: WishViewModel,
    navController: NavController
    ){
    val snackMessage = remember{
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope() //scope helps us to run asyn fun
    val scaffoldState  = rememberScaffoldState()
    if(id != 0L){
        val wish = viewModel.getAWishById(id).collectAsState(initial = Wish(0L, "", ""))
        viewModel.wishDescription = wish.value.description
        viewModel.wishTitleState = wish.value.title
    }else{
        viewModel.wishTitleState = ""
        viewModel.wishDescription = ""
    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
        AppBarView(title =
            if(id != 0L) "Update Wish"
        else "Add Wish"        )

        {
            navController.navigateUp()
        }
    },

    ) {
         Column (
             modifier = Modifier
                 .padding(it)
                 .wrapContentSize(),
             horizontalAlignment = Alignment.CenterHorizontally,
             verticalArrangement = Arrangement.Center
         ){
           Spacer(modifier = Modifier.height(10.dp))
       WishTextField(label = "Title" ,
           value =viewModel.wishTitleState ,
           onValueChanged = {
               viewModel.onWishTitleChanged(it)})
             Spacer(modifier = Modifier.height(10.dp))
             WishTextField(label = "Description" ,
                 value =viewModel.wishDescription ,
                 onValueChanged = {
                     viewModel.onWishDesChanged(it)})
             Spacer(modifier = Modifier.height(10.dp))
             ElevatedButton(onClick = {
                 if(viewModel.wishTitleState.isNotEmpty()
                     && viewModel.wishDescription.isNotEmpty()){
                    if(id != 0L){
                        //TODO Update wish
                       viewModel.updateWish(Wish(
                           id = id,
                           title = viewModel.wishTitleState.trim(),
                           description = viewModel.wishDescription.trim()
                       ))
                    }else {
                        //add a wish
                        viewModel.addWish(
                            Wish(
                                title = viewModel.wishTitleState.trim(),
                                description = viewModel.wishDescription.trim()
                            )
                        )
                        snackMessage.value = "Wish created"
                    }


                 }else{
                    snackMessage.value = "Fill the fields to create a wish"
                 }
                 scope.launch {
                   //  scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                     navController.navigateUp()
                 }
             }) {
                 Text(text =             if(id != 0L) "Update Wish"
                 else "Add Wish"       ,
                     fontSize = 18.sp)

             }
         }

    }
    }



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishTextField(
    label: String,
    value: String,
    onValueChanged: (String) ->Unit
){
    OutlinedTextField(value = value, onValueChange = onValueChanged,
        label = {
            Text(text = label,
                color = Color.Black)
        },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.black),
            unfocusedBorderColor = colorResource(id = R.color.black),
            cursorColor = colorResource(id = R.color.black),
            focusedLabelColor = colorResource(id = R.color.black),
            unfocusedLabelColor = colorResource(id = R.color.black),
        )
    )
}

@Preview
@Composable
fun WishTextFieldPrev(){
    WishTextField(label = "text",
        value = "1",
       onValueChanged = {} )
}