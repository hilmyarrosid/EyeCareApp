package com.example.eyecareapp.ui.components.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eyecareapp.ui.theme.EyeCareAppTheme

@Composable
fun TestContent () {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ){
        Text(
            text = "TEST",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        )
        Text(
            text = "Pilih test yang anda inginkan",
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 10.sp
            )
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .width(300.dp)
                .height(50.dp),
            shape = RoundedCornerShape(5.dp)
        ) {
            Text(
                text = "Colour Blind Test",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            )
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .width(300.dp)
                .height(50.dp),
            shape = RoundedCornerShape(5.dp),
        ) {
            Text(
                text = "Hypomia/Hypermopia Test",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun prevTestContent(){
    EyeCareAppTheme {
        TestContent()
    }
}