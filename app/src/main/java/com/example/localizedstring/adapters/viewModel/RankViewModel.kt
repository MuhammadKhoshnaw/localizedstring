package com.example.localizedstring.adapters.viewModel

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.localizedstring.R
import com.example.localizedstring.entity.LocalizedString
import com.example.localizedstring.entity.UniverseDestinyResult
import com.example.localizedstring.entity.empty
import com.example.localizedstring.entity.localizedString
import com.example.localizedstring.usecase.UniverseDestinyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RankViewModel @Inject constructor(
    private val universeDestinyUseCase: UniverseDestinyUseCase,
) : ViewModel() {

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

    fun onResetExistence() = viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            Timber.i("trying to reset existence")
            _uiState.update { it.copy(actions = RankActions.Loading) }
            val result = universeDestinyUseCase.resetExistence()
            updateUiWith(result)
        }.onFailure {
            Timber.e(it, "Error resetting existence")
        }
    }

    fun onForgeNewReality() = viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            Timber.i("trying to forge new reality")
            _uiState.update { it.copy(actions = RankActions.Loading) }
            val result = universeDestinyUseCase.forgeANewUniverse()
            updateUiWith(result)
        }.onFailure {
            Timber.e(it, "Error forging new reality")
        }
    }

    fun onTryAgain() {
        Timber.i("user clicked try again button")
        _uiState.update { it.copy(actions = RankActions.UniverseDecision) }
    }

    fun onOk() {
        Timber.i("user clicked ok button")
        count = 0
    }

    private fun updateUiWith(result: UniverseDestinyResult) = _uiState.update {
        it.copy(
            body = result.message,
            actions = if (result.success) RankActions.Ok else RankActions.TryAgain
        )
    }

    private fun updateRank() {
        Timber.i("updating rank")

        val newUIState: WelcomeUIState = when (count) {
            in RANK_0_RANGE -> WelcomeUIState(
                title = localizedString(R.string.rank_title, localizedString(R.string.rank_0)),
                body = localizedString(R.string.rank_body, count.toString()),
                avatar = R.drawable.rank_0,
                actions = RankActions.CountClicks,
            )

            in RANK_1_RANGE -> WelcomeUIState(
                title = localizedString(R.string.rank_title, localizedString(R.string.rank_1)),
                body = localizedString(R.string.rank_body, count.toString()),
                avatar = R.drawable.rank_1,
                actions = RankActions.CountClicks,
            )

            in RANK_2_RANGE -> WelcomeUIState(
                title = localizedString(R.string.rank_title, localizedString(R.string.rank_2)),
                body = localizedString(R.string.rank_body, count.toString()),
                avatar = R.drawable.rank_2,
                actions = RankActions.CountClicks,

                )

            in RANK_3_RANGE -> WelcomeUIState(
                title = localizedString(R.string.rank_title, localizedString(R.string.rank_3)),
                body = localizedString(R.string.rank_body, count.toString()),
                avatar = R.drawable.rank_3,
                actions = RankActions.CountClicks,
            )

            in RANK_4_RANGE -> WelcomeUIState(
                title = localizedString(R.string.rank_title, localizedString(R.string.rank_4)),
                body = localizedString(R.string.rank_body, count.toString()),
                avatar = R.drawable.rank_4,
                actions = RankActions.CountClicks,
            )

            in RANK_5_RANGE -> WelcomeUIState(
                title = localizedString(R.string.rank_title, localizedString(R.string.rank_5)),
                body = localizedString(R.string.rank_body, count.toString()),
                avatar = R.drawable.rank_5,
                actions = RankActions.CountClicks,
            )

            in RANK_6_RANGE -> WelcomeUIState(
                title = localizedString(R.string.rank_title, localizedString(R.string.rank_6)),
                body = localizedString(R.string.rank_body, count.toString()),
                avatar = R.drawable.rank_6,
                actions = RankActions.CountClicks,
            )

            in RANK_7_RANGE -> WelcomeUIState(
                title = localizedString(R.string.rank_title, localizedString(R.string.rank_7)),
                body = localizedString(R.string.rank_body, count.toString()),
                avatar = R.drawable.rank_7,
                actions = RankActions.CountClicks,
            )

            in RANK_8_RANGE -> WelcomeUIState(
                title = localizedString(R.string.rank_title, localizedString(R.string.rank_8)),
                body = localizedString(R.string.rank_body, count.toString()),
                avatar = R.drawable.rank_8,
                actions = RankActions.CountClicks,
            )

            in RANK_9_RANGE -> WelcomeUIState(
                title = localizedString(R.string.rank_title, localizedString(R.string.rank_9)),
                body = localizedString(R.string.last_rank_body, count.toString()),
                avatar = R.drawable.rank_9,
                actions = RankActions.UniverseDecision,
            )

            else -> WelcomeUIState(
                title = localizedString(R.string.rank_title, String.empty),
                body = localizedString(R.string.rank_body, String.empty),
                avatar = null,
                actions = RankActions.CountClicks,
            )
        }

        _uiState.update { newUIState }

    }

    private fun generateInitialState(): WelcomeUIState = WelcomeUIState(
        title = localizedString(R.string.rank_title, String.empty),
        body = localizedString(R.string.rank_body, String.empty),
        avatar = null,
        actions = RankActions.CountClicks,
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

sealed interface RankActions {
    data object CountClicks : RankActions
    data object UniverseDecision : RankActions
    data object Loading : RankActions
    data object TryAgain : RankActions
    data object Ok : RankActions
}


