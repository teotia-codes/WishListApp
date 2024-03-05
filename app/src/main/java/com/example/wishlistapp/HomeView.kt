package com.example.wishlistapp

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material.Card
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material.FloatingActionButton
import com.example.wishlistapp.Data.DummyWish
import com.example.wishlistapp.Data.Wish

@Composable
fun HomeView(){
    val context = LocalContext.current
    Scaffold(

        topBar = {AppBarView(title = "WishList",
            {
                Toast.makeText(context, "Button Clicked", Toast.LENGTH_LONG).show()

            })},
        floatingActionButton = {
     FloatingActionButton(
                modifier = Modifier.padding(all = 20.dp),
                contentColor = Color.White,
                backgroundColor = Color(0xFF03DAC5),
                onClick = {
                    Toast.makeText(context,"FAB Clicked",Toast.LENGTH_LONG).show()
                },
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        },
    ) {
  LazyColumn(modifier = Modifier
      .fillMaxSize()
      .padding(it)){
   items(DummyWish.wishList){
       wish-> WishItem(wish = wish) {
//
   }
   }
  }
    }
}
@Composable
fun WishItem(wish: Wish, onClick: () -> Unit){
     Card(modifier = Modifier
         .fillMaxWidth()
         .padding(
             top = 8.dp,
             start = 8.dp,
             end = 8.dp
         )
         .clickable {
             onClick()
         },
              elevation = 10.dp,
              backgroundColor = Color.White
          ) {
         Column(
             modifier = Modifier.padding(16.dp)
         ) {

             Text(
                 text = wish.title,
                 fontWeight = FontWeight.ExtraBold
             )
             Text(text = wish.description)
         }

          }
}