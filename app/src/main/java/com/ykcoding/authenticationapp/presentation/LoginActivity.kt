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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.ykcoding.authenticationapp.R
import com.ykcoding.authenticationapp.composables.OutlinedTextFieldWithError
import com.ykcoding.authenticationapp.ui.theme.NativeAuthenticationAppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginActivity : ComponentActivity() {

    private val viewModel: LoginViewModel by viewModel()

    private val resources = object {
        val roundedCornerShape_10dp = RoundedCornerShape(10.dp)
        val spacing_16dp = 16.dp
        val spacing_8dp = 8.dp
        val textSize_36sp = 36.sp
        val textSize_24sp = 24.sp
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NativeAuthenticationAppTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        /*.background(
                            brush = Brush.radialGradient(
                                colors = listOf(Color.Blue, Color.Cyan, Color.White),
                                center = Offset.Zero,
                                radius = 2500f
                            )
                        )*/
                ) {
                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize(),
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
                modifier = Modifier.fillMaxSize().padding(resources.spacing_16dp),
                verticalArrangement = Arrangement.spacedBy(
                    space = 0.dp,
                    alignment = Alignment.Top
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.authentication_loginScreen_welcomeTitleText),
                    fontSize = resources.textSize_36sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    maxLines = 1,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(resources.spacing_16dp)
                )
                Image(
                    painter = painterResource(R.drawable.ic_login_illustration),
                    contentDescription = "Login illustrator icon",
                    modifier = Modifier.size(250.dp)
                )
                OutlinedTextFieldWithError(
                    text = username,
                    onValueChanged = { value ->
                        username = value
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Send
                    ),
                    leadingIcon = {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "Username Icon"
                        )
                    },
                    hint = "Username",
                )
                OutlinedTextFieldWithError(
                    text = password,
                    onValueChanged = { value ->
                        password = value
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Send
                    ),
                    leadingIcon = {
                        Icon(
                            Icons.Default.Lock,
                            contentDescription = "Password Icon"
                        )
                    },
                    hint = "Password",
                )
                Spacer(modifier = Modifier.height( 2 * resources.spacing_16dp))
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
                Spacer(modifier = Modifier.height(resources.spacing_16dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HorizontalDivider(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp),
                        thickness = 1.dp,
                        color = Color.Gray
                    )
                    Text(
                        text = stringResource(R.string.authentication_loginScreen_continueWithLabel),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        maxLines = 1,
                    )
                    HorizontalDivider(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp),
                        thickness = 1.dp,
                        color = Color.Gray
                    )
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