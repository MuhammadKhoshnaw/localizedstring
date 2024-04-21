package com.example.localizedstring.ui.screen

import android.widget.Space
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.AnimationConstants.DefaultDurationMillis
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.localizedstring.R
import com.example.localizedstring.entity.LocalizedString
import com.example.localizedstring.entity.localizedRowString
import com.example.localizedstring.ui.theme.LocalizedStringTheme
import com.example.localizedstring.viewModel.RankActions
import com.example.localizedstring.viewModel.RankViewModel
import com.example.localizedstring.viewModel.string
import kotlinx.coroutines.delay
import kotlin.math.abs

@Composable
fun RankScreen(
    viewModel: RankViewModel = viewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    RankScreen(
        title = uiState.value.title,
        body = uiState.value.body,
        avatar = uiState.value.avatar,
        rankActions = uiState.value.actions,
        onCountClicked = viewModel::onCountClicked,
    )
}

@Composable
fun RankScreen(
    title: LocalizedString,
    body: LocalizedString,
    @DrawableRes avatar: Int?,
    rankActions: RankActions,
    onCountClicked: () -> Unit,
) {
    Scaffold { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize(),
            ) {

                Avatar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f),
                    avatar = avatar,
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(weight = 1f)
                )

            }

            Column(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1.2f),
                )

                Actions(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(weight = 1f, fill = true)
                        .padding(horizontal = 16.dp),
                    title = title,
                    body = body,
                    rankActions = rankActions,
                    onCountClicked = onCountClicked,
                )
            }
        }

        BoxWithConstraints {

        }
    }
}

@Composable
private fun Avatar(
    modifier: Modifier = Modifier,
    @DrawableRes avatar: Int?,
) {
    Crossfade(
        modifier = modifier,
        targetState = avatar,
        label = "rank avatar",
        animationSpec = tween(
            durationMillis = DefaultDurationMillis,
            easing = LinearEasing
        ),
    ) { currentAvatar ->
        AvatarImage(
            modifier = Modifier
                .fillMaxSize(),
            painter = currentAvatar?.let {
                painterResource(id = it)
            } ?: ColorPainter(Color.Transparent),
        )
    }
}

@Composable
private fun Actions(
    modifier: Modifier = Modifier,
    title: LocalizedString,
    body: LocalizedString,
    rankActions: RankActions,
    onCountClicked: () -> Unit,
) {
    val topFade = Brush.verticalGradient(
        0f to Color.Transparent,
        0.3f to Color.Black,
    )
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .graphicsLayer { alpha = 0.99F }
            .drawWithContent {
                val colors = listOf(
                    Color.Transparent,
                    Color.Black,
                    Color.Black,
                    Color.Black,
                    Color.Black,
                    Color.Black,
                    Color.Black,
                    Color.Black,
                )
                drawContent()
                drawRect(
                    brush = Brush.verticalGradient(
                        0f to Color.Transparent,
                        0f to Color.Black,
                    ),
                    blendMode = BlendMode.DstIn
                )
            },

        horizontalAlignment = Alignment.Start,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Bottom
    ) {

        Spacer(modifier = Modifier.padding(vertical = 32.dp))

        Crossfade(
            targetState = title.string(),
            label = "rank title"
        ) {
            Text(
                text = it,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }


        Text(
            text = body.string(),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )


        AnimatedContent(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .align(alignment = Alignment.CenterHorizontally),
            targetState = rankActions,
            label = "rankActions",
        ) { action ->
            when (action) {
                RankActions.COUNT_CLICKS -> FloatingActionButton(
                    onClick = onCountClicked,
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = Icons.Default.Done.name,
                    )
                }

                RankActions.UNIVERSE_DECISION -> Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                ) {
                    var buttonsEnabled by remember(action) {
                        mutableStateOf(false)
                    }
                    var countDown by remember(action) {
                        mutableIntStateOf(3)
                    }

                    LaunchedEffect(key1 = action) {
                        repeat(3) { count ->
                            countDown = abs(3 - count)
                            delay(1000L)
                        }
                        buttonsEnabled = !buttonsEnabled
                    }

                    OutlinedButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        enabled = buttonsEnabled,
                        onClick = { /*TODO*/ },
                    ) {
                        Text(
                            text = if (buttonsEnabled) "Reset Existence"
                            else "Reset Existence in $countDown",
                        )
                    }

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        enabled = buttonsEnabled,
                        onClick = { /*TODO*/ }) {
                        Text(
                            text = if (buttonsEnabled) "Forge a New Reality"
                            else "Forge a New Reality in $countDown",
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.padding(vertical = 24.dp))
    }
}

@Preview(showBackground = true)
//@Preview(showBackground = true, device = "id:Nexus One")
@Composable
private fun Preview() {
    LocalizedStringTheme {
        RankScreen(
            title = localizedRowString("Current Level : Apprentice \uD83D\uDC76"),
            body = localizedRowString(
                "Click to increase your level!, you have clicked 0 times." +
                        " You are currently an Apprentice. Click to become a Master!" +
                        " You are currently an Apprentice. Click to become a Master!" +
                        " You are currently an Apprentice. Click to become a Master!" +
                        " You are currently an Apprentice. Click to become a Master!" +
                        " You are currently an Apprentice. Click to become a Master!" +
                        " You are currently an Apprentice. Click to become a Master!" +
                        " You are currently an Apprentice. Click to become a Master!" +
                        " You are currently an Apprentice. Click to become a Master!" +
                        " You are currently an Apprentice. Click to become a Master!"

            ),
            avatar = R.drawable.rank_0,
            rankActions = RankActions.COUNT_CLICKS,
            onCountClicked = { }
        )
    }
}
