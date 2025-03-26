package com.ykcoding.authenticationapp.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.ykcoding.authenticationapp.R
import com.ykcoding.authenticationapp.composables.OutlinedTextFieldWithError
import com.ykcoding.authenticationapp.ui.theme.ErrorTextColor
import com.ykcoding.authenticationapp.ui.theme.NativeAuthenticationAppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginActivity : ComponentActivity() {

    private val viewModel: LoginViewModel by viewModel()

    private val resources = object {
        val spacing_16dp = 16.dp
        val textSize_36sp = 36.sp
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NativeAuthenticationAppTheme {
                Box(
                    modifier = Modifier.fillMaxSize()
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
        val username by viewModel.usernameTextState.collectAsState()
        val password by viewModel.passwordTextState.collectAsState()
        val userErrorType by viewModel.usernameErrorType.collectAsState()
        val passwordErrorType by viewModel.passwordErrorState.collectAsState()

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(resources.spacing_16dp),
                verticalArrangement = Arrangement.spacedBy(
                    space = 4.dp,
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
                    onValueChanged = viewModel::setUsername,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Send
                    ),
                    leadingIcon = {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "Username Icon",
                            tint = if (userErrorType == LoginViewModel.ErrorType.EMPTY)
                                ErrorTextColor else Color.Black
                        )
                    },
                    hint = "Username",
                    errorText = when(userErrorType) {
                        LoginViewModel.ErrorType.EMPTY -> "Please enter a valid username"
                        LoginViewModel.ErrorType.NONE -> ""
                    }
                )
                OutlinedTextFieldWithError(
                    text = password,
                    onValueChanged = viewModel::setPassword,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Send
                    ),
                    leadingIcon = {
                        Icon(
                            Icons.Default.Lock,
                            contentDescription = "Password Icon",
                            tint = if (passwordErrorType == LoginViewModel.ErrorType.EMPTY)
                                ErrorTextColor else Color.Black
                        )
                    },
                    hint = "Password",
                    errorText = when(passwordErrorType) {
                        LoginViewModel.ErrorType.EMPTY -> "Please enter a valid password"
                        LoginViewModel.ErrorType.NONE -> ""
                    }
                )
                Spacer(modifier = Modifier.height(4.dp))
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
                    modifier = Modifier
                        .fillMaxWidth()
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