package com.example.hello_ndk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hello_ndk.ui.theme.HelloNDKTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloNDKTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val jniBridge = JNIBridge()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = { TopAppBar(title = { Text(text = "HelloNDK") }) },
    ) {
        Row(modifier = modifier.padding(it)) {
            Spacer(modifier = Modifier.width(12.dp))
            Button(onClick = {
                scope.launch {
                    val ndkValue = jniBridge.helloWorldFromJNI(name)
                    snackbarHostState.showSnackbar(ndkValue)
                }
            }) {
                Text(
                    text = "Call C++",
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = {
                scope.launch {
                    jniBridge.makeJNICallVm()
                    snackbarHostState.showSnackbar("Check ANDROID tag in LogCat")
                }
            }) {
                Text(
                    text = "Make C++ call VM",
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HelloNDKTheme {
        Greeting("Android")
    }
}
