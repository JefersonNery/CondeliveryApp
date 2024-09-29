package br.com.fiap.condeliveryapp.pages

import android.provider.CalendarContract.Colors
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.condeliveryapp.AuthState
import br.com.fiap.condeliveryapp.AuthViewModel
import br.com.fiap.condeliveryapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel
) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Authenticated -> navController.navigate("home")
            is AuthState.Error -> Toast.makeText(
                context,
                (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT
            ).show()

            else -> Unit
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column() {
            Image(
                painter = painterResource(id = R.drawable.headerimg),
                contentDescription = "Header imagem",
                modifier = Modifier
                    .height(150.dp)
                    .offset(y = (-180).dp)
            )
            Image(
                painter = painterResource(id = R.drawable.imglogin),
                contentDescription = "Login Imagem",
                modifier = Modifier
                    .height(200.dp)
                    .offset(y = (-160).dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.offset(y = (-120).dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Ex: Fulano@gmail.com") },
                shape = RoundedCornerShape(16.dp),
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = {
                    Text(text = "Senha")
                },
                shape = RoundedCornerShape(16.dp),
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-25.dp)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextButton(onClick = {
                    navController.navigate("cadastrar")
                }) {
                    Text(text = "Cadastrar uma conta nova")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        authViewModel.login(email, password)
                    },
                    enabled = authState.value != AuthState.Loading
                ) {
                    Text(text = "Entrar")
                }
            }

        }
    }
}