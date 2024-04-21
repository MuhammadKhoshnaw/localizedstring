package com.example.localizedstring.viewModel

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import com.example.localizedstring.R
import com.example.localizedstring.entity.LocalizedString
import com.example.localizedstring.entity.empty
import com.example.localizedstring.entity.localizedString
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber

class RankViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(generateInitialState())
    val uiState = _uiState.asStateFlow()

    private var count = 0
        set(value) {
            field = value
            updateRank()
        }

    init {
        updateRank()
    }

    fun onCountClicked() {
        Timber.i("user clicked count button")
        count++
    }

    private fun updateRank() {
        Timber.i("updating rank")

        val (title, body, avatar) = when (count) {
            in RANK_0_RANGE -> Triple(
                localizedString(R.string.rank_title, localizedString(R.string.rank_0)),
                localizedString(R.string.rank_body, count.toString()),
                R.drawable.rank_0
            )

            in RANK_1_RANGE -> Triple(
                localizedString(R.string.rank_title, localizedString(R.string.rank_1)),
                localizedString(R.string.rank_body, count.toString()),
                R.drawable.rank_1
            )

            in RANK_2_RANGE -> Triple(
                localizedString(R.string.rank_title, localizedString(R.string.rank_2)),
                localizedString(R.string.rank_body, count.toString()),
                R.drawable.rank_2
            )

            in RANK_3_RANGE -> Triple(
                localizedString(R.string.rank_title, localizedString(R.string.rank_3)),
                localizedString(R.string.rank_body, count.toString()),
                R.drawable.rank_3
            )

            in RANK_4_RANGE -> Triple(
                localizedString(R.string.rank_title, localizedString(R.string.rank_4)),
                localizedString(R.string.rank_body, count.toString()),
                R.drawable.rank_4
            )

            in RANK_5_RANGE -> Triple(
                localizedString(R.string.rank_title, localizedString(R.string.rank_5)),
                localizedString(R.string.rank_body, count.toString()),
                R.drawable.rank_5
            )

            in RANK_6_RANGE -> Triple(
                localizedString(R.string.rank_title, localizedString(R.string.rank_6)),
                localizedString(R.string.rank_body, count.toString()),
                R.drawable.rank_6
            )

            in RANK_7_RANGE -> Triple(
                localizedString(R.string.rank_title, localizedString(R.string.rank_7)),
                localizedString(R.string.rank_body, count.toString()),
                R.drawable.rank_7
            )

            in RANK_8_RANGE -> Triple(
                localizedString(R.string.rank_title, localizedString(R.string.rank_8)),
                localizedString(R.string.rank_body, count.toString()),
                R.drawable.rank_8
            )

            in RANK_9_RANGE -> Triple(
                localizedString(R.string.rank_title, localizedString(R.string.rank_9)),
                localizedString(R.string.last_rank_body, count.toString()),
                R.drawable.rank_9
            )

            else -> Triple(
                localizedString(R.string.rank_title, String.empty),
                localizedString(R.string.rank_body, String.empty),
                null,
            )
        }

        _uiState.value = WelcomeUIState(
            title = title,
            body = body,
            avatar = avatar,
        )

    }

    private fun generateInitialState(): WelcomeUIState = WelcomeUIState(
        title = localizedString(R.string.rank_title, String.empty),
        body = localizedString(R.string.rank_body, String.empty),
        avatar = null,
    )

    companion object {
        /*        private val RANK_0_RANGE = 0..1
                private val RANK_1_RANGE = 2..3
                private val RANK_2_RANGE = 4..5
                private val RANK_3_RANGE = 6..7
                private val RANK_4_RANGE = 8..9
                private val RANK_5_RANGE = 10..11
                private val RANK_6_RANGE = 12..13
                private val RANK_7_RANGE = 14..15
                private val RANK_8_RANGE = 16..17
                private val RANK_9_RANGE = 18..Int.MAX_VALUE*/

        private val RANK_0_RANGE = 0..9
        private val RANK_1_RANGE = 10..19
        private val RANK_2_RANGE = 20..34
        private val RANK_3_RANGE = 35..54
        private val RANK_4_RANGE = 55..79
        private val RANK_5_RANGE = 80..109
        private val RANK_6_RANGE = 110..144
        private val RANK_7_RANGE = 145..184
        private val RANK_8_RANGE = 185..229
        private val RANK_9_RANGE = 230..Int.MAX_VALUE
    }
}

data class WelcomeUIState(
    val title: LocalizedString,
    val body: LocalizedString,
    @DrawableRes val avatar: Int?,
)
