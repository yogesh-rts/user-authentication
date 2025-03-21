package com.ykcoding.authenticationapp.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.ykcoding.authenticationapp.R
import com.ykcoding.authenticationapp.ui.theme.NativeAuthenticationAppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginActivity : ComponentActivity() {

    private val viewModel: LoginViewModel by viewModel()

    private val resources = object {
        val roundedCornerShape_10dp = RoundedCornerShape(10.dp)
        val spacing_16dp = 16.dp
        val spacing_8dp = 8.dp
        val textSize_36sp = 36.sp
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NativeAuthenticationAppTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(Color.Blue, Color.Cyan, Color.White),
                                center = Offset.Zero,
                                radius = 2500f
                            )
                        )
                ) {
                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Transparent),
                        containerColor = Color.Transparent,
                    ) { innerPadding ->
                        Layout(innerPadding)
                    }
                }
            }
        }
    }

    @Composable
    fun Layout(padding: PaddingValues) {

        val ctx = LocalContext.current
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(padding)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(
                    space = resources.spacing_16dp,
                    alignment = Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.authentication_loginScreen_title),
                    fontSize = resources.textSize_36sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    maxLines = 1,
                )
                OutlinedTextField(
                    value = username,
                    onValueChange =  { value ->
                        username = value
                    },
                    keyboardOptions = KeyboardOptions.Default,
                    visualTransformation = VisualTransformation.None,
                    leadingIcon = {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "Username Icon"
                        )
                    },
                    label = { Text(text = "Username") },
                    singleLine = true,
                    maxLines = 1,
                    shape = resources.roundedCornerShape_10dp
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { value ->
                        password = value
                    },
                    keyboardOptions = KeyboardOptions.Default,
                    leadingIcon = {
                        Icon(
                            Icons.Default.Lock,
                            contentDescription = "Username Icon"
                        )
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    label = { Text(text = "Password") },
                    singleLine = true,
                    maxLines = 1,
                    shape = resources.roundedCornerShape_10dp
                )
                Spacer(modifier = Modifier.height(resources.spacing_8dp))
                Button(
                    onClick = {
                        viewModel.login(username, password) {
                            Toast.makeText(
                                ctx,
                                "successfully logged-in",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = (4 * resources.spacing_16dp)),
                    colors = ButtonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White,
                        disabledContentColor = Color.LightGray,
                        disabledContainerColor = Color.Gray
                    )
                ) {
                    Text(text = stringResource(R.string.authentication_loginScreen_loginBtnLabel))
                }
            }

        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NativeAuthenticationAppTheme {
    }
}