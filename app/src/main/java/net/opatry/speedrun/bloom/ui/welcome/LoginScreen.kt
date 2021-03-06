/*
 * Copyright (c) 2021 Olivier Patry
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software
 * is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package net.opatry.speedrun.bloom.ui.welcome

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.opatry.speedrun.bloom.R
import net.opatry.speedrun.bloom.ui.theme.BloomTheme
import net.opatry.speedrun.bloom.ui.theme.typography
import net.opatry.speedrun.bloom.ui.welcome.component.WelcomeButton

@Composable
fun LoginScreen(onSignedIn: () -> Unit) {
    Surface(color = MaterialTheme.colors.background) {
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                Modifier.padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    stringResource(R.string.login_login_title),
                    Modifier.paddingFromBaseline(top = 184.dp, bottom = 16.dp),
                    style = typography.h1
                )

                var email by remember { mutableStateOf("") }
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    placeholder = {
                        // TODO replicate text style
                        Text(stringResource(R.string.login_email_address), style = typography.body1)
                    }
                )

                var password by remember { mutableStateOf("") }
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    placeholder = {
                        // TODO replicate text style
                        Text(stringResource(R.string.login_password), style = typography.body1)
                    }
                )
                Text(
                    // FIXME This isn't scalable at all. We should manage this using localized strings
                    //  Maybe having several strings like that and then another one "%s %s %s %s %s"
                    //  to put first and second part to allow more flexibility with translations
                    buildAnnotatedString {
                        append("By clicking below, you agree to our ")
                        withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) {
                            append("Terms of Use")
                        }
                        append(" and consent to our ")
                        withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) {
                            append("Privacy Policy")
                        }
                        append(".")
                    },
                    Modifier.paddingFromBaseline(top = 24.dp, bottom = 16.dp),
                    textAlign = TextAlign.Center,
                    style = typography.body2
                )
                WelcomeButton(onClick = onSignedIn) {
                    Text(stringResource(R.string.login_login))
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview("Login Light Theme", widthDp = 360, heightDp = 640)
@Composable
private fun LoginLightPreview() {
    BloomTheme {
        LoginScreen {}
    }
}

@ExperimentalFoundationApi
@Preview("Login Dark Theme", widthDp = 360, heightDp = 640)
@Composable
private fun LoginDarkPreview() {
    BloomTheme(darkTheme = true) {
        LoginScreen {}
    }
}
