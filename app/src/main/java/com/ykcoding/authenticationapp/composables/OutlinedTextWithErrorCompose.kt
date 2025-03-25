package com.ykcoding.authenticationapp.composables

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.ykcoding.authenticationapp.R

@Composable
fun OutlinedTextFieldWithError(
    text: String,
    hint: String? = null,
    onValueChanged: (String) -> Unit,
    keyboardOptions: KeyboardOptions? = null,
    visualTransformation: VisualTransformation? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    errorText: String? = null,
) {
    val isPasswordField: Boolean = keyboardOptions?.keyboardType == KeyboardType.Password
    val hasFocus by remember { mutableStateOf(false) }
    var displayErrors by remember { mutableStateOf(false) }
    var isPasswordVisible by remember { mutableStateOf(false) }

    val transformation = if (!isPasswordVisible && isPasswordField && visualTransformation == null) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }

    OutlinedTextField(
        value = text,
        onValueChange = {
            onValueChanged(it)
        },
        keyboardOptions = keyboardOptions ?: KeyboardOptions(keyboardType = KeyboardType.Text),
        visualTransformation = transformation,
        leadingIcon = leadingIcon,
        label = { Text(text = hint ?: "") },
        singleLine = true,
        maxLines = 1,
        shape = RoundedCornerShape(10.dp),
        trailingIcon = {
            if (isPasswordField) {
                IconButton(
                    onClick = { isPasswordVisible = !isPasswordVisible }
                ) {
                    val drawable = when(isPasswordVisible) {
                        true -> R.drawable.ic_eye_visibility_on
                        else -> R.drawable.ic_eye_visibility_off
                    }
                    Icon(
                        painter = painterResource(drawable),
                        contentDescription = "Show and Hide Password Icon",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        },
        supportingText = {
            if (displayErrors) {
                Text(
                    text = errorText ?: "",
                    color = Color.Red
                )
            }
        },
        modifier = Modifier
            .onFocusChanged {
                if (!it.hasFocus && hasFocus) {
                    displayErrors = true
                }
            }
    )

}

