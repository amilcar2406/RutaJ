package com.amilcar.rutaj.presentation.splash

import android.annotation.SuppressLint
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.amilcar.rutaj.R
import com.amilcar.rutaj.presentation.navigation.Destinations
import kotlinx.coroutines.delay

@SuppressLint("RememberReturnType")
@Composable
fun SplashScreen(navController : NavController){

    val scale = remember{ Animatable(0f)}

    LaunchedEffect(key1 = true ){
        scale.animateTo(
            targetValue = 4f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(1000)
        navController.popBackStack()
        navController.navigate(Destinations.Login.route)
    }

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center)
    {
        Image(painter = painterResource(id = R.drawable.splash), contentDescription = "splash",
        modifier = Modifier.scale(scale.value)
            //.size(14.dp)
            .clip(RoundedCornerShape(4))                       // clip to the circle shape
            .border(1.dp, Color.Black, RoundedCornerShape(4))

        )

    }

}





/*@Preview(showBackground = true)
@Composable
fun SplashScreenView() {
    Splash()
}*/