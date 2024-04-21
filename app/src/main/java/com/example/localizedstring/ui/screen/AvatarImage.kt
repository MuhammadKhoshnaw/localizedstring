package com.example.localizedstring.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.localizedstring.R
import com.example.localizedstring.ui.theme.LocalizedStringTheme


@Composable
fun AvatarImage(
    modifier: Modifier = Modifier,
    painter: Painter,
) {
    Image(
        painter = painter,
        contentDescription = "Localized String",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxWidth()
            .graphicsLayer { alpha = 0.99f }
            .drawWithContent {
                drawContent()

                val verticalColors = listOf(
                    Color.Black,
                    Color.Black,
                    Color.Black,
                    Color.Black,
                    Color.Transparent,
                )

                val verticalColors2 = listOf(
                    Color.Black,
                    Color.Black,
                    Color.Black,
                    Color.Black,
                    Color.Black,
                    Color.Transparent,
                )


                drawRect(
                    brush = Brush.verticalGradient(
                        colors = verticalColors,
                    ),
                    blendMode = BlendMode.DstIn,
                )

                drawRect(
                    brush = Brush.verticalGradient(
                        colors = verticalColors2,
                    ),
                    blendMode = BlendMode.DstIn,
                )

            }
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    LocalizedStringTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            AvatarImage(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.rank_0),
            )
        }
    }
}
