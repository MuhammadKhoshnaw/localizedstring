package com.example.localizedstring.viewModel

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import com.example.localizedstring.R
import com.example.localizedstring.entity.LocalizedString
import com.example.localizedstring.entity.empty
import com.example.localizedstring.entity.localizedString
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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

        val newUIState: WelcomeUIState = when (count) {
            in RANK_0_RANGE -> WelcomeUIState(
                title = localizedString(R.string.rank_title, localizedString(R.string.rank_0)),
                body = localizedString(R.string.rank_body, count.toString()),
                avatar = R.drawable.rank_0,
                actions = RankActions.COUNT_CLICKS,
            )

            in RANK_1_RANGE -> WelcomeUIState(
                title = localizedString(R.string.rank_title, localizedString(R.string.rank_1)),
                body = localizedString(R.string.rank_body, count.toString()),
                avatar = R.drawable.rank_1,
                actions = RankActions.COUNT_CLICKS,
            )

            in RANK_2_RANGE -> WelcomeUIState(
                title = localizedString(R.string.rank_title, localizedString(R.string.rank_2)),
                body = localizedString(R.string.rank_body, count.toString()),
                avatar = R.drawable.rank_2,
                actions = RankActions.COUNT_CLICKS,

                )

            in RANK_3_RANGE -> WelcomeUIState(
                title = localizedString(R.string.rank_title, localizedString(R.string.rank_3)),
                body = localizedString(R.string.rank_body, count.toString()),
                avatar = R.drawable.rank_3,
                actions = RankActions.COUNT_CLICKS,
            )

            in RANK_4_RANGE -> WelcomeUIState(
                title = localizedString(R.string.rank_title, localizedString(R.string.rank_4)),
                body = localizedString(R.string.rank_body, count.toString()),
                avatar = R.drawable.rank_4,
                actions = RankActions.COUNT_CLICKS,
            )

            in RANK_5_RANGE -> WelcomeUIState(
                title = localizedString(R.string.rank_title, localizedString(R.string.rank_5)),
                body = localizedString(R.string.rank_body, count.toString()),
                avatar = R.drawable.rank_5,
                actions = RankActions.COUNT_CLICKS,
            )

            in RANK_6_RANGE -> WelcomeUIState(
                title = localizedString(R.string.rank_title, localizedString(R.string.rank_6)),
                body = localizedString(R.string.rank_body, count.toString()),
                avatar = R.drawable.rank_6,
                actions = RankActions.COUNT_CLICKS,
            )

            in RANK_7_RANGE -> WelcomeUIState(
                title = localizedString(R.string.rank_title, localizedString(R.string.rank_7)),
                body = localizedString(R.string.rank_body, count.toString()),
                avatar = R.drawable.rank_7,
                actions = RankActions.COUNT_CLICKS,
            )

            in RANK_8_RANGE -> WelcomeUIState(
                title = localizedString(R.string.rank_title, localizedString(R.string.rank_8)),
                body = localizedString(R.string.rank_body, count.toString()),
                avatar = R.drawable.rank_8,
                actions = RankActions.COUNT_CLICKS,
            )

            in RANK_9_RANGE -> WelcomeUIState(
                title = localizedString(R.string.rank_title, localizedString(R.string.rank_9)),
                body = localizedString(R.string.last_rank_body, count.toString()),
                avatar = R.drawable.rank_9,
                actions = RankActions.UNIVERSE_DECISION,
            )

            else -> WelcomeUIState(
                title = localizedString(R.string.rank_title, String.empty),
                body = localizedString(R.string.rank_body, String.empty),
                avatar = null,
                actions = RankActions.COUNT_CLICKS,
            )
        }

        _uiState.update { newUIState }

    }

    private fun generateInitialState(): WelcomeUIState = WelcomeUIState(
        title = localizedString(R.string.rank_title, String.empty),
        body = localizedString(R.string.rank_body, String.empty),
        avatar = null,
        actions = RankActions.COUNT_CLICKS,
    )

    companion object {
                private val RANK_0_RANGE = 0..1
                private val RANK_1_RANGE = 2..3
                private val RANK_2_RANGE = 4..5
                private val RANK_3_RANGE = 6..7
                private val RANK_4_RANGE = 8..9
                private val RANK_5_RANGE = 10..11
                private val RANK_6_RANGE = 12..13
                private val RANK_7_RANGE = 14..15
                private val RANK_8_RANGE = 16..17
                private val RANK_9_RANGE = 18..Int.MAX_VALUE

        /*       private val RANK_0_RANGE = 0..9
               private val RANK_1_RANGE = 10..19
               private val RANK_2_RANGE = 20..34
               private val RANK_3_RANGE = 35..54
               private val RANK_4_RANGE = 55..79
               private val RANK_5_RANGE = 80..109
               private val RANK_6_RANGE = 110..144
               private val RANK_7_RANGE = 145..184
               private val RANK_8_RANGE = 185..229
               private val RANK_9_RANGE = 230..Int.MAX_VALUE*/
    }
}

data class WelcomeUIState(
    val title: LocalizedString,
    val body: LocalizedString,
    @DrawableRes val avatar: Int?,
    val actions: RankActions,
)

enum class RankActions {
    COUNT_CLICKS,
    UNIVERSE_DECISION
}
