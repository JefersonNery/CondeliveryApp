package br.com.fiap.condeliveryapp.pages

import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.condeliveryapp.AuthState
import br.com.fiap.condeliveryapp.AuthViewModel
import br.com.fiap.condeliveryapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel
) {

    val authState = authViewModel.authState.observeAsState()

    var codigo by remember {
        mutableStateOf("")
    }

    var textState = remember { mutableStateOf(TextFieldValue()) }

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Unauthenticated -> navController.navigate("login")
            else -> Unit
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.headerimg),
            contentDescription = "Header imagem",
            modifier = Modifier.height(150.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-40.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Alameda Mamoré, nº 809 - apt 513 - 6º andar",
                Modifier
                    .fillMaxWidth()
                    .background(color = colorResource(id = R.color.bglivery))
                    .padding(10.dp)
            )
        }
        Column(
            modifier = Modifier.offset(y = (-75.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Bem Vindo(a) de volta !",
                Modifier
                    .fillMaxWidth()
                    .padding(50.dp),
                fontSize = 30.sp,
                color = colorResource(id = R.color.txtlivery)
            )

            Text("Fez o pedido no app parceiro ?", fontSize = 22.sp)
            Text(
                "Que maravilha!! nos informe qual o app parceiro, o código e deixa que cuidamos do resto.",
                Modifier
                    .fillMaxWidth()
                    .padding(30.dp),
                textAlign = TextAlign.Center

            )

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column() {
                    Image(
                        painter = painterResource(id = R.drawable.parceiros),
                        contentDescription = "Header imagem",
                        modifier = Modifier
                            .height(80.dp)
                            .fillMaxWidth()
                    )
                }
                Text(
                    "Se necessário, você pode inserir instruções de entrega para nosso entregador no campo abaixo :",
                    fontSize = 15.sp,
                    modifier = Modifier
                        .width(400.dp)
                        .padding(10.dp),
                    textAlign = TextAlign.Center

                )

                OutlinedTextField(
                    value = textState.value,
                    onValueChange = { textState.value = it },
                    placeholder = { Text("EX: Deixe na porta, garagem etc.") },
                    modifier = Modifier
                        .width(400.dp)
                        .height(130.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Gray, unfocusedBorderColor = Color.LightGray
                    )
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Column(
                modifier = Modifier
                    .width(400.dp)
                    .height(150.dp)
                    .background(color = colorResource(id = R.color.bglivery))
                    .clip(shape = RoundedCornerShape(20.dp)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    value = codigo,
                    onValueChange = { codigo = it },
                    modifier = Modifier
                        .height(50.dp)
                        .width(300.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = {
                        Text(text = "Digite o Codigo da entrega")
                    },
                    shape = RoundedCornerShape(4.dp),
                )

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {},
                    modifier = Modifier
                        .width(150.dp)
                        .height(45.dp)
                        .background(color = colorResource(id = R.color.bglivery))
                ) {
                    Text("Enviar")
                }
            }
        }


        TextButton(
            modifier = Modifier.offset(y = (-80.dp)),
            onClick = {
                authViewModel.signout()
            }) {
            Text(text = "Deslogar")
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 40.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.menu),
            contentDescription = "Menu imagem",
            modifier = Modifier.height(80.dp)
        )
    }
}