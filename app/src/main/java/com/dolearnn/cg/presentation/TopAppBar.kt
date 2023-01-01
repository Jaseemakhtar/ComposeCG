package com.dolearnn.cg.presentation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dolearnn.cg.R
import com.dolearnn.cg.presentation.navigation.AlgorithmsRoute
import com.dolearnn.cg.presentation.navigation.algorithmNameArg
import com.dolearnn.cg.ui.extensions.RowSpacer

@Composable
fun TopAppBar(navHostController: NavHostController) {
    val backStackEntry by navHostController.currentBackStackEntryAsState()

    AppBar(
        icon = if (backStackEntry?.destination?.route == AlgorithmsRoute)
            R.drawable.ic_home else
            R.drawable.ic_arrow_back,
        title = backStackEntry?.arguments?.getString(algorithmNameArg)
            ?: stringResource(id = R.string.text_home)
    )
}

@Composable
private fun AppBar(@DrawableRes icon: Int, title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = "Home Icon",
            modifier = Modifier
                .size(28.dp)
                .padding(start = 6.dp),
            contentScale = ContentScale.Inside,
            alignment = Alignment.Center
        )

        RowSpacer(space = 8.dp)

        Text(
            text = title,
            fontWeight = FontWeight.Medium,
            color = Color.White,
            fontSize = 14.sp
        )
    }
}