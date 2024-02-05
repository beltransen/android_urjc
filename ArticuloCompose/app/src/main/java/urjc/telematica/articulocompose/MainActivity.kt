package urjc.telematica.articulocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import urjc.telematica.articulocompose.ui.theme.ArticuloComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArticuloComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ComposeArticle(
                        stringResource(id = R.string.article_title),
                        stringResource(id = R.string.article_subtitle),
                        stringResource(id = R.string.article_text),
                        Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun ComposeArticle(title: String, brief: String, fulltext:String, modifier: Modifier = Modifier){
    Column(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_compose_background),
            contentDescription = null
        )
        Column {
            Text(
                text = title,
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = brief,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = fulltext,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticuloComposePreview() {
    ArticuloComposeTheme {
        ComposeArticle(
            stringResource(id = R.string.article_title),
            stringResource(id = R.string.article_subtitle),
            stringResource(id = R.string.article_text),
            Modifier.fillMaxSize()
        )
    }
}