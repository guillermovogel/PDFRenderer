package com.vogel.pdfrender

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.rememberVerticalPdfReaderState
import com.vogel.pdfrender.ui.theme.PDFRendererTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PDFRendererTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    PDFViewer("Términos y condiciones")
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PDFViewer(title: String) {
    val pdfState = rememberVerticalPdfReaderState(
        resource = ResourceType.Remote("https://myreport.altervista.org/Lorem_Ipsum.pdf"),
        isZoomEnable = true
    )

    Scaffold(topBar = {
        Row(
            modifier = Modifier
                .background(Color(0xFF000E31))
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
            Text(
                textAlign = TextAlign.Start,
                fontSize = 20.sp,
                modifier = Modifier.fillMaxWidth(),
                text = title,
                color = Color.White
            )
        }
    },

        content = {
            VerticalPDFReader(
                state = pdfState, modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Gray)
            )
        }
    )


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PDFRendererTheme {

        PDFViewer("Android")
    }
}