package com.dolearnn.cg.presentation.algorithms

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.dolearnn.cg.config.Config
import com.dolearnn.cg.data.database.algorithm.Algorithm
import com.dolearnn.cg.ui.extensions.ColumnSpacer

@Composable
fun AlgorithmsScreen(
    onClick: (Algorithm) -> Unit,
    viewModel: AlgorithmsViewModel = viewModel()
) {
    val algorithms by viewModel.algorithms.observeAsState(emptyList())

    AlgorithmsScreenContent(algorithms, viewModel::getItemBg, onClick)
}

@Composable
fun AlgorithmsScreenContent(
    algorithms: List<Algorithm>,
    getBg: (Int) -> Int,
    onClick: (Algorithm) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {

        Banner()

        LazyVerticalGrid(
            columns = GridCells.Adaptive(140.dp),
            modifier = Modifier
                .weight(1f)
                .padding(top = 24.dp, end = 26.dp, start = 26.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            itemsIndexed(items = algorithms) { index, item ->
                AlgorithmCardItem(
                    title = item.name,
                    desc = item.description,
                    icon = item.iconURL,
                    bg = getBg(index),
                    onClick = {
                        onClick(item)
                    }
                )
            }
        }
    }
}

@Composable
fun AlgorithmCardItem(
    title: String,
    desc: String,
    icon: String,
    @DrawableRes
    bg: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.9f)
    ) {
        Image(
            painter = painterResource(id = bg),
            contentDescription = "Card BG",
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(18.dp))
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = rememberRipple(),
                    onClick = onClick
                )
        )
        Image(
            painter = rememberAsyncImagePainter(model = "${Config.BaseUrl}$icon"),
            contentDescription = "image_one",
            modifier = Modifier
                .padding(end = 20.dp)
                .size(80.dp, 80.dp)
                .align(Alignment.TopEnd),
            contentScale = ContentScale.Inside,
            alpha = 0.6f
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 20.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.White
            )

            ColumnSpacer(space = 8.dp)

            Text(
                text = desc,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun Banner() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp)
    ) {
        Text(
            text = "Explore CG",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 32.sp
        )
        Text(
            text = "Algorithms",
            fontWeight = FontWeight.Medium,
            color = Color.White,
            fontSize = 32.sp
        )
    }
}
