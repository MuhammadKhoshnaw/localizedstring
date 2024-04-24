package com.example.localizedstring.framework.ui.screen

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.localizedstring.R
import com.example.localizedstring.adapters.viewModel.RankActions
import com.example.localizedstring.adapters.viewModel.RankViewModel
import com.example.localizedstring.adapters.viewModel.string
import com.example.localizedstring.entity.LocalizedString
import com.example.localizedstring.entity.localizedRowString
import com.example.localizedstring.framework.ui.theme.LocalizedStringTheme
import kotlinx.coroutines.delay
import kotlin.math.abs

// TODO: implement universe decision logic upload some data to a fake
//  BE and use localisedString to get the response

// TODO: the response will be in string then implement that string id in the project

// TODO: implement the animation and logic to reset the app after the universe decision

@Composable
fun RankScreen(
    viewModel: RankViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    RankScreen(
        title = uiState.value.title,
        body = uiState.value.body,
        avatar = uiState.value.avatar,
        rankActions = uiState.value.actions,
        onCountClicked = viewModel::onCountClicked,
        onResetExistence = viewModel::onResetExistence,
        onForgeNewReality = viewModel::onForgeNewReality,
        onTryAgain = viewModel::onTryAgain,
        onOk = viewModel::onOk,
    )
}

@Composable
fun RankScreen(
    title: LocalizedString,
    body: LocalizedString,
    @DrawableRes avatar: Int?,
    rankActions: RankActions,
    onCountClicked: () -> Unit,
    onResetExistence: () -> Unit,
    onForgeNewReality: () -> Unit,
    onTryAgain: () -> Unit,
    onOk: () -> Unit,
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
                    onResetExistence = onResetExistence,
                    onForgeNewReality = onForgeNewReality,
                    onTryAgain = onTryAgain,
                    onOk = onOk,
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
    onResetExistence: () -> Unit,
    onForgeNewReality: () -> Unit,
    onTryAgain: () -> Unit,
    onOk: () -> Unit,
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .graphicsLayer { alpha = 0.99F }
            .drawWithContent {
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
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Top
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
                RankActions.CountClicks -> FloatingActionButton(
                    modifier = Modifier
                        .padding(top = 32.dp),
                    onClick = onCountClicked,
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = Icons.Default.Done.name,
                    )
                }

                RankActions.UniverseDecision -> Column(
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
                        onClick = onResetExistence,
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
                        onClick = onForgeNewReality
                    ) {
                        Text(
                            text = if (buttonsEnabled) "Forge a New Reality"
                            else "Forge a New Reality in $countDown",
                        )
                    }
                }

                RankActions.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.width(64.dp),
                        color = MaterialTheme.colorScheme.secondary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    )
                }

                RankActions.Ok -> {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        onClick = onOk
                    ) {
                        Text(text = "Ok")
                    }
                }

                RankActions.TryAgain -> {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        onClick = onTryAgain
                    ) {
                        Text(text = "Try Again")
                    }
                }

            }
        }

        Spacer(modifier = Modifier.padding(vertical = 24.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    LocalizedStringTheme {
        RankScreen(
            title = localizedRowString("Current Level : Apprentice \uD83D\uDC76"),
            body = localizedRowString("Click to increase your level!, you have clicked 0 times."),
            avatar = R.drawable.rank_0,
            rankActions = RankActions.Loading,
            onCountClicked = { },
            onResetExistence = { },
            onForgeNewReality = { },
            onTryAgain = { },
            onOk = { },
        )
    }
}
