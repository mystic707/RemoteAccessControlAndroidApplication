package com.mysticagit.test.toucheventgenerator

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import com.mysticagit.test.toucheventgenerator.ui.theme.ToucheventgeneratorTheme
import java.io.InputStream
import java.io.OutputStream
import java.net.Socket

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToucheventgeneratorTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                    DrawButtons(object : TouchedKeyListener {
                        override fun onTouchedKey(key: String) {
                            Log.d("mysticagit", "clicked key : $key")

                            // TODO :
                            sendMessage(key)
                        }
                    })
//                    DrawMappingButtons(object : TouchedKeyListener {
//                        override fun onTouchedKey(key: String) {
//                            Log.d("mysticagic", "clicked key : $key")
//
//                            // TODO :
//                            sendMessage(key)
//                        }
//                    })
                }
            }
        }
    }

    private fun sendMessage(message: String) {
        val SERVER_IP = "10.10.113.81"  // TODO : 서버 ip 주소
        val SERVER_PORT = 12345

        Thread {
            try {
                Socket(SERVER_IP, SERVER_PORT).use { socket ->
                    val output: OutputStream = socket.getOutputStream()
                    val input: InputStream = socket.getInputStream()

                    // 서버에 메시지 전송
                    output.write(message.toByteArray())
                    output.flush()

                    // 서버로부터 응답 수신
                    val buffer = ByteArray(1024)
                    val bytesRead = input.read(buffer)
                    val response = String(buffer, 0, bytesRead)

                    Log.d("mysticagit", "sendMessage response : $response")
                    runOnUiThread {
                        val toast = Toast.makeText(this, response, Toast.LENGTH_LONG)
                        toast.show()
                    }
                }
            } catch (e: Exception) {
                Log.d("mysticagit", "sendMessage exception : $e")
            }
        }.start()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ToucheventgeneratorTheme {
        Greeting("Android")
    }
}

@Composable
fun DrawButtons(keyListener: TouchedKeyListener) {
    var counter1 by remember { mutableStateOf(0) }
    var counter2 by remember { mutableStateOf(0) }
    var counter3 by remember { mutableStateOf(0) }
    var counter4 by remember { mutableStateOf(0) }

    var counter5 by remember { mutableStateOf(0) }
    var counter6 by remember { mutableStateOf(0) }

    var counterW by remember { mutableStateOf(0) }
    var counterA by remember { mutableStateOf(0) }
    var counterD by remember { mutableStateOf(0) }
    var counterS by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Direction
        Text(text = "Direction")
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                counter1++
                keyListener.onTouchedKey("up")},
            modifier = Modifier.wrapContentWidth().wrapContentHeight()) {
            Text(text = "Up : $counter1")
        }
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    counter3++
                    keyListener.onTouchedKey("left")},
                modifier = Modifier.wrapContentWidth().wrapContentHeight()) {
                Text(text = "Left : $counter3")
            }
            Spacer(modifier = Modifier.width(10.dp))

            Button(
                onClick = {
                    counter4++
                    keyListener.onTouchedKey("right")},
                modifier = Modifier.wrapContentWidth().wrapContentHeight()) {
                Text(text = "Right : $counter4")
            }
            Spacer(modifier = Modifier.height(10.dp))
        }

        Button(
            onClick = {
                counter2++
                keyListener.onTouchedKey("down")},
            modifier = Modifier.wrapContentWidth().wrapContentHeight()) {
            Text(text = "Down : $counter2")
        }
        Spacer(modifier = Modifier.height(10.dp))

        // Action
        Text(text = "Action")
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                counter5++
                keyListener.onTouchedKey("ActionA")},
            modifier = Modifier.wrapContentWidth().wrapContentHeight()) {
            Text(text = "ActionA : $counter5")
        }
        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                counter6++
                keyListener.onTouchedKey("ActionB")},
            modifier = Modifier.wrapContentWidth().wrapContentHeight()) {
            Text(text = "ActionB : $counter6")
        }
        Spacer(modifier = Modifier.height(10.dp))

        // Direction Mapping
        Text(text = "Direction Mapping")
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                counterW++
                keyListener.onTouchedKey("w")},
            modifier = Modifier.wrapContentWidth().wrapContentHeight()) {
            Text(text = "w : $counterW")
        }
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    counterA++
                    keyListener.onTouchedKey("a")},
                modifier = Modifier.wrapContentWidth().wrapContentHeight()) {
                Text(text = "a : $counterA")
            }
            Spacer(modifier = Modifier.width(10.dp))

            Button(
                onClick = {
                    counterD++
                    keyListener.onTouchedKey("d")},
                modifier = Modifier.wrapContentWidth().wrapContentHeight()) {
                Text(text = "d : $counterD")
            }
            Spacer(modifier = Modifier.height(10.dp))
        }

        Button(
            onClick = {
                counterS++
                keyListener.onTouchedKey("s")},
            modifier = Modifier.wrapContentWidth().wrapContentHeight()) {
            Text(text = "s : $counterS")
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun DrawMappingButtons(keyListener: TouchedKeyListener) {
    var counter1 by remember { mutableStateOf(0) }
    var counter2 by remember { mutableStateOf(0) }
    var counter3 by remember { mutableStateOf(0) }
    var counter4 by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Direction Mapping
        Text(text = "Direction Mapping")
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                counter1++
                keyListener.onTouchedKey("w")},
            modifier = Modifier.wrapContentWidth().wrapContentHeight()) {
            Text(text = "w : $counter1")
        }
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    counter3++
                    keyListener.onTouchedKey("a")},
                modifier = Modifier.wrapContentWidth().wrapContentHeight()) {
                Text(text = "a : $counter3")
            }
            Spacer(modifier = Modifier.width(10.dp))

            Button(
                onClick = {
                    counter4++
                    keyListener.onTouchedKey("d")},
                modifier = Modifier.wrapContentWidth().wrapContentHeight()) {
                Text(text = "d : $counter4")
            }
            Spacer(modifier = Modifier.height(10.dp))
        }

        Button(
            onClick = {
                counter2++
                keyListener.onTouchedKey("s")},
            modifier = Modifier.wrapContentWidth().wrapContentHeight()) {
            Text(text = "s : $counter2")
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}

interface TouchedKeyListener {
    fun onTouchedKey(key: String)
}