package com.amilcar.rutaj.presentation.login

import android.content.Context
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.amilcar.rutaj.R
import com.amilcar.rutaj.presentation.components.EventDialog
import com.amilcar.rutaj.presentation.components.LabelledCheckbox
import com.amilcar.rutaj.presentation.components.RoundedButton
import com.amilcar.rutaj.presentation.components.TransparentTextField
import com.amilcar.rutaj.presentation.navigation.Destinations
import com.amilcar.rutaj.presentation.util.Variables
import com.amilcar.rutaj.ui.theme.Gray50
import com.amilcar.rutaj.ui.theme.Orange900
import com.amilcar.rutaj.ui.theme.Red600
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider


@ExperimentalPermissionsApi
@Composable
fun LoginScreen(
    viewModel : LoginViewModel,
    navController : NavHostController,
    state : LoginState,
    onLogin : (String, String, String,Context) -> Unit,
    onNavigateToRegister : () -> Unit,
    onNavigatetoRecoveyPassword : (String) -> Unit,
    onDismissDialog : () -> Unit,
) {
    val systemUiController = rememberSystemUiController()

    val context = LocalContext.current
    val colorSurface : Color
    val imagenFoco : Painter
    val colorTextoGoogle : Color

    if(isSystemInDarkTheme()){
        colorSurface = MaterialTheme.colors.background
        imagenFoco = painterResource(id = R.drawable.focodark)
        colorTextoGoogle = Orange900

        systemUiController.setSystemBarsColor(
            color = MaterialTheme.colors.primary
        )
    }else{
        colorSurface = Color.White
        imagenFoco = painterResource(id = R.drawable.foco)
        colorTextoGoogle = Red600

        systemUiController.setSystemBarsColor(
            color = Gray50
        )
    }


    val emailValue = rememberSaveable { mutableStateOf("") }
    val passwordValue = rememberSaveable { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current



    if (Variables.G_usuarioState) {
        viewModel.dataStoreEnabled.value = true
        emailValue.value = Variables.G_usuarioEmail
        passwordValue.value = Variables.G_usuarioPassword
    } else {
        viewModel.dataStoreEnabled.value = false
    }


    val token = stringResource(R.string.token)
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts
            .StartActivityForResult()
    ) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try {
            val account = task.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            viewModel.signInWithGoogleCredential(credential) {

                Variables.G_usuarioState = false

                onLogin(
                    account.email.toString(),
                    account.id.toString(),
                    account.displayName.toString(),
                    context
                )
            }
        } catch (e : Exception) {
            Log.d("mica",e.toString())
        }
    }





    LaunchedEffect(state.successLogin) {
        if (state.successLogin) {
                navController.navigate(route = Destinations.Main.route) {
                    popUpTo(Destinations.Login.route) { inclusive = true }
                }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Image(
            painter = imagenFoco,
            contentDescription = "Login Image",
            contentScale = ContentScale.Crop
        )


        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            ConstraintLayout {
                val (surfase) = createRefs()



                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(450.dp)
                        .constrainAs(surfase) {
                            bottom.linkTo(parent.bottom)
                        },
                    color = colorSurface,
                    shape = RoundedCornerShape(
                        topStartPercent = 8,
                        topEndPercent = 8
                    )
                ) {
                    // to do campos de texto
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "Cooperativa de Servicios Públicos",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.h6.copy(
                                color = MaterialTheme.colors.onSurface
                            ),
                            fontSize = 20.sp
                        )
                        Text(
                            text = "Ruta J Ltda.",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.h6.copy(
                                color = MaterialTheme.colors.onSurface
                            ),
                            fontSize = 20.sp
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {

                            TransparentTextField(
                                textFieldValue = emailValue,
                                textLabel = "Email",
                                keyboardType = KeyboardType.Email,
                                keyboardActions = KeyboardActions(
                                    onNext = {
                                        focusManager.moveFocus(FocusDirection.Down)
                                    }
                                ),
                                imeAction = ImeAction.Next,
                                trailingIcon = {
                                    IconButton(
                                        onClick = {
                                            //  todo
                                        }) {
                                        Icon(
                                            Icons.Default.ArrowDropDown,
                                            contentDescription = "arrow drop dwon Icon"
                                        )
                                    }
                                }

                            )

                            TransparentTextField(
                                textFieldValue = passwordValue,
                                textLabel = "Password",
                                keyboardType = KeyboardType.Password,
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        focusManager.clearFocus()
                                        onLogin(
                                            (emailValue.value),
                                            (passwordValue.value),
                                            "",
                                            context
                                        )
                                    }
                                ),
                                imeAction = ImeAction.Done,
                                trailingIcon = {
                                    IconButton(
                                        onClick = {
                                            passwordVisibility = !passwordVisibility
                                        }) {
                                        Icon(
                                            imageVector = if (passwordVisibility) {
                                                Icons.Default.Visibility
                                            } else {
                                                Icons.Default.VisibilityOff
                                            },
                                            contentDescription = "Toggle Password Icon"
                                        )
                                    }
                                },
                                visualTransformation = if (passwordVisibility) {
                                    VisualTransformation.None
                                } else {
                                    PasswordVisualTransformation()
                                }
                            )

                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(end = 4.dp)
                                    .clickable { navController.navigate(Destinations.PoliticaPrivacidad.route) },
                                text = "Política de privacidad",
                                textAlign = TextAlign.Right,
                                fontStyle = FontStyle.Italic,
                                style = MaterialTheme.typography.subtitle1,
                                fontSize =  10.sp
                            )

                            LabelledCheckbox(
                                checked = viewModel.dataStoreEnabled.value,
                                onCheckedChange = {
                                    viewModel.dataStoreEnabled.value = it
                                    Variables.G_usuarioState = !Variables.G_usuarioState
                                },
                                label = "Recordar Password",
                                modifier = Modifier
                                    .padding(4.dp)
                                    .fillMaxWidth()
                            )


                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                ClickableText(

                                    text = buildAnnotatedString {
                                        withStyle(
                                            style = SpanStyle(
                                                color = MaterialTheme.colors.onSurface,
                                                fontWeight = FontWeight.Medium,
                                                fontSize = 13.sp
                                            )
                                        ) {
                                            append("Regístrese")
                                        }
                                    },
                                    style = MaterialTheme.typography.body1
                                ) {
                                    // abre vantena de registro
                                    onNavigateToRegister()

                                    // poner un scaffold con un snackbar para un mensaje
                                }
                                ClickableText(

                                    text = buildAnnotatedString {
                                        withStyle(
                                            style = SpanStyle(
                                                color = MaterialTheme.colors.onSurface,
                                                fontWeight = FontWeight.Medium,
                                                fontSize = 13.sp
                                            )
                                        ) {
                                            append("Olvidó su contraseña ?")
                                        }
                                    },
                                    style = MaterialTheme.typography.body1
                                ) {
                                    // voy a ventana de recupero de password


                                    if (emailValue.value.isNotBlank()) {
                                        if (Patterns.EMAIL_ADDRESS.matcher(emailValue.value)
                                                .matches()
                                        ) {
                                            onNavigatetoRecoveyPassword(emailValue.value)
                                        } else {
                                            Toast.makeText(
                                                context,
                                                "Ingrese un mail válido",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Ingrese un mail registrado",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }

                                }
                            }



                            RoundedButton(
                                text = "Login",
                                displayProgressBar = state.displayProgressBar,
                                enabled = emailValue.value!="" && passwordValue.value!="",
                                onClick = {
                                    onLogin(
                                        (emailValue.value),
                                        (passwordValue.value),
                                        "",
                                        context
                                    )
                                }
                            )


                            OutlinedButton(
                                modifier = Modifier
                                    .width(250.dp)
                                    .height(40.dp),
                                shape = RoundedCornerShape(50.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface),
                                onClick = {

                                    // funcion de logeo con google
                                    val opciones = GoogleSignInOptions.Builder(
                                        GoogleSignInOptions.DEFAULT_SIGN_IN
                                    )
                                        .requestIdToken(token)
                                        .requestEmail()
                                        .build()
                                    val googleSignInClient = GoogleSignIn.getClient(context,opciones)
                                    launcher.launch(googleSignInClient.signInIntent)
                                }, border = BorderStroke(
                                    width = 2.dp,
                                    brush = Brush.horizontalGradient(
                                        listOf(
                                            Color(0xFF42A5F5),
                                            Color(0xFFFFA726)
                                        )
                                    )
                                )
                            ) {
                                Icon(
                                    modifier = Modifier.size(ButtonDefaults.IconSize),
                                    painter = painterResource(id = R.drawable.googlelogo),
                                    contentDescription = "logo google",
                                )
                                Spacer(Modifier.size(ButtonDefaults.IconSpacing))

                                Text(
                                    text = "Inicie sesión con Google",
                                    textAlign = TextAlign.Left,
                                    style = MaterialTheme.typography.body2.copy(
                                        color = colorTextoGoogle,
                                        fontSize = 14.sp
                                    )
                                )

                            }

                        }
                    }


                }


            }

        }



        if (state.errorMessage != null) {
            EventDialog(
                errorMessage = state.errorMessage,
                onDismiss = onDismissDialog
            )
        }

    }


}


