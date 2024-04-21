package com.example.localizedstring.ui.screen

import androidx.annotation.DrawableRes
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.AnimationConstants.DefaultDurationMillis
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.localizedstring.viewModel.RankViewModel
import com.example.localizedstring.viewModel.string

@Composable
fun RankScreen(
    viewModel: RankViewModel = viewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    RankScreen(
        title = uiState.value.title,
        body = uiState.value.body,
        avatar = uiState.value.avatar,
        onCountClicked = viewModel::onCountClicked,
    )
}

@Composable
fun RankScreen(
    title: LocalizedString,
    body: LocalizedString,
    @DrawableRes avatar: Int?,
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

                Crossfade(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f),
                    targetState = avatar,
                    label = "rank avatar",
                    animationSpec = tween(
                        durationMillis = DefaultDurationMillis,
                        easing = LinearEasing
                    ),
                ) { avatar ->
                    AvatarImage(
                        modifier = Modifier
                            .fillMaxSize(),
                        painter = avatar?.let {
                            painterResource(id = it)
                        } ?: ColorPainter(Color.Transparent),
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 32.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
                ) {

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

                    FloatingActionButton(
                        onClick = onCountClicked,
                        modifier = Modifier
                            .padding(top = 24.dp)
                            .align(alignment = Alignment.CenterHorizontally),
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = Icons.Default.Done.name,
                        )
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, device = "id:Nexus 10")
@Composable
private fun Preview() {
    LocalizedStringTheme {
        RankScreen(
            title = localizedRowString("Current Level : Apprentice \uD83D\uDC76"),
            body = localizedRowString("Click to increase your level!, you have clicked 0 times."),
            avatar = R.drawable.rank_0,
            onCountClicked = { }
        )
    }
}
