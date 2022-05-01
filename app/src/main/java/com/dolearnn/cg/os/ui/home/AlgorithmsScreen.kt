package com.dolearnn.cg.os.ui.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
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
import coil.compose.rememberImagePainter
import com.dolearnn.cg.os.database.algorithm.Algorithm
import com.dolearnn.cg.os.ui.component.extensions.ColumnSpacer

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AlgorithmsScreen(viewModel: AlgorithmsViewModel = viewModel(), onClick: (Algorithm) -> Unit) {
    val algorithms by viewModel.algorithms.observeAsState(emptyList())

    AlgorithmsScreenContent(algorithms, viewModel::getItemBg, onClick)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AlgorithmsScreenContent(
    algorithms: List<Algorithm>,
    getBg: (Int) -> Int,
    onClick: (Algorithm) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {

        // Banner Region
        Banner()
        // endRegion

        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            modifier = Modifier
                .padding(top = 12.dp, end = 26.dp)
                .weight(1f)
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
            .padding(start = 26.dp, top = 12.dp, bottom = 12.dp)
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
            painter = rememberImagePainter(data = "https://dolearnn.com$icon"),
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
